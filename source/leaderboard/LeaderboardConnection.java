package net.tslat.aoa3.leaderboard;

import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

class LeaderboardConnection extends Thread {
	private final LinkedBlockingQueue<LeaderboardTask> queue;
	private final Properties properties;

	protected LeaderboardConnection(LinkedBlockingQueue<LeaderboardTask> queue, Properties properties) {
		super();

		this.queue = queue;
		this.properties = properties;
	}

	@Override
	public void run() {
		try (Connection connection = DriverManager.getConnection(SkillsLeaderboard.getConnectionPath(), properties)) {
			do {
				try {
					LeaderboardTask task = queue.take();

					task.execute(connection);
				}
				catch (InterruptedException ex) {
					Logging.logMessage(Level.ERROR, "Attempted to perform operation while thread is interrupted. This shouldn't happen. Skipping task.", ex);
				}
			}
			while (!SkillsLeaderboard.getTaskQueue().isEmpty() || SkillsLeaderboard.isEnabled());
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Error connecting to leaderboard database, dropping thread.", ex);
		}
	}
}
