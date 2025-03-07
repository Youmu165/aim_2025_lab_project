package com.aim.metaheuristics.population.multimeme;

import java.util.Random;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.Meme;
import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;

/**
 * @author Warren G Jackson
 * @since 04/03/2025
 */
public class SimpleInheritanceMethod implements MemeplexInheritanceMethod {

	private final SAT problem;
	private final Random rng;
	
	public SimpleInheritanceMethod(SAT problem, Random rng) {
		
		this.problem = problem;
		this.rng = rng;
	}
	
	/**
	 * Copies the memetic material of the parents to the children
	 * using the Simple Inheritance Method.
	 * 
	 * @param parent1Index The solution memory index of parent 1.
	 * @param parent2Index The solution memory index of parent 2.
	 * @param child1Index The solution memory index of child 1.
	 * @param child2Index The solution memory index of child 2.
	 * 
	 * Simple Inheritance Method PSEUDOCODE:
	 * 
	 * INPUT: parent1, parent2, child1, child2
	 * IF f( parent1 ) == f( parent2 ) THEN
	 * 
	 *     inherit = random \in { parent1, parent2 }
	 *     child1.memeplex <- inherit.memeplex;
	 *     child2.memeplex <- inherit.memeplex;
	 *     
	 * ELSEIF f( parent1 ) < f( parent2 ) THEN
	 * 
	 *     child1.memeplex <- parent1.memeplex;
	 *     child2.memeplex <- parent1.memeplex;
	 *     
	 * ELSE // parent2 is best
	 * 
	 *     child1.memeplex = parent2.memeplex;
	 *     child2.memeplex = parent2.memeplex;
	 *     
	 * ENDIF
	 * return;
	 */
	@Override
	public void performMemeticInheritance(int parent1Index, int parent2Index, int child1Index, int child2Index) {
		
		// TODO - implement the simple inheritance method as above.
	}
}
