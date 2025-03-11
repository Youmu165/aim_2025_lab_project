package com.aim.runners;

import java.util.Random;

import com.aim.TestFrameConfig;

import com.aim.heuristics.*;
import uk.ac.nott.cs.aim.satheuristics.SATHeuristic;

/**
 * Test frame/experimental configuration for lab 2.
 * 
 * @author Warren G. Jackson
 * @since 06/02/2025
 *
 */
public class Lab2ExercisesTestFrameConfig extends TestFrameConfig {

	/**
	 * The experimental seed, set as the date this lab was released.
	 */
	private static final long m_parentSeed = 7022025L;

	/*
	 * permitted instance ID's in the range [0, 11].
	 */
	private final int INSTANCE_ID =1;

	/*
	 * permitted run times (seconds) = 1, 5, 10, 20
	 */
	private final int RUN_TIME =10;

	/**
	 * 
	 */
	private final int TRIALS_PER_TEST = 31;

	/**
	 * 
	 */
	private static Lab2ExercisesTestFrameConfig oThis;

	/**
	 * 
	 */
	private Lab2ExercisesTestFrameConfig() {

		super(new long[] {m_parentSeed});
	}

	public synchronized static Lab2ExercisesTestFrameConfig getInstance() {

		if (oThis == null) {
			oThis = new Lab2ExercisesTestFrameConfig();
		}

		return oThis;
	}

	@Override
	public int getInstanceId() {
		return this.INSTANCE_ID;
	}

	@Override
	public int getRunTime() {
		return this.RUN_TIME;
	}

	@Override
	public String getMethodName() {
		return "Davis's Bit Hill Climbing and Steepest Descent";
	}

	@Override
	public String getConfigurationAsString() {

		return String.format("SAT instance #%d with a run time of %d nominal seconds.", INSTANCE_ID, RUN_TIME);
	}

	@Override
	public int getTotalRuns() {

		return TRIALS_PER_TEST;
	}

	/**
	 * This method should not be changed but is intended for personal use if you
	 * wish to try with other heuristics of your own making.
	 * 
	 * @param heuristicID 0 for the first heuristic, or 1 for the second.
	 * @param random      The random number generator used by all SATHeuristic's
	 * @return The corresponding SAT heuristic
	 */
	public static SATHeuristic getSATHeuristic(int heuristicID, Random random) {

		SATHeuristic heuristic = null;

		switch (heuristicID) {
		case 0:
			heuristic = new DavissBitHC(random);
			break;
			case 1:
				heuristic = new SteepestDescentHC(random);
				break;
		default:
			System.err.println("Request for more than 2 heuristics at a time!");
			System.exit(0);
		}

		return heuristic;
	}
}
