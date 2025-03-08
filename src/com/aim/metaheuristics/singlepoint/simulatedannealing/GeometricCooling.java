package com.aim.metaheuristics.singlepoint.simulatedannealing;

/**
 * 
 * @author Warren G. Jackson
 *
 */
public class GeometricCooling implements CoolingSchedule {
	
	/**
	 * Maintain the state of the current temperature
	 */
	private double dCurrentTemperature;
	
	/**
	 * The $\alpha$ parameter of the cooling schedule.
	 * Recall from the lectures what a "reasonable" setting should be but be prepared to experiment to find a "good" value!
	 */
	private final double dAlpha;
	
	/**
	 * 
	 * @param initialSolutionFitness The objective value of the initial solution.
	 */
	public GeometricCooling(double initialSolutionFitness) {
			
		this.dCurrentTemperature = initialSolutionFitness;
		
		// TODO You will need to find a suitable value for alpha 
		//      through prior knowledge and experimentation!
		this.dAlpha = 0.6999999999999;
	}

	@Override
	public double getCurrentTemperature() {
		
		return this.dCurrentTemperature;
	}

	/**
	 * DEFINITION: T_{i + 1} = alpha * T_i
	 */
	@Override
	public void advanceTemperature() {
		
		// TODO update the value of the current temperature, 'dCurrentTemperature'
		dCurrentTemperature *= dAlpha;
	}
	
	public String toString() {
		
		return "Geometric Cooling (α = " + this.dAlpha + ")";
	}
}
