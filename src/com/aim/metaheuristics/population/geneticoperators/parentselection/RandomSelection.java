package com.aim.metaheuristics.population.geneticoperators.parentselection;

import com.aim.metaheuristics.population.ParentSelection;
import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;

import java.util.Random;

/**
 * @author Warren G. Jackson
 * @since 27/02/2025
 */
public class RandomSelection extends ParentSelection {

	public RandomSelection(SAT problem, Random rng, int POPULATION_SIZE) {
		
		super(problem, rng, POPULATION_SIZE);
	}

	public int parentSelection() {

		// parent solutions are in memory indices [0,POPULATION_SIZE)
		return oRandom.nextInt(iPopulationSize);
	}
}
