package net.tslat.aoa3.library.leaderboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.leaderboard.PacketLeaderboardStats;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AoALeaderboard extends Thread {
	private static final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
	private static volatile boolean running = true;

	private static final Runnable THREAD_KILLER = new KillTask();

	@Override
	public void run() {
		if (!ConfigurationUtil.MainConfig.leaderboardEnabled) {
			running = false;

			return;
		}

		while (running) {
			try {
				Runnable task = taskQueue.take();

				if (task == THREAD_KILLER) {
					running = false;

					break;
				}
				else {
					task.run();
				}
			}
			catch (Exception e) {
				AdventOfAscension.logMessage(Level.INFO, "Exception encountered while running an AoALeaderboard task, skipping.");
				e.printStackTrace();
			}
		}
	}

	public static void updatePlayerStat(@Nonnull EntityPlayer pl, @Nonnull Enums.Skills skill, int currentLvl) {
		taskQueue.offer(new UpdatePlayerStat(pl, skill, currentLvl));
	}

	public static void removePlayer(@Nonnull EntityPlayer pl) {
		taskQueue.offer(new RemovePlayer(pl));
	}

	public static void requestPlayerStats(@Nonnull EntityPlayerMP pl, @Nullable Enums.Skills skill) {
		taskQueue.offer(new RequestPlayerStats(pl, skill));
	}

	public static void requestTopTen(@Nonnull EntityPlayerMP pl, @Nullable Enums.Skills skill) {
		taskQueue.offer(new RequestTopTen(pl, skill));
	}

	public static void requestNearbyRankings(@Nonnull EntityPlayerMP pl, @Nullable Enums.Skills skill) {
		taskQueue.offer(new RequestNearbyRankings(pl, skill));
	}

	public static void kill() {
		running = false;
	}

	public static void doShutdownTasks() {
		save();
		taskQueue.offer(THREAD_KILLER);
	}

	public static AoALeaderboard init() {
		AoALeaderboard thread = new AoALeaderboard();
		thread.start();
		running = true;
		taskQueue.clear();
		taskQueue.offer(new InitTask());

		return thread;
	}

	public static void save() {
		taskQueue.offer(new SaveAllBoards());
	}

	private static final class InitTask implements Runnable {
		@Override
		public void run() {
			LeaderboardDataHandler.init();
		}
	}

	private static final class KillTask implements Runnable {
		@Override
		public void run() {}
	}

	private static final class SaveAllBoards implements Runnable {
		private SaveAllBoards() {}

		@Override
		public void run() {
			LeaderboardDataHandler.saveAllLeaderboards();
		}
	}

	private static class RemovePlayer implements Runnable {
		private final EntityPlayer player;

		private RemovePlayer(EntityPlayer pl) {
			this.player = pl;
		}

		@Override
		public void run() {
			LeaderboardDataHandler.getLeaderboard(null).removePlayer(player);

			for (Enums.Skills skill : Enums.Skills.values()) {
				LeaderboardDataHandler.getLeaderboard(skill).removePlayer(player);
			}
		}
	}

	private static class UpdatePlayerStat implements Runnable {
		private final EntityPlayer player;
		private final Enums.Skills skill;
		private final int lvl;

		private UpdatePlayerStat(EntityPlayer pl, @Nullable Enums.Skills skill, int lvl) {
			this.player = pl;
			this.skill = skill;
			this.lvl = lvl;
		}

		@Override
		public void run() {
			Leaderboard skillLeaderboard = LeaderboardDataHandler.getLeaderboard(skill);
			Leaderboard totalsLeaderboard = LeaderboardDataHandler.getLeaderboard(null);

			if (skillLeaderboard != null)
				skillLeaderboard.addOrUpdatePlayer(player, lvl);

			if (totalsLeaderboard != null) {
				final PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

				if (plData != null) {
					int total = 0;

					for (Enums.Skills skill : Enums.Skills.values()) {
						total += plData.stats().getLevel(skill);
					}

					totalsLeaderboard.addOrUpdatePlayer(player, total);
				}
			}
		}
	}

	private static class RequestTopTen implements Runnable {
		private final EntityPlayerMP player;

		@Nullable
		private final Enums.Skills skill;

		private RequestTopTen(EntityPlayerMP pl, Enums.Skills skill) {
			this.player = pl;
			this.skill = skill;
		}

		@Override
		public void run() {
			Leaderboard.LeaderboardDataPackage data = LeaderboardDataHandler.getLeaderboard(skill).getTopTen();

			PacketUtil.network.sendTo(new PacketLeaderboardStats(data.skill, data.firstRank, data.entries), player);
		}
	}

	private static class RequestNearbyRankings implements Runnable {
		private final EntityPlayerMP player;

		@Nullable
		private final Enums.Skills skill;

		private RequestNearbyRankings(EntityPlayerMP pl, Enums.Skills skill) {
			this.player = pl;
			this.skill = skill;
		}

		@Override
		public void run() {
			Leaderboard.LeaderboardDataPackage data = LeaderboardDataHandler.getLeaderboard(skill).getSurroundingRankEntries(player);

			PacketUtil.network.sendTo(new PacketLeaderboardStats(data.skill, data.firstRank, data.entries), player);
		}
	}

	private static class RequestPlayerStats implements Runnable {
		private final EntityPlayerMP player;

		@Nullable
		private final Enums.Skills skill;

		private RequestPlayerStats(EntityPlayerMP pl, Enums.Skills skill) {
			this.player = pl;
			this.skill = skill;
		}

		@Override
		public void run() {

		}
	}
}
