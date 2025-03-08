package com.aim.runners;

import com.aim.TestFrameConfig;

/**
 * Test frame/experimental configuration for lab 5.
 * 
 * @author Warren G. Jackson
 * @since 31/01/2021
 * @version 1.0.0
 *
 */
public class Lab5ExercisesTestFrameConfig extends TestFrameConfig {

	/*
	 * permitted values = { Mode.GA, Mode.MA } Mode.GA = genetic algorithm ( local
	 * search <- NOOP ) Mode.MA = memetic algorithm ( local search <- DBHC_IE )
	 * 
	 * You are encouraged to play around with this setting in your own time :)
	 */
	protected final Mode MODE = Mode.MA;

	protected final Selection P1_SELECTION = Selection.TOURNAMENT;

	protected final Selection P2_SELECTION = Selection.TOURNAMENT;

	protected final Replacement REPLACEMENT = Replacement.TRANS_GENERATIONAL;

	protected final Crossover CROSSOVER = Crossover.UXO;

	/*
	 * permitted total runs = 31
	 */
	protected final int TOTAL_RUNS = 31;

	/*
	 * permitted instance ID's = 5
	 */
	protected final int INSTANCE_ID = 5;

	/*
	 * permitted population sizes = even integers \in [4, 32]; default = 8
	 */
	public static final int POP_SIZE =4;

	public static final int TOURNAMENT_SIZE = 3;

	private static final long m_parentSeed = 28022025L;
	
	/**
	 * 
	 */
	private static Lab5ExercisesTestFrameConfig oThis;

	/**
	 * 
	 */
	private Lab5ExercisesTestFrameConfig() {

		super(new long[] {m_parentSeed});
	}

	/**
	 * 
	 * @return
	 */
	public synchronized static Lab5ExercisesTestFrameConfig getInstance() {

		if (oThis == null) {
			oThis = new Lab5ExercisesTestFrameConfig();
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
		return "Memetic Algorithm";
	}

	public int getPopulationSize() {
		return POP_SIZE;
	}

	@Override
	public String getConfigurationAsString() {
		return getMethodName() + " ( " + MODE.toString() + "_Mode ) given " + MODE.getGenerations() 
		+ " generations for solving instance ID " + getInstanceId()  + " over " + getTotalRuns() + " runs"
		+ (" with Population size = " + getPopulationSize() + ".");
	}

	/**
	 * Execution mode for the Memetic Algorithm.
	 * 
	 * @author Warren G. Jackson
	 * @since 27/02/2025
	 *
	 * Generations in GA and MA mode set to take approximately an equivalent amount of time.
	 */
	public enum Mode {

		GA(20000 / POP_SIZE), MA(500 / POP_SIZE);

		private final int generations;

		Mode(int generations) {
			this.generations = generations;
		}

		public int getGenerations() {
			return generations;
		}
	}

	public enum Selection {

		TOURNAMENT, RANDOM, FITTEST;
	}

	public enum Replacement {

		BASIC, TRANS_GENERATIONAL;
	}

	public enum Crossover {

		ONE_PTX, UXO;
	}
}
