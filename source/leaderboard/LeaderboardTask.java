package net.tslat.aoa3.leaderboard;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.sql.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class LeaderboardTask {
	private boolean cachedAutoCommitStatus = true;
	protected static final ConcurrentHashMap<Connection, HashMap<String, PreparedStatement>> preparedStatements = new ConcurrentHashMap<>();

	protected abstract void execute(final Connection connection);

	@Nullable
	protected PreparedStatement getPreparedStatement(Connection connection, String id) {
		HashMap<String, PreparedStatement> connectionStatements = preparedStatements.get(connection);

		return connectionStatements == null ? null : connectionStatements.get(id);
	}

	protected void savePreparedStatement(Connection connection, String id, PreparedStatement statement) {
		if (!preparedStatements.containsKey(connection))
			preparedStatements.put(connection, new HashMap<>());

		preparedStatements.get(connection).put(id, statement);
	}

	@Nullable
	protected PreparedStatement prepare(final Connection connection, String id, String sql) {
		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			savePreparedStatement(connection, id, statement);

			return statement;
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Failed to create prepared statement for leaderboard task.", ex);

			return null;
		}
	}

	protected void runFailSafeStatement(final Connection connection, String sql) {
		runFailSafeStatement(connection, sql, null, null);
	}

	protected void runFailSafeStatement(final Connection connection, String sql, String errorMsg) {
		runFailSafeStatement(connection, sql, errorMsg, null);
	}

	protected void runFailSafeStatement(final Connection connection, String sql, @Nullable Consumer<Statement> resultConsumer) {
		runFailSafeStatement(connection, sql, null, resultConsumer);
	}

	protected void runFailSafeStatement(final Connection connection, String sql, String errorMsg, @Nullable Consumer<Statement> resultConsumer) {
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

	protected void runStatement(final Connection connection, String sql) throws SQLException {
		runStatement(connection, sql, null);
	}

	protected void runStatement(final Connection connection, String sql, @Nullable Consumer<ResultSet> resultConsumer) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			if (resultConsumer == null) {
				statement.executeUpdate(sql);
			}
			else if (statement.execute(sql)) {
				resultConsumer.accept(statement.getResultSet());
			}
		}
	}

	protected void runPreparedStatement(final Connection connection, PreparedStatement statement, Pair<SQLType, Object>... args) {
		runPreparedStatement(connection, statement, null, args);
	}

	protected void runPreparedStatement(final Connection connection, PreparedStatement statement, @Nullable Consumer<ResultSet> resultConsumer, Pair<SQLType, Object>... args) {
		try {
			if (args.length > 0) {
				int i = 1;

				for (Pair<SQLType, Object> arg : args) {
					statement.setObject(i, arg.getSecond(), arg.getFirst());

					i++;
				}
			}

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

	protected static String idToTableName(ResourceLocation id) {
		return id.toString().replace(":", "_").toUpperCase(Locale.ROOT);
	}

	protected boolean requiresCompletion() {
		return false;
	}

	public void queue() {
		if (SkillsLeaderboard.isEnabled() || requiresCompletion())
			SkillsLeaderboard.queueTask(this);
	}

	protected void beginBatchUpdate(final Connection connection) {
		try {
			this.cachedAutoCommitStatus = connection.getAutoCommit();

			connection.setAutoCommit(false);
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.WARN, "Unable to prep connection for batch operations, this probably isn't good.", ex);
		}
	}

	protected void endBatchUpdate(final Connection connection) {
		try {
			connection.commit();
			connection.setAutoCommit(this.cachedAutoCommitStatus);
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.WARN, "Unable to reset connection after batch operations. This probably isn't good.", ex);
		}
	}
}
