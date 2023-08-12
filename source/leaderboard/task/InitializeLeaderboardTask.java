package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.advent.Logging;
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
			leaderboardConnection.runStatement(connection, "CREATE TABLE IF NOT EXISTS Totals (Uuid CHAR(36) NOT NULL, Username NVARCHAR(20) NOT NULL, Total INT NOT NULL, LastUpdate DATE, PRIMARY KEY (Uuid))");

			for (AoASkill skill : AoARegistries.AOA_SKILLS.getAllRegisteredObjects()) {
				String tableName = idToTableName(AoARegistries.AOA_SKILLS.getId(skill));

				leaderboardConnection.runStatement(connection, "CREATE TABLE IF NOT EXISTS " + tableName + " (Uuid CHAR(36) NOT NULL, Username NVARCHAR(20) NOT NULL, Level SMALLINT NOT NULL, LastUpdate DATE, PRIMARY KEY (Uuid))");
			}

			connection.commit();
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Error while initializing leaderboard tables. Shutting down leaderboard.", ex);

			SkillsLeaderboard.shutdown();
		}

		endBatchUpdate(connection);
	}
}
