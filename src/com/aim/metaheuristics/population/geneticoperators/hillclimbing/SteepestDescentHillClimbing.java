package com.aim.metaheuristics.population.geneticoperators.hillclimbing;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationHeuristic;

import java.util.Random;

public abstract class SteepestDescentHillClimbing extends PopulationHeuristic {

	public SteepestDescentHillClimbing(SAT problem, Random random) {
		
		super(problem, random);
	}

	public void applyHeuristic(int iSolutionMemoryIndex) {

		int iBestIndex = 0;
		double currentCost = problem.getObjectiveFunctionValue(iSolutionMemoryIndex);
		for (int j = 0; j < problem.getNumberOfVariables(); j++) {
			
			problem.bitFlip(j, iSolutionMemoryIndex);
			double candidateCost = problem.getObjectiveFunctionValue(iSolutionMemoryIndex);
			
			if (acceptMove(currentCost, candidateCost)) {

				currentCost = candidateCost;
				iBestIndex = j;
			}
				
			problem.bitFlip(j, iSolutionMemoryIndex);
		}

		problem.bitFlip(iBestIndex, iSolutionMemoryIndex);
	}

	public abstract boolean acceptMove(double paramDouble1, double paramDouble2);
}
