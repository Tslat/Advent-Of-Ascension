package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.leaderboard.LeaderboardTask;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.player.skill.AoASkill;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.SQLException;

public class InitializeLeaderboardTask extends LeaderboardTask {
	@Override
	protected void execute(Connection connection) {
		try {
			beginBatchUpdate(connection);

			if (AoAConfig.SERVER.dontCacheDatabase.get())
				runStatement(connection, "SET DATABASE DEFAULT TABLE TYPE TEXT;");

			runStatement(connection, "CREATE TABLE IF NOT EXISTS Totals (Uuid UUID, Username VARCHCHAR(16), Total INT, LastUpdate DATE)");

			if (AoAConfig.SERVER.dontCacheDatabase.get())
				runStatement(connection, "SET TABLE Totals TYPE TEXT");

			for (AoASkill skill : AoARegistries.AOA_SKILLS.getAllRegisteredObjects()) {
				String tableName = idToTableName(skill.getRegistryName());

				runStatement(connection, "CREATE TABLE IF NOT EXISTS " + tableName + " (Uuid UUID, Username VARCHCHAR(16), Level SMALLINT, LastUpdate DATE)");

				if (AoAConfig.SERVER.dontCacheDatabase.get())
					runStatement(connection, "SET TABLE " + tableName + " TYPE TEXT");
			}

			endBatchUpdate(connection);
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.ERROR, "Error while initializing leaderboard tables. Shutting down leaderboard.", ex);

			SkillsLeaderboard.shutdown();
		}
	}
}
