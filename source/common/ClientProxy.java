package net.tslat.aoa3.common;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.event.ClientEventHandler;
import net.tslat.aoa3.client.gui.KeyBinder;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabGuides;
import net.tslat.aoa3.client.gui.render.*;
import net.tslat.aoa3.client.render.FXRenders;
import net.tslat.aoa3.client.render.entities.projectiles.ProjectileRenders;
import net.tslat.aoa3.common.packet.leaderboard.PacketLeaderboardStats;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.library.Enums;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy {
	@Override
	public void preInit() {
		KeyBinder.init();
		registerClientEvents();
	}

	@Override
	public void postInit() {
		ProjectileRenders.postInit();
		AdventGuiTabGuides.prepAvailableBundles();
	}

	@Override
	public void handleLeaderboardData(PacketLeaderboardStats packet) {

	}

	@Override
	public void worldShutdown() {
		BossBarRenderer.boss = null;
	}

	@Override
	public void displayScreenOverlay(final int ticks, final Enums.ScreenOverlays screen) {
		ScreenOverlayRenderer.overlayTicks = ticks;
		ScreenOverlayRenderer.screen = screen;
	}

	@Override
	public void addRecoil(final float recoil, final int firingTime) {
		ClientEventHandler.recoilTicks = Math.min(50, firingTime);
		ClientEventHandler.recoilTicksRemaining = Math.min(50, firingTime);
		ClientEventHandler.recoilAngle = recoil;
	}

	@Override
	public void setPlayerHealth(final float health) {
		Minecraft.getMinecraft().player.setHealth(health);
	}

	private void registerClientEvents() {
		EventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.register(new KeyBinder());
		forgeBus.register(new EntityPropertiesRenderer());
		forgeBus.register(new SniperGuiRenderer());
		forgeBus.register(new HelmetScreenRenderer());
		forgeBus.register(new ScreenOverlayRenderer());
		forgeBus.register(new ResourcesRenderer());
		forgeBus.register(new XpParticlesRenderer());
		forgeBus.register(new BossBarRenderer());
		forgeBus.register(new ClientEventHandler());
		forgeBus.register(new ProjectileRenders());
		forgeBus.register(new FXRenders());
	}

	@Override
	public void setFancyLeaves(BlockLeaves block) {
		block.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
	}

	@Override
	public void registerStateMappers() {
		BlockRegister.registerStateMappers();
	}
}
