package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.leaderboard.LeaderboardTask;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

import java.sql.Connection;

public class UpdatePlayerTask extends LeaderboardTask {
	private final String uuid;
	private final String name;
	private final String tableName;
	private final int level;
	private int newTotal;

	public UpdatePlayerTask(ServerPlayerDataManager plData, AoASkill.Instance skill) {
		this.uuid = plData.player().getUUID().toString();
		this.name = plData.player().getName().getString();
		this.tableName = idToTableName(skill.type().getRegistryName());
		this.level = skill.getLevel(true);

		for (AoASkill.Instance instance : plData.getSkills()) {
			newTotal += skill.getLevel(true);
		}
	}

	@Override
	protected void execute(Connection connection) {
		runFailSafeStatement(connection,
				"IF EXISTS (SELECT 1 FROM Totals WHERE Uuid='" + uuid + "') " +
						"BEGIN " +
						"UPDATE Totals SET Total=" + newTotal + ", LastUpdate=CURRENT_DATE() WHERE Uuid='" + uuid + "' " +
						"END " +
						"ELSE " +
						"BEGIN " +
						"INSERT INTO Totals (Uuid, Username, Total, LastUpdate) VALUES ('" + uuid + "', '" + name + "', " + newTotal + ", CURRENT_DATE()) " +
						"END",
		"Error executing database update for " + this.name + ", regretfully skipping.");

		runFailSafeStatement(connection,
				"IF EXISTS (SELECT 1 FROM " + tableName + " WHERE Uuid='" + uuid + "') " +
						"BEGIN " +
						"UPDATE " + tableName + " SET Level=" + level + ", LastUpdate=CURRENT_DATE() WHERE Uuid='" + uuid + "' " +
						"END " +
						"ELSE " +
						"BEGIN " +
						"INSERT INTO " + tableName + " (Uuid, Username, Level, LastUpdate) VALUES ('" + uuid + "', '" + name + "', " + level + ", CURRENT_DATE()) " +
						"END",
		"Error executing database update for " + this.name + ", regretfully skipping.");
	}

	@Override
	protected boolean requiresCompletion() {
		return true;
	}
}
