package net.tslat.aoa3.common;

import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.PacketToastPopup;
import net.tslat.aoa3.common.packet.leaderboard.PacketLeaderboardStats;
import net.tslat.aoa3.entity.mobs.greckon.EntitySilencer;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.leaderboard.AoALeaderboard;
import net.tslat.aoa3.utils.WebUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

public class ServerProxy {
	private static AoALeaderboard leaderboardThread;

	public void preInit() {}

	public void postInit() {}

	public void serverStart(MinecraftServer server) {
		OverworldEvents.doWorldStartCheck(server.getWorld(0));
		WebUtil.extractPlayerCrownsFromWeb();
	}

	public void serverStarted() {
		/*if (FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer())
			leaderboardThread = AoALeaderboard.init();*/
	}

	public void handleLeaderboardData(PacketLeaderboardStats packet) {}

	public void worldShutdown() {}

	public void serverShutdown() {
		if (leaderboardThread != null) {
			AoALeaderboard.doShutdownTasks();

			try {
				leaderboardThread.join();
			} catch (InterruptedException ex) {
				AdventOfAscension.logMessage(Level.ERROR, "The AoA Leaderboard thread was interrupted prematurely, shutdown tasks were not completed. Data may have been compromised.");
				ex.printStackTrace();
			}
		}
	}

	public void displayScreenOverlay(final int ticks, final Enums.ScreenOverlays screen) {}

	public void displayHelmetScreen(final boolean on, final int screen) {}

	public void addRecoil(final float recoil, final int firingTime) {}

	public void renderInfo() {}

	public void setPlayerHealth(final float health) {}

	public void setFancyLeaves(BlockLeaves block) {}

	public void displayToast(PacketToastPopup.ToastPopupType type, Object subject, Object value) {}

	public void registerStateMappers() {}

	public void doSilencerSilence(EntitySilencer silencer) {}

	public void spawnParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int textureOffsetIndex, float scale, int... args) {}

	public void playMusic(SoundEvent soundEvent, @Nullable Entity linkedEntity) {}

	public void stopMusic() {}
}
