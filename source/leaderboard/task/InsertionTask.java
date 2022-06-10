package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.leaderboard.LeaderboardTask;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.leaderboard.connection.InsertionConnection;

public abstract class InsertionTask extends LeaderboardTask<InsertionConnection> {
	@Override
	public void queue() {
		if (SkillsLeaderboard.isEnabled() || requiresCompletion())
			SkillsLeaderboard.queueUpdateTask(this);
	}
}
