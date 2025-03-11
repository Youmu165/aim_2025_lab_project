package com.aim.heuristics;


import java.util.Random;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.SATHeuristic;


public class SteepestDescentHC extends SATHeuristic {

	public SteepestDescentHC(Random random) {
		
		super(random);
	}

	/**
	  * STEEPEST DESCENT HILL CLIMBING LECTURE SLIDE PSEUDO-CODE
	  *
	  *	bestEval = evaluate(currentSolution)
	  *	for (j = 0; j < length(currentSolution); j++) { // performs a single pass of the solution
	  *	
	  *		bitFlip(currentSolution, j) // flip the j^th bit of the solution to produce s' from s
	  *		tempEval = evaluate(solution)
	  *	
	  *		if(tempEval < bestEval) {
	  *			// remember which bit led to the most improvement
	  *			bestIndex = j
	  *			bestEval = tempEval
	  *			improved = true
	  *		}
	  *	
	  *		bitFlip(currentSolution, j) // revert the bit flip so we can try another
	  *	}
	  *	
	  *	if(improved) bitflip(currentSolution, bestIndex)
	  *
	  * @param problem The problem to be solved.
	  */
	public void applyHeuristic(SAT problem) {
//		int currentSolutionIndex = 0;
//		int numberOfVariables = problem.getNumberOfVariables();
//		// TODO
//		double bestEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
//		int bestIndex = -1;
//		boolean isImproved = true;
//		for (int i = 0; i < numberOfVariables; i++) {
//			problem.bitFlip( i);
////			bestEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
//			double tempEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
//			if (tempEval <= bestEval) {
//				bestIndex = i;
//				bestEval = tempEval;
//				isImproved = true;
//
//			}
//
//			problem.bitFlip( i,currentSolutionIndex);
//
//		}
//		if(isImproved)
//		{
//			problem.bitFlip( bestIndex);
//		}
//		return;
		int bestIndex = 0;
		int currentIndex = CURRENT_SOLUTION_INDEX;
        double currentCost =  problem.getObjectiveFunctionValue(currentIndex);
		boolean isImproved = false;
		double tempEval = 0;
		for (int j = 0; j < problem.getNumberOfVariables(); j++ )
		{
			problem.bitFlip(j, currentIndex);
			tempEval = problem.getObjectiveFunctionValue(currentIndex);

			if(tempEval <= currentCost)
			{
				bestIndex = j;
				currentCost = tempEval;
				isImproved = true;

			}
			problem.bitFlip(j, currentIndex);

		}

		if (isImproved)
		{
			problem.bitFlip(bestIndex, currentIndex);
		}
	}

	public String getHeuristicName() {
		
		return "SDHC";
	}

}
