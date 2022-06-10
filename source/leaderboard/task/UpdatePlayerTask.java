package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.leaderboard.connection.InsertionConnection;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdatePlayerTask extends InsertionTask {
	private final String uuid;
	private final String name;
	private final AoASkill skill;
	private final short level;
	private int newTotal;

	public UpdatePlayerTask(ServerPlayerDataManager plData, AoASkill.Instance skill) {
		this.uuid = plData.player().getUUID().toString();
		this.name = plData.player().getName().getString();
		this.skill = skill.type();
		this.level = (short)skill.getLevel(true);

		for (AoASkill.Instance instance : plData.getSkills()) {
			newTotal += instance.getLevel(true);
		}
	}

	@Override
	public void execute(Connection connection, InsertionConnection leaderboardConnection) {
		try {
			leaderboardConnection.updatePlayerTotal(connection, uuid, name, newTotal);
			leaderboardConnection.updatePlayerLevel(connection, uuid, name, skill, level);
		}
		catch (SQLException ex) {
			Logging.logMessage(Level.WARN, "Unable to prepare statement for execution to update " + name + " in skills leaderboard. This is probably not a good sign", ex);
		}
	}

	@Override
	protected boolean requiresCompletion() {
		return true;
	}
}
