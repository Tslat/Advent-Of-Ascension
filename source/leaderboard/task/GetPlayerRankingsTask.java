package net.tslat.aoa3.leaderboard.task;

import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.leaderboard.connection.RetrievalConnection;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;

public class GetPlayerRankingsTask extends RetrievalTask {
	private final AoASkill skill;
	private final int page;
	private final ServerPlayer requestor;

	public GetPlayerRankingsTask(@Nullable AoASkill skill, int page, ServerPlayer forPlayer) {
		this.skill = skill;
		this.page = page;
		this.requestor = forPlayer;
	}

	@Override
	public void execute(Connection connection, RetrievalConnection leaderboardConnection) {

	}
}
