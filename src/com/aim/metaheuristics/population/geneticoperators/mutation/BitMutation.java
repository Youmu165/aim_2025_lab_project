package com.aim.metaheuristics.population.geneticoperators.mutation;

import java.util.Random;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.satheuristics.genetics.PopulationHeuristic;

/**
 * 
 * @author Warren G. Jackson
 * @since 27/02/2025
 */
public class BitMutation extends PopulationHeuristic {
	
	/**
	 * 
	 */
	private double m_dMutationRate;
	
	/**
	 * 
	 */
	private final int m_iNumVariables;

	/**
	 * 
	 * @param oProblem
	 * @param oRandom
	 */
	public BitMutation(SAT oProblem, Random oRandom) {
		super(oProblem, oRandom);

		this.m_iNumVariables = oProblem.getNumberOfVariables();
		setMutationRate(1);
	}

	/**
	 * 
	 * @param iIntensityOfMutation
	 */
	public void setMutationRate(int iIntensityOfMutation) {

		this.m_dMutationRate = ((double) iIntensityOfMutation / this.m_iNumVariables);
	}

	@Override
	public void applyHeuristic(int iSolutionMemoryIndex) {
		
		for (int i = 0; i < this.problem.getNumberOfVariables(); i++) {
			
			if (this.random.nextDouble() < this.m_dMutationRate) {
				
				this.problem.bitFlip(i, iSolutionMemoryIndex);
			}
		}
	}
}
