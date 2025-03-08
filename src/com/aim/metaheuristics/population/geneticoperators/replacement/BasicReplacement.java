
package com.aim.metaheuristics.population.geneticoperators.replacement;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationReplacement;

import java.util.stream.IntStream;

/**
 * @author Warren G Jackson
 * @since 27/02/2025
 */
public class BasicReplacement extends PopulationReplacement {

	/**
	 * Replaces the current population with the offspring population.
	 *
	 * @return The indices of the solutions to use in the next generation.
	 *
	 * PSEUDOCODE
	 *
	 * INPUT current_pop, offspring_pop
	 * OUTPUT: offspring_pop; // return the indices of the next population
	 */
	@Override
	protected int[] getNextGeneration(SAT oProblem, int iPopulationSize) {
		
		// offspring indices are from 'populationSize' inclusive to 'populationSize * 2' exclusive.
        return IntStream.range(iPopulationSize, iPopulationSize << 1).toArray();
	}

}
