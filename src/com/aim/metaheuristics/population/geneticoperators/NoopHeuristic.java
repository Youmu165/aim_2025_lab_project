package com.aim.metaheuristics.population.geneticoperators;

import java.util.Random;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationHeuristic;

/**
 * @author Warren G Jackson
 * @since 27/02/2025
 */
public class NoopHeuristic extends PopulationHeuristic {

	public NoopHeuristic(SAT oProblem, Random oRandom) {
		
		super(oProblem, oRandom);
	}

	@Override
	public void applyHeuristic(int iMemoryIndex) {

		return; // i.e. does nothing
	}

}
