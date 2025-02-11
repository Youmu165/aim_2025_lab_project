package com.aim.heuristics;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.helperfunctions.ArrayMethods;
import uk.ac.nott.cs.aim.satheuristics.SATHeuristic;

import java.util.Random;

public class DavissBitHC extends SATHeuristic {
    public DavissBitHC(Random random) {
        super(random);
    }

    /**
     * DAVIS's BIT HILL CLIMBING LECTURE SLIDE PSEUDO-CODE
     *
     *  bestEval = evaluate(currentSolution)
     *  perm = createRandomPermutation(length(currentSolution))
     *   for (j = 0; j < length(currentSolution); j++) { // performs a single pass of the solution
     *   
     *       bitFlip(currentSolution, perm[j]) // flip the bit referenced to in perm's j^th index
     *       tempEval = evaluate(solution)
     *   
     *       if(tempEval < bestEval) {
     *           bestEval = tempEval // accept the bit flip
     *       } else {
     *           bitFlip(currentSolution, j) // otherwise reject the bit flip
     *       }
     *   }
     *
     * @param problem The problem to be solved.
     */
    public void applyHeuristic(SAT problem) {
        int currentSolutionIndex = 0;
        int numberOfVariables = problem.getNumberOfVariables();

        double bestEval = problem.getObjectiveFunctionValue(currentSolutionIndex);

        int[] tempList = new int[numberOfVariables];
        System.out.println("1. "+currentSolutionIndex +"\n" + "2. " + numberOfVariables + "\n");

        for (int j = 0; j < numberOfVariables; j++) {
            tempList[j] = j;
        }


        int[] perm = ArrayMethods.shuffle(tempList, random);

        for (int i = 0; i < numberOfVariables; i++) {
            problem.bitFlip(perm[i]);
            double tempEval = problem.getObjectiveFunctionValue(currentSolutionIndex);


            if (tempEval < bestEval) {
                bestEval = tempEval;
            }
            else
            {
                problem.bitFlip(perm[i]);
            }
        }




        // TODO


//        boolean[] currentSolution = problem.getSolution();
//        double bestEval = problem.evaluate(currentSolution);
        return;
    }



    @Override
    public String getHeuristicName() {

        return "DBHC";
    }
}
