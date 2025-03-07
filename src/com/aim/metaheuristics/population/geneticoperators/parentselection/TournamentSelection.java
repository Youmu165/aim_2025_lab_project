package com.aim.metaheuristics.population.geneticoperators.parentselection;

import java.util.Random;
import java.util.stream.IntStream;

import com.aim.metaheuristics.population.ParentSelection;
import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.helperfunctions.ArrayMethods;

/**
 * @author Warren G Jackson
 * @since 27/02/2025
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

		int best = 0;
		for (int i = 0; i < iTournamentSize; i++) {
			int solution = oRandom.nextInt(iTournamentSize);
			if(oProblem.getObjectiveFunctionValue(solution) < oProblem.getObjectiveFunctionValue(best))
			{
				best = solution;
			}
		}
		
		// TODO - study FittestSelection and RandomSelection for hints
		return best;
	}
}
