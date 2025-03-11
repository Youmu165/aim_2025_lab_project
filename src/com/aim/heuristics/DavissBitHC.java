package com.aim.heuristics;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;
import uk.ac.nott.cs.aim.helperfunctions.ArrayMethods;
import uk.ac.nott.cs.aim.satheuristics.SATHeuristic;

import java.util.Random;
import java.util.stream.IntStream;

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
        int currentSolutionIndex = CURRENT_SOLUTION_INDEX;
        double bestEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
        int[] variableIndices = IntStream.range(0, problem.getNumberOfVariables()).toArray();
        int[] perm = ArrayMethods.shuffle(variableIndices, random);

        for (int j = 0; j < problem.getNumberOfVariables(); j++) {
            problem.bitFlip(perm[j], currentSolutionIndex);
            double tempEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
           // bestEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
            if(tempEval <= bestEval) {
                bestEval = tempEval;
            }
            else
            {
                problem.bitFlip(perm[j], currentSolutionIndex);
            }
        }
////        int currentSolutionIndex = 0;
////        int numberOfVariables = problem.getNumberOfVariables();
////
////        double bestEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
////
////        int[] tempList = new int[numberOfVariables];
//////        System.out.println("1. "+currentSolutionIndex +"\n" + "2. " + numberOfVariables + "\n");
////
////        for (int j = 0; j < numberOfVariables; j++) {
////            tempList[j] = j;
////        }
////
////
////        int[] perm = ArrayMethods.shuffle(tempList, random);
////
////        for (int i = 0; i < numberOfVariables; i++) {
////            problem.bitFlip(perm[i]);
////            double tempEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
//////            bestEval = problem.getObjectiveFunctionValue(currentSolutionIndex);
////
////
////            if (tempEval <= bestEval) {
////                bestEval = tempEval;
////            }
////            else
////            {
////                problem.bitFlip(perm[i],currentSolutionIndex);
////            }
////        }
//
//
//
//
//        // TODO
//
//
////        boolean[] currentSolution = problem.getSolution();
////        double bestEval = problem.evaluate(currentSolution);
//        return;
        // shuffle and store the array to create a permutation. NEED to use the RNG from the SATHeuristic class ('random')
//        int iBitStringLength = problem.getNumberOfVariables();
//        int[] aiPerm = IntStream.range(0,iBitStringLength).toArray();
//        aiPerm = ArrayMethods.shuffle(aiPerm, random);
//
//        // initialise the best evaluation to the objective value of the current solution
//        double dBestEval = problem.getObjectiveFunctionValue(CURRENT_SOLUTION_INDEX);
//        double dTempEval;
//
//        // need to try each bit exactly once
//        for(int iBitIndex = 0; iBitIndex < iBitStringLength; iBitIndex++) {
//
//            // flip the bits in "permutation order" for the solution in the CURRENT_SOLUTION_INDEX
//            problem.bitFlip(aiPerm[iBitIndex], CURRENT_SOLUTION_INDEX);
//
//            // get the objective value of the new solution
//            dTempEval = problem.getObjectiveFunctionValue(CURRENT_SOLUTION_INDEX);
//
//            // if the objective value of the new solution is not worse, then keep the bit flipped
//            if(dTempEval <= dBestEval) {
//
//                dBestEval = dTempEval;
//
//            } else { // else flip the bit back to its original truth value
//
//                problem.bitFlip(aiPerm[iBitIndex], CURRENT_SOLUTION_INDEX);
//            }
//        }
    }



    @Override
    public String getHeuristicName() {

        return "DBHC";
    }
}
