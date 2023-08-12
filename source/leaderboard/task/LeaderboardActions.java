package net.tslat.aoa3.leaderboard.task;

import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.Nullable;

public final class LeaderboardActions {
    public static void addNewPlayer(ServerPlayerDataManager plData) {
        if (true)
            return;
        SkillsLeaderboard.getUpdateTaskQueue().add(new AddPlayerTask(plData));
    }

    public static void updatePlayer(ServerPlayerDataManager plData, AoASkill.Instance skill) {
        if (true)
            return;
        SkillsLeaderboard.getUpdateTaskQueue().add(new UpdatePlayerTask(plData, skill));
    }

    public static void getPlayerData(@Nullable AoASkill skill, int page, ServerPlayer forPlayer) {
        if (true)
            return;
        SkillsLeaderboard.getRetrievalTaskQueue().add(new GetPlayerRankingsTask(skill, page, forPlayer));
    }
}
