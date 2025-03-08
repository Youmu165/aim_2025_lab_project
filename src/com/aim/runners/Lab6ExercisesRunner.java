package com.aim.runners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import com.aim.TestFrame;
import com.aim.TestFrameConfig;

import com.aim.metaheuristics.population.geneticoperators.crossover.OnePTX;
import com.aim.metaheuristics.population.geneticoperators.crossover.UniformXO;
import com.aim.metaheuristics.population.geneticoperators.hillclimbing.*;
import com.aim.metaheuristics.population.geneticoperators.mutation.BitMutation;
import com.aim.metaheuristics.population.geneticoperators.parentselection.FittestSelection;
import com.aim.metaheuristics.population.geneticoperators.parentselection.RandomSelection;
import com.aim.metaheuristics.population.geneticoperators.parentselection.TournamentSelection;
import com.aim.metaheuristics.population.geneticoperators.replacement.BasicReplacement;
import com.aim.metaheuristics.population.geneticoperators.replacement.TransGenerationalReplacementWithElitistReplacement;
import com.aim.metaheuristics.population.multimeme.MultiMeme;
import com.aim.metaheuristics.population.multimeme.SimpleInheritanceMethod;
import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationHeuristic;
import uk.ac.nott.cs.aim.statistics.PlotData;
import uk.ac.nott.cs.aim.statistics.XBoxPlot;

public class Lab6ExercisesRunner extends TestFrame {

	private final Object best_lock = new Object();
	private final Object output_lock = new Object();

	public Lab6ExercisesRunner(Lab6ExercisesTestFrameConfig config) {
		super(config);
	}
	
	public void runTests() {
		
		Lab6ExercisesTestFrameConfig config = (Lab6ExercisesTestFrameConfig)getTestConfiguration();
		double[] data = new double[config.getTotalRuns()];
		long[] SEEDS = getExperimentalSeeds();

		ArrayList<Double> runScores = new ArrayList<Double>();

		final double[] bestFoundCost = {Double.MAX_VALUE};
		final String[] bestFoundRepresentation = {null};

		// store allele frequencies across all runs
		ConcurrentHashMap<String, AtomicLong> oMapAllelesToFrequencies = new ConcurrentHashMap<>();
		int[] optionsPerMeme = config.getOptionsPerMeme();
		for (int i = 0; i < optionsPerMeme.length; i++) {

			for (int j = 0; j < optionsPerMeme[i]; j++) {

				String str = String.format("M_%dA_%d", i, j);
				oMapAllelesToFrequencies.put(str, new AtomicLong(0));
			}
		}

		List<Double> costs = IntStream.range(0, config.getTotalRuns()).parallel().boxed().map(trial -> {

			//generation based termination
			Random random = new Random(SEEDS[trial]);

			SAT sat = new SAT(config.getInstanceId(), config.getRunTime(), random, config.getPopulationSize(), config.getMemeCount(), config.getOptionsPerMeme());

			ArrayList<ArrayList<Long>> memeUsage = new ArrayList<ArrayList<Long>>();

			//initialise allele counters to 0
			for(int i = 0; i < config.getMemeCount(); i++) {

				memeUsage.add(i, new ArrayList<Long>());
				for(int j = 0; j < config.getOptionsPerMeme()[i]; j++) {

					memeUsage.get(i).add(j, 0L);
				}
			}

			LinkedList<ArrayList<Double>> fitnessTrace = new LinkedList<ArrayList<Double>>();
			for(int i = 0; i < config.getPopulationSize(); i++) {
				fitnessTrace.add(new ArrayList<Double>());
			}

			// create a MultiMeme dependent upon default configurations
			MultiMeme mma = new MultiMeme(sat,
						random,
						config.getPopulationSize(),
						config.INNOVATION_RATE,
						new UniformXO(sat, random), // crossover
						new BitMutation(sat, random), // mutation
						new TransGenerationalReplacementWithElitistReplacement(), // replacement
						new TournamentSelection(sat, random, config.POP_SIZE, 3), // parent selection
						new TournamentSelection(sat, random, config.POP_SIZE, 3), // parent selection
						new SimpleInheritanceMethod(sat, random), // memeplex inheritance
						new PopulationHeuristic[] { // create mapping for local search operators used for meme in meme index 1
								new DBHC_OI(sat, random), // [0]
								new DBHC_IE(sat, random), // [1]
								new SDHC_OI(sat, random), // [2]
								new SDHC_IE(sat, random)  // [3]
						});

			int count = 0;
			while(!sat.hasTimeExpired() && count <= config.MAX_GENERATIONS) {

				mma.run();

				//add all of population
				PriorityQueue<Double> pq = new PriorityQueue<>();
				for(int i = 0; i < config.getPopulationSize(); i++) {
					pq.add(sat.getObjectiveFunctionValue(i));
				}

				for(int i = 0; i < config.getPopulationSize(); i++) {
					fitnessTrace.get(i).add(pq.remove());
				}

				for(int i = 0; i < config.getMemeCount(); i++) {

					for(int j = 0; j < config.getPopulationSize(); j++) {
						int allele = sat.getMeme(j, i).getMemeOption();
						long c = memeUsage.get(i).get(allele);
						memeUsage.get(i).set(allele, c + 1);
					}
				}

				count++;
			}

			double currentBestSolution = sat.getBestSolutionValue();
			data[trial] = currentBestSolution;
			runScores.add(currentBestSolution);

			System.out.println("Heuristic: " + mma.toString());
			System.out.println("Run ID: " + trial);
			System.out.println("Best Solution Value: " + sat.getBestSolutionValue());
			System.out.println("Best Solution: " + sat.getBestSolutionAsString());

			for(int i = 0; i < config.getMemeCount(); i++) {

				System.out.println("MEME " + i + ":");
				for(int j = 0; j < config.getOptionsPerMeme()[i]; j++) {

					long frequency = memeUsage.get(i).get(j);
					System.out.println("Allele " + j + " = " + frequency);
					String str = String.format("M_%dA_%d", i, j);
					oMapAllelesToFrequencies.get(str).addAndGet(frequency);
				}
			}

			System.out.println();

			synchronized (best_lock) {

				// ugly java lambda hack, never copy this... this is only safe due to the syncrhonisation!
				if(sat.getBestSolutionValue() < bestFoundCost[0]) {

					bestFoundCost[0] = sat.getBestSolutionValue();
					bestFoundRepresentation[0] = sat.getBestSolutionAsString();
				}
			}

			synchronized (output_lock) {


				//print or save results
				StringBuilder sb = new StringBuilder();
				sb.append(config.INNOVATION_RATE + ",").append(config.getRunTime()).append(",").append(config.getInstanceId());
				for(double ofv : runScores) {
					sb.append(",").append(ofv);
				}

				sb.append(",").append(bestFoundRepresentation[0]);

				System.out.println("Best Solution ::" + bestFoundRepresentation[0]);
				saveData(config.getTotalRuns() + "Runs.csv", sb.toString());

			}

			return sat.getBestSolutionValue();

		}).toList();

		System.out.println();

		System.out.println("Total allele frequencies:");
		for (int i = 0; i < optionsPerMeme.length; i++) {

			for (int j = 0; j < optionsPerMeme[i]; j++) {

				String str = String.format("M_%dA_%d", i, j);
				System.out.println(str + " = " + oMapAllelesToFrequencies.get(str));
			}

			System.out.println();
		}

		List<PlotData> oPlotData = new ArrayList<>();
		oPlotData.add(new PlotData(costs, "MMA"));

		XBoxPlot.getPlotCreator().createPlot(config.getBoxPlotTitle(), "Heuristic", "Objective Value", oPlotData);
	}
	
