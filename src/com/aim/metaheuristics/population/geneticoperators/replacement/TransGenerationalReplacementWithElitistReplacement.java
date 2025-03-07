
package com.aim.metaheuristics.population.geneticoperators.replacement;

import java.util.stream.IntStream;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationReplacement;

/**
 * @author Warren G Jackson
 * @since 27/02/2025
 */
public class TransGenerationalReplacementWithElitistReplacement extends PopulationReplacement {

	/**
	 * Replaces the current population with the offspring and replaces the worst
	 * offspring with the best solution if the best is not contained in the offspring.
	 *
	 * @return The indices of the solutions to use in the next generation.
	 *
	 * PSEUDOCODE
	 *
	 * INPUT current_pop, offspring_pop
	 * fitnesses <- evaluate( current_pop U offspring_pop );
	 * best <- min( fitnesses );
	 * next_pop <- indicesOf( offspring_pop );
	 * IF best \notin offspring_pop THEN
	 *     next_pop.replace( worst, best );
	 * ENDIF
	 * OUTPUT: next_pop; // return the indices of the next population
	 */
	@Override
	protected int[] getNextGeneration(SAT oProblem, int iPopulationSize) {
		int best = 0;

		// TODO - study BasicReplacement for hints.
		for (int i = 0; i < iPopulationSize * 2; i++) {
			if (oProblem.getObjectiveFunctionValue(i) < oProblem.getObjectiveFunctionValue(best)) {
				best = i;
			}
		}

		int[] ret = new int[iPopulationSize];
		for (int i = 0; i < iPopulationSize; i++) {
			ret[i] = iPopulationSize + i;
		}

		if (best < iPopulationSize) {

			int worst = iPopulationSize;
			for (int i = iPopulationSize; i < iPopulationSize * 2; i++) {
				if (oProblem.getObjectiveFunctionValue(i) > oProblem.getObjectiveFunctionValue(worst)) {
					worst = i;
				}
			}

			ret[worst - iPopulationSize] = best;
		}
		return ret;
	}

}
