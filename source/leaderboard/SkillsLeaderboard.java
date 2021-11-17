package net.tslat.aoa3.leaderboard;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.leaderboard.task.InitializeLeaderboardTask;
import org.apache.logging.log4j.Level;
import org.hsqldb.jdbc.JDBCDriver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SkillsLeaderboard {
	private static LinkedBlockingQueue<LeaderboardTask> taskQueue;
	private static Properties connectionProperties = new Properties();

	private static Thread[] threads;

	private static final AtomicBoolean enabled = new AtomicBoolean(true);
	private static String dataPath;

	public static String getConnectionPath() {
		return "jdbc:hsqldb:file:" + dataPath + "/skills";
	}

	public static LinkedBlockingQueue<LeaderboardTask> getTaskQueue() {
		return taskQueue;
	}

	public static boolean isEnabled() {
		return enabled.get();
	}

	public static void init() {
		enabled.set(AoAConfig.SERVER.skillsLeaderboardEnabled.get());

		if (!enabled.get())
			return;

		try {
			dataPath = FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(AdventOfAscension.MOD_ID), AdventOfAscension.MOD_ID).toAbsolutePath().toString();
		}
		catch (Exception ex) {
			Logging.logMessage(Level.ERROR, "Unable to create or access directory for database storage. Disabling leaderboard.", ex);

			shutdown();
			return;
		}

		taskQueue = new LinkedBlockingQueue<LeaderboardTask>();

		try {
			DriverManager.registerDriver(new JDBCDriver());
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Unable to attach Skills Leaderboard database driver, disabling leaderboard.", ex);

			shutdown();

			return;
		}

		Logging.logMessage(Level.INFO, "Starting threads and opening connections for skills database..");

		connectionProperties = new Properties();

		connectionProperties.put("user", AoAConfig.SERVER.databaseUsername.get());
		connectionProperties.put("password", AoAConfig.SERVER.databasePassword.get());
		connectionProperties.put("shutdown", true);
		connectionProperties.put("hsqldb.log_size", 5);

		for (int i = 0; i < AoAConfig.SERVER.maxLeaderboardThreads.get(); i++) {
			threads[i] = new LeaderboardConnection(taskQueue, connectionProperties);
			threads[i].start();
		}

		new InitializeLeaderboardTask().queue();
	}

	public static void shutdown() {
		enabled.set(false);

		if (threads != null && threads.length > 0) {
			try {
				for (Thread thread : threads) {
					if (thread.isAlive())
						thread.join();
				}
			}
			catch (InterruptedException ex) {
				Logging.logMessage(Level.ERROR, "Tried to stop already interrupted consumer thread, this is awkward..", ex);
			}
		}
	}

	protected static void queueTask(LeaderboardTask task) {
		getTaskQueue().add(task);
	}
}
