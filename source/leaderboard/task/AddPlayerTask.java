package net.tslat.aoa3.leaderboard.task;

import com.mojang.datafixers.util.Pair;
import net.tslat.aoa3.leaderboard.LeaderboardTask;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

import java.sql.Connection;
import java.util.ArrayList;

public class AddPlayerTask extends LeaderboardTask {
	private final String uuid;
	private final String name;
	private final ArrayList<Pair<AoASkill, Integer>> skills = new ArrayList<Pair<AoASkill, Integer>>();
	private int totalLevel = 0;

	public AddPlayerTask(ServerPlayerDataManager plData) {
		this.uuid = plData.player().getUUID().toString();
		this.name = plData.player().getName().getString();

		for (AoASkill.Instance skill : plData.getSkills()) {
			int level = skill.getLevel(true);
			totalLevel += level;

			this.skills.add(Pair.of(skill.type(), level));
		}
	}

	@Override
	protected void execute(Connection connection) {
		beginBatchUpdate(connection);

		runFailSafeStatement(connection,
				"IF EXISTS (SELECT 1 FROM Totals WHERE Uuid='" + uuid + "') " +
						"BEGIN " +
						"UPDATE Totals SET Total=" + totalLevel + ", LastUpdate=CURRENT_DATE() WHERE Uuid='" + uuid + "' " +
						"END " +
						"ELSE " +
						"BEGIN " +
						"INSERT INTO Totals (Uuid, Username, Total, LastUpdate) VALUES ('" + uuid + "', '" + name + "', " + totalLevel + ", CURRENT_DATE()) " +
						"END",
				"Error executing database update for " + this.name + ", regretfully skipping.");

		for (Pair<AoASkill, Integer> skill : skills) {
			String tableName = idToTableName(skill.getFirst().getRegistryName());

			runFailSafeStatement(connection,
					"IF EXISTS (SELECT 1 FROM " + tableName + " WHERE Uuid='" + uuid + "') " +
							"BEGIN " +
							"UPDATE " + tableName + " SET Level=" + skill.getSecond() + ", LastUpdate=CURRENT_DATE() WHERE Uuid='" + uuid + "' " +
							"END " +
							"ELSE " +
							"BEGIN " +
							"INSERT INTO " + tableName + " (Uuid, Username, Level, LastUpdate) VALUES ('" + uuid + "', '" + name + "', " + skill.getSecond() + ", CURRENT_DATE()) " +
							"END",
					"Error executing database update for " + this.name + ", regretfully skipping.");
		}

		endBatchUpdate(connection);
	}
}
