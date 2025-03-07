package com.aim.metaheuristics.population.geneticoperators.crossover;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.CrossoverHeuristic;

import java.util.Random;

/**
 * @author Warren G Jackson
 * @since 27/02/2025
 *
 * Can't call this class 1PTX in Java (:
 */
public class OnePTX extends CrossoverHeuristic {

	public OnePTX(SAT problem, Random random) {
		
		super(problem, random);
	}

	/**
	 * Applies 1PTX to the parents in parent1Index and parent2Index to produce the offspring
	 * in solution indices child1Index and child2Index
	 *
	 * @param parent1Index The memory index of the first parent.
	 * @param parent2Index The memory index of the second parent.
	 * @param child1Index The memory index of the store the first child/offspring.
	 * @param child2Index The memory index of the store the second child/offspring.
	 */
	public void applyHeuristic(int parent1Index, int parent2Index, int child1Index, int child2Index) {
		
		//copy all bits to child indices
		problem.copySolution(parent1Index, child1Index);
		problem.copySolution(parent2Index, child2Index);

		// ensure results in modified solutions by choosing a crossover point *within* the representation
		int iCrossoverPoint = 1 + random.nextInt(problem.getNumberOfVariables() - 1);
		
		for(int i = iCrossoverPoint; i < problem.getNumberOfVariables(); i++) {

			// here we need to use a new method `exchangeBits`
			problem.exchangeBits(child1Index, child2Index, i);
		}
	}
}
