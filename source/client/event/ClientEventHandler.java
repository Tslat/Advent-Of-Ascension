package net.tslat.aoa3.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.HaloChangePacket;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {
	@SubscribeEvent
	public static void clientTick(final TickEvent.ClientTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (!Minecraft.getInstance().isPaused())
				ScreenOverlayRenderer.tickOverlays();

			if (!Minecraft.getInstance().hasSingleplayerServer()) {
				GlobalEvents.tick++;
				AoAScheduler.handleSyncScheduledTasks(GlobalEvents.tick);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getPlayer() instanceof ClientPlayerEntity) {
			if (AoAConfig.CLIENT.showWelcomeMessage.get()) {
				if (AoAKeybinds.ADVENT_GUI.getKey().getValue() == GLFW.GLFW_KEY_UNKNOWN) {
					ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome.alt", TextFormatting.GRAY), Util.NIL_UUID);
				}
				else {
					ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome", TextFormatting.GRAY, AoAKeybinds.ADVENT_GUI.getKeyBinding().getTranslatedKeyMessage()), Util.NIL_UUID);
				}
			}

			AoAPackets.INSTANCE.sendTo(new HaloChangePacket(AoAConfig.CLIENT.personalHaloPreference.get()), ((ClientPlayerEntity)ev.getPlayer()).connection.getConnection(), NetworkDirection.PLAY_TO_SERVER);
			ClientPlayerDataManager.setPlayer((ClientPlayerEntity)ev.getPlayer());
		}
	}

	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent ev) {
		ClientPlayerDataManager.reset();
	}

	@SubscribeEvent
	public static void onPlayerDeath(LivingDeathEvent ev) {
		if (ev.getEntity() instanceof PlayerEntity)
			ScreenOverlayRenderer.clearOverlays();
	}

	@SubscribeEvent
	public static void onSoundPlay(PlaySoundEvent ev) {
		if (SilencerEntity.isClientNearby) {
			ClientPlayerEntity player = Minecraft.getInstance().player;

			if (player == null || player.level.getEntitiesOfClass(SilencerEntity.class, player.getBoundingBox().inflate(8)).isEmpty()) {
				SilencerEntity.isClientNearby = false;
				Minecraft.getInstance().getSoundManager().soundEngine.listener.setGain(SilencerEntity.previousGain);
			}
		}
	}
}
