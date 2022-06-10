package net.tslat.aoa3.leaderboard.task;

import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

public final class LeaderboardActions {
    public static void addNewPlayer(ServerPlayerDataManager plData) {
        SkillsLeaderboard.getUpdateTaskQueue().add(new AddPlayerTask(plData));
    }

    public static void updatePlayer(ServerPlayerDataManager plData, AoASkill.Instance skill) {
        SkillsLeaderboard.getUpdateTaskQueue().add(new UpdatePlayerTask(plData, skill));
    }
}
