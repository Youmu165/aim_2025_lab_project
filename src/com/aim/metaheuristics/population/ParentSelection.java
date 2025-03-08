package com.aim.metaheuristics.population;

import uk.ac.nott.cs.aim.domains.chesc2014_SAT.SAT;

import java.util.Random;

/**
 * Abstracting parent selection methods.
 *
 * @author Warren G Jackson
 * @since 27-02-2025
 */
public abstract class ParentSelection {

    protected final int iPopulationSize;

    protected final Random oRandom;

    protected final SAT oProblem;

    public ParentSelection(SAT oProblem, Random oRandom, int iPopulationSize) {

        this.oProblem = oProblem;
        this.oRandom = oRandom;
        this.iPopulationSize = iPopulationSize;
    }

    /**
     *
     * @return The index of a parent solution.
     */
    public abstract int parentSelection();
}
