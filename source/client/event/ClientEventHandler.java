package net.tslat.aoa3.client.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.gui.overlay.ScreenEffectRenderer;
import net.tslat.aoa3.client.render.shader.AoAPostProcessing;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.HaloSelectPacket;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Map;

public final class ClientEventHandler {
	public static void init() {
		final IEventBus forgeBus = NeoForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.ClientTickEvent.class, ClientEventHandler::onClientTick);
		forgeBus.addListener(EventPriority.NORMAL, false, ClientPlayerNetworkEvent.LoggingIn.class, ClientEventHandler::onPlayerJoin);
		forgeBus.addListener(EventPriority.NORMAL, false, ClientPlayerNetworkEvent.LoggingOut.class, ClientEventHandler::onPlayerLogout);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDeathEvent.class, ClientEventHandler::onPlayerDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, PlaySoundEvent.class, ClientEventHandler::onSoundPlay);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTooltipEvent.class, ClientEventHandler::onTooltip);
		forgeBus.addListener(EventPriority.NORMAL, false, RenderLevelStageEvent.class, AoAPostProcessing::handlePostProcessing);
	}

	private static void onClientTick(final TickEvent.ClientTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (!Minecraft.getInstance().hasSingleplayerServer()) {
				GlobalEvents.tick++;
				AoAScheduler.handleSyncScheduledTasks(GlobalEvents.tick);
			}
		}
	}

	private static void onPlayerJoin(ClientPlayerNetworkEvent.LoggingIn ev) {
		if (AoAConfigs.CLIENT.showWelcomeMessage.get()) {
			if (AoAKeybinds.ADVENT_GUI.getKey().getValue() == GLFW.GLFW_KEY_UNKNOWN) {
				ev.getPlayer().sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("message", "login.welcome.alt"), ChatFormatting.GRAY));
			}
			else {
				ev.getPlayer().sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("message", "login.welcome"), ChatFormatting.GRAY, AoAKeybinds.ADVENT_GUI.getTranslatedKeyMessage()));
			}

			ev.getPlayer().sendSystemMessage(Component.literal("AoA IS IN A TRANSITION PERIOD. ALL DIMENSIONS AND MOST MOBS ARE MISSING. PLAY 1.16.5 FOR SURVIVAL AoA. JOIN THE DISCORD FOR MORE INFO").withStyle(ChatFormatting.DARK_RED));
		}

		AoANetworking.sendToServer(new HaloSelectPacket(AoAConfigs.CLIENT.personalHaloPreference.get()));
		ClientPlayerDataManager.get().updatePlayerInstance(ev.getPlayer());
	}

	private static void onPlayerLogout(ClientPlayerNetworkEvent.LoggingOut ev) {
		ClientPlayerDataManager.get().reset();
	}

	private static void onPlayerDeath(LivingDeathEvent ev) {
		if (ev.getEntity() == Minecraft.getInstance().player)
			ScreenEffectRenderer.clearOverlays();
	}

	private static void onSoundPlay(PlaySoundEvent ev) {
		if (SilencerEntity.isClientNearby) {
			LocalPlayer player = Minecraft.getInstance().player;

			if (player == null) {
				SilencerEntity.isClientNearby = false;

				return;
			}

			if (player.level().getEntitiesOfClass(SilencerEntity.class, player.getBoundingBox().inflate(8)).isEmpty()) {
				SilencerEntity.isClientNearby = false;
				Minecraft.getInstance().getSoundManager().updateSourceVolume(SoundSource.MASTER, SilencerEntity.previousGain);
			}
		}
	}

	private static void onTooltip(final ItemTooltipEvent ev) {
		Map<String, List<Pair<ResourceLocation, Integer>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(RegistryUtil.getId(ev.getItemStack().getItem()));

		if (!restrictions.isEmpty()) {
			List<Component> lines = ev.getToolTip();

			if (ev.getFlags().isAdvanced()) {
				lines.add(1, LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.skillReq"), ChatFormatting.DARK_RED));

				int index = 2;

				for (Map.Entry<String, List<Pair<ResourceLocation, Integer>>> reqEntry : restrictions.entrySet()) {
					lines.add(index++, Component.literal("  ").withStyle(ChatFormatting.RED).append(LocaleUtil.getLocaleMessage(Util.makeDescriptionId("ability", AoAAbilities.LEVEL_RESTRICTION.getId()) + ".description." + reqEntry.getKey())).append(":"));

					for (Pair<ResourceLocation, Integer> pair : reqEntry.getValue()) {
						AoASkill skill = AoASkills.getSkill(pair.getFirst());

						lines.add(index++, Component.literal("    ").withStyle(ChatFormatting.GOLD).append(pair.getSecond() + " ").append(skill.getName()));
					}
				}

				lines.add(index, Component.literal(""));
			}
			else {
				lines.add(1, LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.skillReq.hidden"), ChatFormatting.DARK_RED));
			}
		}
	}
}
