package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.leaderboard.connection.InsertionConnection;
import net.tslat.aoa3.player.skill.AoASkill;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.SQLException;

public class InitializeLeaderboardTask extends InsertionTask {
	@Override
	public void execute(Connection connection, InsertionConnection leaderboardConnection) {
		beginBatchUpdate(connection);

		try {
			if (AoAConfigs.SERVER.dontCacheDatabase.get())
				leaderboardConnection.runStatement(connection, "SET DATABASE DEFAULT TABLE TYPE TEXT;");

			leaderboardConnection.runStatement(connection, "CREATE TABLE IF NOT EXISTS Totals (Uuid NCHAR(36), Username VARCHCHAR(20), Total INT, LastUpdate DATE)");

			if (AoAConfigs.SERVER.dontCacheDatabase.get())
				leaderboardConnection.runStatement(connection, "SET TABLE Totals TYPE TEXT");

			for (AoASkill skill : AoARegistries.AOA_SKILLS.getAllRegisteredObjects()) {
				String tableName = idToTableName(AoARegistries.AOA_SKILLS.getId(skill));

				leaderboardConnection.runStatement(connection, "CREATE TABLE IF NOT EXISTS " + tableName + " (Uuid NCHAR(36), Username VARCHCHAR(20), Level SMALLINT, LastUpdate DATE)");

				if (AoAConfigs.SERVER.dontCacheDatabase.get())
					leaderboardConnection.runStatement(connection, "SET TABLE " + tableName + " TYPE TEXT");
			}
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Error while initializing leaderboard tables. Shutting down leaderboard.", ex);

			SkillsLeaderboard.shutdown();
		}

		endBatchUpdate(connection);
	}
}
