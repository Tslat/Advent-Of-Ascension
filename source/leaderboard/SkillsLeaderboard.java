package net.tslat.aoa3.leaderboard;

import net.minecraftforge.fml.loading.FMLPaths;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.leaderboard.connection.InsertionConnection;
import net.tslat.aoa3.leaderboard.connection.RetrievalConnection;
import net.tslat.aoa3.leaderboard.task.InitializeLeaderboardTask;
import net.tslat.aoa3.util.ObjectUtil;
import org.apache.logging.log4j.Level;
import org.hsqldb.jdbc.JDBCDriver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SkillsLeaderboard {
	private static LinkedBlockingQueue<LeaderboardTask<InsertionConnection>> updateTaskQueue;
	private static LinkedBlockingQueue<LeaderboardTask<RetrievalConnection>> retrievalTaskQueue;

	private static InsertionConnection[] updateThreads;
	private static RetrievalConnection[] retrievalThreads;

	private static final AtomicBoolean enabled = new AtomicBoolean(true);
	private static String dataPath;

	public static String getConnectionPath() {
		return "jdbc:hsqldb:file:" + dataPath + "/skills";
	}

	public static LinkedBlockingQueue<LeaderboardTask<InsertionConnection>> getUpdateTaskQueue() {
		return updateTaskQueue;
	}

	public static LinkedBlockingQueue<LeaderboardTask<RetrievalConnection>> getRetrievalTaskQueue() {
		return retrievalTaskQueue;
	}

	public static boolean isEnabled() {
		return enabled.get();
	}

	public static void init() {
		enabled.set(AoAConfigs.SERVER.skillsLeaderboardEnabled.get());

		if (!isEnabled())
			return;

		try {
			dataPath = ObjectUtil.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(AdventOfAscension.MOD_ID), AdventOfAscension.MOD_ID).toAbsolutePath().toString();
		}
		catch (Exception ex) {
			Logging.logMessage(Level.ERROR, "Unable to create or access directory for database storage. Disabling leaderboard.", ex);

			shutdown();
			return;
		}

		updateTaskQueue = new LinkedBlockingQueue<>();

		try {
			DriverManager.registerDriver(new JDBCDriver());
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Unable to attach Skills Leaderboard database driver, disabling leaderboard.", ex);

			shutdown();

			return;
		}

		Logging.logMessage(Level.INFO, "Starting threads and opening connections for skills database..");

		int maxThreads = AoAConfigs.SERVER.maxLeaderboardThreads.get();
		int updateThreadCount = maxThreads / 2;
		int retrievalThreadCount = maxThreads - updateThreadCount;
		Properties connectionProperties = new Properties();
		updateThreads = new InsertionConnection[updateThreadCount];
		retrievalThreads = new RetrievalConnection[retrievalThreadCount];

		connectionProperties.put("user", AoAConfigs.SERVER.databaseUsername.get());
		connectionProperties.put("password", AoAConfigs.SERVER.databasePassword.get());
		connectionProperties.put("shutdown", true);
		connectionProperties.put("hsqldb.log_size", 5);

		for (int i = 0; i < updateThreadCount; i++) {
			updateThreads[i] = new InsertionConnection(updateTaskQueue, connectionProperties);
			updateThreads[i].start();
		}

		for (int i = 0; i < retrievalThreadCount; i++) {
			retrievalThreads[i] = new RetrievalConnection(retrievalTaskQueue, connectionProperties);
			retrievalThreads[i].start();
		}

		new InitializeLeaderboardTask().queue();
	}

	public static void shutdown() {
		enabled.set(false);

		if (updateThreads != null) {
			for (Thread thread : updateThreads) {
				try {
					if (thread.isAlive())
						thread.join();
				}
				catch (InterruptedException ex) {
					Logging.logMessage(Level.ERROR, "Tried to stop already interrupted consumer thread, this is awkward..", ex);
				}
			}
		}

		if (retrievalThreads != null) {
			for (Thread thread : retrievalThreads) {
				try {
					if (thread.isAlive())
						thread.join();
				}
				catch (InterruptedException ex) {
					Logging.logMessage(Level.ERROR, "Tried to stop already interrupted consumer thread, this is awkward..", ex);
				}
			}
		}

	}

	public static void queueUpdateTask(LeaderboardTask<InsertionConnection> task) {
		getUpdateTaskQueue().add(task);
	}

	public static void queueRetrievalTask(LeaderboardTask<RetrievalConnection> task) {
		getRetrievalTaskQueue().add(task);
	}
}
