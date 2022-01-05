package net.tslat.aoa3.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.network.NetworkDirection;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.gui.hud.RecoilRenderer;
import net.tslat.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.HaloChangePacket;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.object.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import org.lwjgl.glfw.GLFW;

public final class ClientEventHandler {
	public static void init() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.ClientTickEvent.class, ClientEventHandler::onClientTick);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, ClientEventHandler::onPlayerJoin);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedOutEvent.class, ClientEventHandler::onPlayerLogout);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDeathEvent.class, ClientEventHandler::onPlayerDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, PlaySoundEvent.class, ClientEventHandler::onSoundPlay);
		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.RenderTickEvent.class, RecoilRenderer::renderEvent);
	}

	private static void onClientTick(final TickEvent.ClientTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (!Minecraft.getInstance().isPaused())
				ScreenOverlayRenderer.tickOverlays();

			if (!Minecraft.getInstance().hasSingleplayerServer()) {
				GlobalEvents.tick++;
				AoAScheduler.handleSyncScheduledTasks(GlobalEvents.tick);
			}
		}
	}

	private static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getPlayer().level.isClientSide()) {
			if (AoAConfig.CLIENT.showWelcomeMessage.get()) {
				if (AoAKeybinds.ADVENT_GUI.getKey().getValue() == GLFW.GLFW_KEY_UNKNOWN) {
					ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome.alt", TextFormatting.GRAY), Util.NIL_UUID);
				}
				else {
					ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome", TextFormatting.GRAY, AoAKeybinds.ADVENT_GUI.getKeyBinding().getTranslatedKeyMessage()), Util.NIL_UUID);
				}
			}

			AoAPackets.INSTANCE.sendTo(new HaloChangePacket(AoAConfig.CLIENT.personalHaloPreference.get()), ((ClientPlayerEntity)ev.getPlayer()).connection.getConnection(), NetworkDirection.PLAY_TO_SERVER);
			ClientPlayerDataManager.get().updatePlayerInstance(ev.getPlayer());
		}
	}

	private static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent ev) {
		ClientPlayerDataManager.get().reset();
	}

	private static void onPlayerDeath(LivingDeathEvent ev) {
		if (ev.getEntity() == Minecraft.getInstance().player)
			ScreenOverlayRenderer.clearOverlays();
	}

	private static void onSoundPlay(PlaySoundEvent ev) {
		if (SilencerEntity.isClientNearby) {
			ClientPlayerEntity player = Minecraft.getInstance().player;

			if (player == null) {
				SilencerEntity.isClientNearby = false;

				return;
			}

			if (player.level.getEntitiesOfClass(SilencerEntity.class, player.getBoundingBox().inflate(8)).isEmpty()) {
				SilencerEntity.isClientNearby = false;
				Minecraft.getInstance().getSoundManager().soundEngine.listener.setGain(SilencerEntity.previousGain);
			}
		}
	}
}
