package net.tslat.aoa3.common;

import net.minecraft.block.BlockLeaves;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.common.packet.leaderboard.PacketLeaderboardStats;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.leaderboard.AoALeaderboard;
import net.tslat.aoa3.utils.WebUtil;

public class ServerProxy {
	public void preInit() {}

	public void postInit() {
		WebUtil.doHTTPTasks(false);
	}

	public void serverStart(MinecraftServer server) {
		OverworldEvents.doWorldStartCheck(server.getWorld(0));
	}

	public void serverStarted() {
		if (FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer())
			AoALeaderboard.init();
	}

	public void handleLeaderboardData(PacketLeaderboardStats packet) {}

	public void worldShutdown() {}

	public void serverShutdown() {
		AoALeaderboard.doShutdownTasks();
	}

	public void displayScreenOverlay(final int ticks, final Enums.ScreenOverlays screen) {}

	public void displayHelmetScreen(final boolean on, final int screen) {}

	public void addRecoil(final float recoil, final int firingTime) {}

	public void renderInfo() {}

	public void setPlayerHealth(final float health) {}

	public void setFancyLeaves(BlockLeaves block) {}

	public void registerStateMappers() {}
}
