package com.aim.pseudorandom;

import java.util.Random;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.SATHeuristic;


/**
 * A heuristic to flip a random bit.
 * @author Warren G. Jackson
 */
public class RandomBitFlipHeuristic extends SATHeuristic {

	public RandomBitFlipHeuristic(Random random) {
		
		super(random);
	}

	@Override
	public void applyHeuristic(SAT problem) {
		
		// TODO - select a random bit in the solution and flip it
//		boolean[] solution = problem.getSolution(SINGLE_POINT_SOLUTION_INDEX);
//		int numVariables = solution.length;
//		int flipIndex = random.nextInt(numVariables);
//		solution[flipIndex] = !solution[flipIndex];
//		problem.setSolution(SINGLE_POINT_SOLUTION_INDEX, solution);
//		problem.getObjectiveFunctionValue(SINGLE_POINT_SOLUTION_INDEX);

//		int numVariables = problem.getNumberOfVariables();
//		int flipIndex = random.nextInt(numVariables);
//		problem.bitFlip(flipIndex);

		problem.bitFlip(random.nextInt(problem.getNumberOfVariables()));
	}

	@Override
	public String getHeuristicName() {
		
		return "Random Bit Flip";
	}
}
