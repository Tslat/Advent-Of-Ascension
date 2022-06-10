package net.tslat.aoa3.leaderboard.connection;

import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.leaderboard.LeaderboardTask;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public abstract class LeaderboardConnection extends Thread {
	private final LinkedBlockingQueue<LeaderboardTask<LeaderboardConnection>> queue;
	private final Properties properties;

	protected LeaderboardConnection(LinkedBlockingQueue<LeaderboardTask<LeaderboardConnection>> queue, Properties properties) {
		super();

		this.queue = queue;
		this.properties = properties;
	}

	@Override
	public void run() {
		try (Connection connection = DriverManager.getConnection(SkillsLeaderboard.getConnectionPath(), properties)) {
			prepareStatements(connection);

			do {
				try {
					LeaderboardTask<LeaderboardConnection> task = queue.take();

					task.execute(connection, this);
				}
				catch (InterruptedException ex) {
					Logging.logMessage(Level.ERROR, "Attempted to perform operation while thread is interrupted. This shouldn't happen. Skipping task.", ex);
				}
			}
			while (SkillsLeaderboard.isEnabled() || !queue.isEmpty());

			closeStatements();
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Error connecting to leaderboard database, dropping thread.", ex);
		}
	}

	protected abstract void prepareStatements(Connection connection) throws SQLException;
	protected abstract void closeStatements() throws SQLException ;

	public void runFailSafeStatement(final Connection connection, String sql) {
		runFailSafeStatement(connection, sql, null);
	}

	public void runFailSafeStatement(final Connection connection, String sql,  @Nullable Consumer<Statement> resultConsumer) {
		try (Statement statement = connection.createStatement()) {
			if (resultConsumer == null) {
				statement.executeUpdate(sql);
			}
			else if (statement.execute(sql)) {
				resultConsumer.accept(statement);
			}
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.WARN, "Error encountered while executing SQL statement");
		}
	}

	public void runStatement(final Connection connection, String sql) throws SQLException {
		runStatement(connection, sql, null);
	}

	public void runStatement(final Connection connection, String sql, @Nullable Consumer<ResultSet> resultConsumer) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			if (resultConsumer == null) {
				statement.executeUpdate(sql);
			}
			else if (statement.execute(sql)) {
				resultConsumer.accept(statement.getResultSet());
			}
		}
	}

	protected void runPreparedStatement(final Connection connection, PreparedStatement statement) {
		runPreparedStatement(connection, statement, null);
	}

	protected void runPreparedStatement(final Connection connection, PreparedStatement statement, @Nullable Consumer<ResultSet> resultConsumer) {
		try {
			if (resultConsumer == null) {
				statement.executeUpdate();
			}
			else {
				resultConsumer.accept(statement.executeQuery());
			}
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.WARN, "Failed to execute prepated statement for skills leaderboard.", ex);
		}
	}
}
