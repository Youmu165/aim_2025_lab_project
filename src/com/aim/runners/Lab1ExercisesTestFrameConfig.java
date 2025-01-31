package com.aim.runners;

import com.aim.TestFrameConfig;

/**
 * 
 * @author Warren G. Jackson
 *
 */
public class Lab1ExercisesTestFrameConfig extends TestFrameConfig {

	/**
	 * The experimental seed, set as the date of the in-person lab.
	 */
	private static final long[] m_seeds = { 31012025, 31012025, 31012025 };

	/*
	 * permitted instance ID's = 1
	 */
	private final int INSTANCE_ID = 1;

	/*
	 * permitted run times (nominal seconds) = 10
	 */
	private final int RUN_TIME = 10;

	/**
	 * Sets the number of trials equal to the number of experimental seeds.
	 */
	private final int TRIALS_PER_TEST = m_seeds.length;

	/**
	 * Singleton instance.
	 */
	private static Lab1ExercisesTestFrameConfig oThis;

	/**
	 * 
	 */
	private Lab1ExercisesTestFrameConfig() {

		super(m_seeds);
	}

	/**
	 * 
	 * @return The Singleton instantiation of this class.
	 */
	public static synchronized Lab1ExercisesTestFrameConfig getConfig() {

		if (oThis == null) {
			oThis = new Lab1ExercisesTestFrameConfig();
		}

		return oThis;
	}

	@Override
	public int getInstanceId() {
		return INSTANCE_ID;
	}

	@Override
	public int getRunTime() {
		return RUN_TIME;
	}

	@Override
	public String getMethodName() {
		return "Random Walk";
	}

	@Override
	public String getConfigurationAsString() {
		
		return String.format("SAT instance #%d with a run time of %d nominal seconds.", INSTANCE_ID, RUN_TIME);
	}

	@Override
	public int getTotalRuns() {
		return TRIALS_PER_TEST;
	}

}
