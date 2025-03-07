package com.aim.metaheuristics.population.geneticoperators.hillclimbing;

import java.util.Random;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;

/**
 * @author Warren G Jackson
 * @since 27/02/2025
 */
public class DBHC_IE extends DavissBitHillClimbing {
	
	public DBHC_IE(SAT oProblem, Random oRandom) {
		
		super(oProblem, oRandom);
	}

	public boolean acceptMove(double dCurrentSolutionFitness, double dCandidateSolutionFitness) {
		
		return dCandidateSolutionFitness <= dCurrentSolutionFitness;
	}
}
