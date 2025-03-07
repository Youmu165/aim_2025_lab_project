package com.aim.metaheuristics.population.multimeme;

public interface MemeplexInheritanceMethod {
	
	/**
	 * 
	 * @param parent1Index
	 * @param parent2Index
	 * @param child1Index
	 * @param child2Index
	 */
    void performMemeticInheritance(int parent1Index, int parent2Index, int child1Index, int child2Index);
}
