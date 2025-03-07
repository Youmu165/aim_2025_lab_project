package com.aim.runners;

import com.aim.TestFrameConfig;

public class Lab6ExercisesTestFrameConfig extends TestFrameConfig {


	protected final int TOURNAMENT_SIZE = 3;

	/*
	 * permitted total runs = 31
	 */
	protected final int TOTAL_RUNS = 31;

	/*
	 * permitted instance ID's = 1, (5 - default), 9
	 */
	protected final int INSTANCE_ID = 5;

	/*
	 * permitted run times (seconds) = infinite, use generations instead.
	 */
	protected final int RUN_TIME = Integer.MAX_VALUE;

	/*
	 * permitted number of generations = 100 (same as the standard MA from last lab).
	 */
	protected final int MAX_GENERATIONS = 100;

	/*
	 * permitted population size = 8
	 */
	protected final int POP_SIZE = 8;

	/*
	 * The number of memes in each memeplex
	 */
	protected final int MEMES = 3;

	/*
	 * The number of options that each meme can have.
	 * 
	 * Meme [0] represents intensity of mutation
	 * Meme [1] represents choice of hill climbing operator
	 * Meme [2] represents depth of search as number of invocations of hill climbing
	 */
	protected final int[] OPTIONS_PER_MEME = new int[] { 5, 4, 5 };

	/*
	 * permitted innovation rates, 0.0 <= INNOVATION_RATE <= 1.0 
	 */
	protected final double INNOVATION_RATE = 0.0;

	/**
	 * The experimental seed, set as the date of the in-person lab 5.
	 */
	private static final long m_parentSeed = 28022025L;

	private static Lab6ExercisesTestFrameConfig oThis;

	private Lab6ExercisesTestFrameConfig() {
		
		super(new long[] { m_parentSeed });
	}

	public synchronized static Lab6ExercisesTestFrameConfig getInstance() {
		
		if (oThis == null) {
			
			oThis = new Lab6ExercisesTestFrameConfig();
		}

		return oThis;
	}

	@Override
	public int getTotalRuns() {
		return this.TOTAL_RUNS;
	}

	@Override
	public int getInstanceId() {
		return this.INSTANCE_ID;
	}

	@Override
	public int getRunTime() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getMethodName() {
		return "Multimeme Memetic Algorithm";
	}

	public int getPopulationSize() {
		return this.POP_SIZE;
	}

	public int getMemeCount() {
		return this.MEMES;
	}

	public int[] getOptionsPerMeme() {
		return this.OPTIONS_PER_MEME;
	}

	@Override
	public String getConfigurationAsString() {
		return "Population size = " + getPopulationSize();
	}

	public String getBoxPlotTitle() {

		String config = getConfigurationAsString();
		return "Results for " + getMethodName() + " given " + MAX_GENERATIONS + " generations for solving instance ID "
				+ getInstanceId() + " over " + getTotalRuns() + " runs"
				+ (config == null ? "." : (" with " + config + "."));
	}
}
