package net.tslat.aoa3.leaderboard;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelResource;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.leaderboard.connection.InsertionConnection;
import net.tslat.aoa3.leaderboard.connection.RetrievalConnection;
import net.tslat.aoa3.leaderboard.task.InitializeLeaderboardTask;
import net.tslat.aoa3.leaderboard.task.KillTask;
import net.tslat.aoa3.util.ObjectUtil;
import org.apache.logging.log4j.Level;
import org.hsqldb.jdbc.JDBCDriver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SkillsLeaderboard {
	private static final LevelResource DATA_DIR = new LevelResource("data");
	private static LinkedBlockingQueue<LeaderboardTask<InsertionConnection>> updateTaskQueue;
	private static LinkedBlockingQueue<LeaderboardTask<RetrievalConnection>> retrievalTaskQueue;

	private static InsertionConnection[] updateThreads;
	private static RetrievalConnection[] retrievalThreads;

	private static final AtomicBoolean enabled = new AtomicBoolean(true);
	private static String dataPath;

	public static String getConnectionPath() {
		return "jdbc:hsqldb:file:" + dataPath + "\\skills";
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

	public static void init(MinecraftServer server) {
		enabled.set(AoAConfigs.SERVER.skillsLeaderboardEnabled.get());

		if (!isEnabled())
			return;

		try {
			dataPath = ObjectUtil.getOrCreateDirectory(server.getWorldPath(DATA_DIR), "aoa_leaderboards").toAbsolutePath().toString();
		}
		catch (Exception ex) {
			Logging.logMessage(Level.ERROR, "Unable to create or access directory for database storage. Disabling leaderboard.", ex);

			shutdown();
			return;
		}

		updateTaskQueue = new LinkedBlockingQueue<>();
		retrievalTaskQueue = new LinkedBlockingQueue<>();

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
		int updateThreadCount = (int)Math.ceil(maxThreads / 1.5f);
		int retrievalThreadCount = maxThreads - updateThreadCount;
		Properties connectionProperties = new Properties();
		updateThreads = new InsertionConnection[updateThreadCount];
		retrievalThreads = new RetrievalConnection[retrievalThreadCount];

		connectionProperties.put("user", AoAConfigs.SERVER.databaseUsername.get());
		connectionProperties.put("password", AoAConfigs.SERVER.databasePassword.get());
		connectionProperties.put("shutdown", "true");
		connectionProperties.put("hsqldb.log_size", "5");

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
				while (thread.isAlive()) {
					queueUpdateTask(new KillTask());

					try {
						thread.join(1);
					}
					catch (InterruptedException ignored) {}
				}
			}
		}

		if (retrievalThreads != null) {
			for (Thread thread : retrievalThreads) {

				while (thread.isAlive()) {
					queueRetrievalTask(new KillTask());

					try {
						thread.join(1);
					}
					catch (InterruptedException ignored) {}
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
