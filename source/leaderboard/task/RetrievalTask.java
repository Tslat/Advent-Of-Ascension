package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.leaderboard.LeaderboardTask;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.leaderboard.connection.RetrievalConnection;

public abstract class RetrievalTask extends LeaderboardTask<RetrievalConnection> {
	@Override
	public void queue() {
		if (SkillsLeaderboard.isEnabled() || requiresCompletion())
			SkillsLeaderboard.queueRetrievalTask(this);
	}
}
