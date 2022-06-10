package net.tslat.aoa3.leaderboard;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.leaderboard.connection.LeaderboardConnection;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public abstract class LeaderboardTask<T extends LeaderboardConnection> {
	private boolean cachedAutoCommitStatus = true;

	public abstract void execute(final Connection connection, T leaderboardConnection);

	public static String idToTableName(ResourceLocation id) {
		return id.toString().replace(":", "_").toUpperCase(Locale.ROOT);
	}

	protected boolean requiresCompletion() {
		return false;
	}

	public abstract void queue();

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
