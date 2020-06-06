package net.tslat.aoa3.common;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.event.ClientEventHandler;
import net.tslat.aoa3.client.event.KeyBinder;
import net.tslat.aoa3.client.gui.render.*;
import net.tslat.aoa3.client.gui.toasts.LevelRequirementToast;
import net.tslat.aoa3.client.gui.toasts.ResourceRequirementToast;
import net.tslat.aoa3.client.gui.toasts.TributeRequirementToast;
import net.tslat.aoa3.client.model.entities.player.LayerPlayerHalo;
import net.tslat.aoa3.client.render.FXRenders;
import net.tslat.aoa3.client.render.entities.projectiles.ProjectileRenders;
import net.tslat.aoa3.client.sound.MusicSound;
import net.tslat.aoa3.command.CommandAoAWiki;
import net.tslat.aoa3.common.packet.PacketToastPopup;
import net.tslat.aoa3.common.packet.leaderboard.PacketLeaderboardStats;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.mobs.greckon.EntitySilencer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

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
		ClientCommandHandler.instance.registerCommand(new CommandAoAWiki());

		for (RenderPlayer playerRenderer : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
			playerRenderer.addLayer(new LayerPlayerHalo(playerRenderer));
		}
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
		forgeBus.register(new SkillsRenderer());
		forgeBus.register(new XpParticlesRenderer());
		forgeBus.register(new BossBarRenderer());
		forgeBus.register(new ClientEventHandler());
		forgeBus.register(new ProjectileRenders());
		forgeBus.register(new FXRenders());
	}

	@Override
	public void displayToast(PacketToastPopup.ToastPopupType type, Object subject, Object value) {
		switch (type) {
			case SKILL_REQUIREMENT:
				if (ConfigurationUtil.MainConfig.useToasts) {
					Minecraft.getMinecraft().getToastGui().add(new LevelRequirementToast((Enums.Skills)subject, (Integer)value));
				}
				else {
					Minecraft.getMinecraft().player.sendMessage(StringUtil.getColourLocaleWithArguments("message.feedback.insufficientLevels", TextFormatting.RED, String.valueOf(value), StringUtil.capitaliseFirstLetter(subject.toString())));
				}
				break;
			case RESOURCE_REQUIREMENT:
				if (ConfigurationUtil.MainConfig.useToasts) {
					Minecraft.getMinecraft().getToastGui().add(new ResourceRequirementToast((Enums.Resources)subject, (Float)value));
				}
				else {
					Minecraft.getMinecraft().player.sendMessage(StringUtil.getColourLocaleWithArguments("message.feedback.insufficientResource", TextFormatting.RED, StringUtil.roundToNthDecimalPlace((Float)value, 2), StringUtil.capitaliseFirstLetter(subject.toString())));
				}
				break;
			case TRIBUTE_REQUIREMENT:
				if (ConfigurationUtil.MainConfig.useToasts) {
					Minecraft.getMinecraft().getToastGui().add(new TributeRequirementToast((Enums.Deities)subject, (Integer)value));
				}
				else {
					Minecraft.getMinecraft().player.sendMessage(StringUtil.getColourLocaleWithArguments("message.feedback.insufficientTribute", TextFormatting.RED, String.valueOf(value), StringUtil.capitaliseFirstLetter(subject.toString())));
				}
				break;
		}
	}

	@Override
	public void setFancyLeaves(BlockLeaves block) {
		block.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
	}

	@Override
	public void registerStateMappers() {
		BlockRegister.registerStateMappers();
	}

	@Override
	public void doSilencerSilence(EntitySilencer silencer) {
		if (silencer.getDistanceSq(Minecraft.getMinecraft().player) < 8 * 8)
			Minecraft.getMinecraft().getSoundHandler().stopSounds();
	}

	@Override
	public void spawnParticle(int particleId, World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int textureOffsetIndex, float scale, int... args) {
		FXRenders.spawnParticle(particleId, posX, posY, posZ, speedX, speedY, speedZ, textureOffsetIndex, scale, args);
	}

	@Override
	public void playMusic(SoundEvent soundEvent, @Nullable Entity linkedEntity) {
		if (Minecraft.getMinecraft().world != null) {
			SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();

			if (!MusicSound.isPlaying(soundEvent, linkedEntity)) {
				stopMusic();
				soundHandler.playSound(new MusicSound(soundEvent, linkedEntity));
			}
		}
	}

	@Override
	public void stopMusic() {
		Minecraft.getMinecraft().getSoundHandler().stopSounds();
	}
}
