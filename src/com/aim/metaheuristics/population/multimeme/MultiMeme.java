package com.aim.metaheuristics.population.multimeme;

import java.util.Random;

import com.aim.metaheuristics.population.ParentSelection;
import com.aim.metaheuristics.population.geneticoperators.mutation.BitMutation;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.Meme;
import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.CrossoverHeuristic;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationHeuristic;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationReplacement;
import uk.ac.nott.cs.aim.searchmethods.PopulationBasedSearchMethod;

/**
 * @author Warren G Jackson
 * @since 04/03/2025
 */
public class MultiMeme extends PopulationBasedSearchMethod {

	/**
	 * The innovation rate setting
	 */
	private final double innovationRate;
	
	private final CrossoverHeuristic crossover;
	private final BitMutation mutation;
	private final PopulationReplacement replacement;
	private final ParentSelection p1selection;
	private final ParentSelection p2selection;

	private final MemeplexInheritanceMethod inheritance;
	
	/**
	 * The possible local search operators to use.
	 */
	private final PopulationHeuristic[] lss;
	
	// Constructor used for testing. Please do not remove!
	/**
	 *
	 * @param problem
	 * @param rng
	 * @param populationSize
	 * @param innovationRate
	 * @param crossover
	 * @param mutation
	 * @param replacement
	 * @param p1selection
	 * @param p2selection
	 * @param inheritance
	 * @param lss
	 */
	public MultiMeme(SAT problem, Random rng, int populationSize, double innovationRate, CrossoverHeuristic crossover,
							 BitMutation mutation, PopulationReplacement replacement, ParentSelection p1selection, ParentSelection p2selection, MemeplexInheritanceMethod inheritance,
							 PopulationHeuristic[] lss) {

		super(problem, rng, populationSize);

		this.innovationRate = innovationRate;
		this.crossover = crossover;
		this.mutation = mutation;
		this.replacement = replacement;
		this.p1selection = p1selection;
		this.p2selection = p2selection;
		this.inheritance = inheritance;
		this.lss = lss;
	}

	/**
	 * MMA PSEUDOCODE:
	 * 
	 * INPUT: PopulationSize, MaxGenerations, InnovationRate
	 * 
	 * generateInitialPopulation();
	 * FOR 0 -> MaxGenerations
	 * 
	 ####### BEGIN IMPLEMENTING HERE #######
	 *     FOR 0 -> PopulationSize / 2
	 *         select parents using tournament selection with tournament size = 3
	 *         apply crossover to generate offspring
	 *         inherit memeplex using simple inheritance method
	 *         mutate the memes within each memeplex of each child with  probability dependent on the innovation rate
	 *         apply mutation to offspring with intensity of mutation set for each solution dependent on its meme option
	 *         apply local search to offspring with choice of operator dependent on each solution�s meme option
	 *     ENDFOR
	 *     do population replacement
	 ####### STOP IMPLEMENTING HERE #######
	 * ENDFOR
	 * return s_best;
	 */
	public void runMainLoop() {
		
				for(int i = 0; i < POPULATION_SIZE; i+=2) {
			
			// select two parents. we don't care if they are the same this week
			int p1 = p1selection.parentSelection();
			int p2 = p2selection.parentSelection();

			// calculate child indices
			int c1 = i + POPULATION_SIZE;
			int c2 = c1 + 1;
			
			// apply crossover
			crossover.applyHeuristic(p1, p2, c1, c2);



			// TODO complete the implementation of the MMA
			// ...
		}
		
		// perform replacement
		replacement.doReplacement(problem, POPULATION_SIZE);
	}
	
	/**
	 * Applies mutation to the child dependent on its current meme option for mutation.
	 * Mapping of meme option to IOM: IntensityOfMutation <- memeOption;
	 * 
	 * @param childIndex The solution memory index of the child to mutate.
	 * @param memeIndex The meme index used for storing the meme relating to the intensity of mutation setting.
	 */
	public void applyMutationForChildDependentOnMeme(int childIndex, int memeIndex) {
		
		// TODO implementation of mutation embedding intensity of mutation from memes
		// ...
	}
	
	/**
	 * Applies the local search operator to the child as specified by its current meme option.
	 * 
	 * @param childIndex The solution memory index of the child to mutate.
	 * @param operatorMemeIndex The meme index used for storing the meme relating to the local search operator setting.
	 * @param depthOfSearchMemeIndex The meme index used for storing the meme relating to the depth of search parameter setting.
	 */
	public void applyLocalSearchForChildDependentOnMeme(int childIndex, int operatorMemeIndex, int depthOfSearchMemeIndex) {
		
		// TODO implementation of local search dependent on memes
		// ...
	}

	/**
	 * Applies mutation to each meme within the memeplex of the specified solution with probability
	 * dependent on the innovation rate.
	 * 
	 * HINT: mutation does not mean bit flip; it only means in this case 
	 * 		that you should MODIFY the current value of the meme option
	 * 		subject to the above definition.
	 * 
	 * @param solutionIndex The solution memory index of the solution to mutate the memeplex of.
	 */
	public void performMutationOfMemeplex(int solutionIndex) {
		
		// TODO implementation of mutation of memeplex
		// ...
	}
	
	public String toString() {
		
		return "Multimeme Memetic Algorithm";
	}
	
}
