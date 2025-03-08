package com.aim.metaheuristics.population.geneticoperators.hillclimbing;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;

import java.util.Random;

/**
 * @author Warren G Jackson
 * @since 27/02/2025
 */
public class SDHC_IE extends SteepestDescentHillClimbing {

	public SDHC_IE(SAT oProblem, Random oRandom) {
		
		super(oProblem, oRandom);
	}

	public boolean acceptMove(double dCurrentSolutionFitness, double dCandidateSolutionFitness) {
		
		return dCandidateSolutionFitness <= dCurrentSolutionFitness;
	}
}
