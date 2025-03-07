package com.aim.metaheuristics.population.geneticoperators.parentselection;

import com.aim.metaheuristics.population.ParentSelection;
import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Warren G. Jackson
 * @since 27/02/2025
 */
public class FittestSelection extends ParentSelection {

	public FittestSelection(SAT problem, Random rng, int POPULATION_SIZE) {
		
		super(problem, rng, POPULATION_SIZE);
	}

	/**
	  * @return The index of the chosen parent solution.
	  *
	  * PSEUDOCODE
	  *
	  * INPUT: parent_pop
	  * bestSolution = getBestSolution(solutions);
	  * index = indexOf(bestSolution);
	  * return index;
	  */
	public int parentSelection() {
		
		int bestIndex = -1;
		double bestFitness = Double.MAX_VALUE;
		ArrayList<Integer> oBestParents = new ArrayList<Integer>();

		// find the best solution in the entire parent population
		for(int index = 0; index < iPopulationSize; index++) {

			double fitness = oProblem.getObjectiveFunctionValue(index);
			
			if(fitness < bestFitness) {
				// set the best solution found
				bestFitness = fitness;
				oBestParents.clear();
				oBestParents.add(index);
			} else if(fitness == bestFitness) {
				// record equal best cost solutions for ties
				oBestParents.add(index);
			}
		}

		// select best solution uniformly at random from the list of best solutions (if more than 1)
		return oBestParents.get(oRandom.nextInt(oBestParents.size()));
	}
}
