package com.aim.metaheuristics.population.geneticoperators.parentselection;

import java.util.Random;
import java.util.stream.IntStream;

import com.aim.metaheuristics.population.ParentSelection;
import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.helperfunctions.ArrayMethods;

/**
 * @author Warren G. Jackson
 */
public class TournamentSelection extends ParentSelection {

	private final int iTournamentSize;

	private final int[] aiParentIndices;

	public TournamentSelection(SAT problem, Random random, int POPULATION_SIZE, int tournamentSize) {
		
		super(problem, random, POPULATION_SIZE);

		this.iTournamentSize = tournamentSize;
		this.aiParentIndices = IntStream.range(0, POPULATION_SIZE).toArray();
	}

	/**
	  * @return The index of the chosen parent solution.
	  *
	  * PSEUDOCODE
	  *
	  * INPUT: parent_pop, tournament_size
	  * solutions = getUniqueRandomSolutions(tournament_size); 
	  * bestSolution = getBestSolution(solutions);
	  * index = indexOf(bestSolution);
	  * return index;
	  */
	public int parentSelection() {
		
		int bestIndex = -1;
		double bestFitness = Double.MAX_VALUE;
		
		//create list of random indices
		int[] indices = ArrayMethods.shuffle(aiParentIndices, oRandom);

		// select from the first tournamentSize elements
		for(int i = 0; i < iTournamentSize; i++) {

			int sol = indices[i];
			double fitness = oProblem.getObjectiveFunctionValue(sol);

			// don't need to be concerned about selecting randomly from multiple best
			// solutions as the tournamentSize parents were already randomised.
			if(fitness < bestFitness) {
				bestFitness = fitness;
				bestIndex = sol;
			}
		}
		
		return bestIndex;
	}
}
