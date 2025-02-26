package com.aim.metaheuristics.singlepoint.simulatedannealing;

/**
 * 
 * @author Warren G. Jackson
 *
 */
public class LundyAndMees implements CoolingSchedule {

	/**
	 * initial factor
	 */

	private final double c;
	/**
	 * Maintain the state of the current temperature
	 */
	private double dCurrentTemperature;
	
	/**
	 * The $\beta$ parameter of the Lundy and Mees cooling schedule.
	 * Recall from the lectures what a "reasonable" setting should be but be prepared to experiment to find a "good" value!
	 */
	private final double dBeta;
	
	/**
	 * 
	 * @param initialSolutionFitness
	 *            The objective value of the initial solution. Maybe useful (or
	 *            not) for some setting?
	 */
	public LundyAndMees(double initialSolutionFitness) {

		// 'c' is a multiplier used to set the initial temperature based on the cost of the initial solution
		// you may change this in your experimentation
		this.c = 1d;
		this.dCurrentTemperature = c * initialSolutionFitness;
		
		// TODO You will need to find a suitable value for beta 
		//      through prior knowledge and experimentation!
		this.dBeta = 0.999999d;
	}
	
	@Override
	public double getCurrentTemperature() {
		
		return dCurrentTemperature;
	}

	/**
	 * DEFINITION: T_{i + 1} = T_i / ( 1 + beta * T_i )
	 */
	@Override
	public void advanceTemperature() {
		
		// TODO update the value of the current temperature, 'dCurrentTemperature'
		dCurrentTemperature = dCurrentTemperature / (1 + dBeta * dCurrentTemperature);
	}
	
	@Override
	public String toString() {
		
		return "Lundy and Mees (T_0 = " + c + ".f(s_0)β = " + this.dBeta + ")";
	}

}
