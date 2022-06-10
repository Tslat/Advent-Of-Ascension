package net.tslat.aoa3.leaderboard.task;

import com.mojang.datafixers.util.Pair;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.leaderboard.connection.InsertionConnection;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPlayerTask extends InsertionTask {
	private final String uuid;
	private final String name;
	private final ArrayList<Pair<AoASkill, Short>> skills = new ArrayList<>();
	private int totalLevel = 0;

	public AddPlayerTask(ServerPlayerDataManager plData) {
		this.uuid = plData.player().getUUID().toString();
		this.name = plData.player().getName().getString();

		for (AoASkill.Instance skill : plData.getSkills()) {
			int level = skill.getLevel(true);
			totalLevel += level;

			this.skills.add(Pair.of(skill.type(), (short)level));
		}
	}

	@Override
	public void execute(Connection connection, InsertionConnection leaderboardConnection) {
		beginBatchUpdate(connection);

		try {
			leaderboardConnection.updatePlayerTotal(connection, uuid, name, totalLevel);

			for (Pair<AoASkill, Short> skill : skills) {
				leaderboardConnection.updatePlayerLevel(connection, uuid, name, skill.getFirst(), skill.getSecond());
			}

		}
		catch (SQLException ex) {
			Logging.logMessage(Level.WARN, "Unable to prepare statement for execution to add " + name + " to the skills leaderboard. This is probably not a good sign", ex);
		}

		endBatchUpdate(connection);
	}
}