	private void saveData(String filePath, String data) {
		
		Path path = Paths.get("./" + filePath);
		if(!Files.exists(path)) {
			try {
				Files.createFile(path);
				TestFrameConfig CFG = getTestConfiguration();
				
				//add header
				StringBuilder header = new StringBuilder("Heuristic,Innovation Rate,Run Time,Instance ID");
				for(int i = 0; i < CFG.getTotalRuns(); i++) {
					
					header.append(",").append(i);
				}
				
				header.append(",Best Solution As String");
				
				Files.write(path, (header + "\r\n" + data).getBytes());
				
			} catch (IOException e) {
				System.err.println("Could not create file at " + path.toAbsolutePath());
				System.err.println("Printing data to screen instead...");
				System.out.println(data);
			}
			
		} else {
			
			try {
				byte[] currentData = Files.readAllBytes(path);
				data = "\r\n" + data;
				byte[] newData = data.getBytes();
				byte[] writeData = new byte[currentData.length + newData.length];
				System.arraycopy(currentData, 0, writeData, 0, currentData.length);
				System.arraycopy(newData, 0, writeData, currentData.length, newData.length);
				Files.write(path, writeData);
				
			} catch (IOException e) {
				System.err.println("Could not create file at " + path.toAbsolutePath());
				System.err.println("Printing data to screen instead...");
				System.out.println(data);
			}
			
		}
		
	}
	
	public static void main(String [] args) {
		
		Lab6ExercisesTestFrameConfig config = Lab6ExercisesTestFrameConfig.getInstance();
		TestFrame runner = new Lab6ExercisesRunner(config);
		runner.runTests();
	}
}
