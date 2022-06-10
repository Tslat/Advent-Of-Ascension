package net.tslat.aoa3.client.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import net.tslat.aoa3.client.model.armor.AoAMiscModels;
import net.tslat.aoa3.client.render.entity.layer.PlayerHaloRenderLayer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.HaloChangePacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.content.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.data.client.AdventGuiThemeReloadListener;
import net.tslat.aoa3.data.client.BestiaryReloadListener;
import net.tslat.aoa3.data.client.MiscellaneousReloadListener;
import net.tslat.aoa3.data.client.RealmstoneInsertsReloadListener;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Map;

public final class ClientEventHandler {
	public static void init() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.ClientTickEvent.class, ClientEventHandler::onClientTick);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, ClientEventHandler::onPlayerJoin);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedOutEvent.class, ClientEventHandler::onPlayerLogout);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDeathEvent.class, ClientEventHandler::onPlayerDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, PlaySoundEvent.class, ClientEventHandler::onSoundPlay);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTooltipEvent.class, ClientEventHandler::onTooltip);
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, RegisterClientReloadListenersEvent.class, ClientEventHandler::onResourceListenersRegistration);
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, EntityRenderersEvent.AddLayers.class, ClientEventHandler::onRenderLayerRegistration);
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
					ev.getPlayer().sendSystemMessage(LocaleUtil.getLocaleMessage("message.login.welcome.alt", ChatFormatting.GRAY));
				}
				else {
					ev.getPlayer().sendSystemMessage(LocaleUtil.getLocaleMessage("message.login.welcome", ChatFormatting.GRAY, AoAKeybinds.ADVENT_GUI.getTranslatedKeyMessage()));
				}
			}

			AoAPackets.INSTANCE.sendTo(new HaloChangePacket(AoAConfig.CLIENT.personalHaloPreference.get()), ((LocalPlayer)ev.getPlayer()).connection.getConnection(), NetworkDirection.PLAY_TO_SERVER);
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
			LocalPlayer player = Minecraft.getInstance().player;

			if (player == null) {
				SilencerEntity.isClientNearby = false;

				return;
			}

			if (player.level.getEntitiesOfClass(SilencerEntity.class, player.getBoundingBox().inflate(8)).isEmpty()) {
				SilencerEntity.isClientNearby = false;
				Minecraft.getInstance().getSoundManager().updateSourceVolume(SoundSource.MASTER, SilencerEntity.previousGain);
			}
		}
	}

	private static void onTooltip(final ItemTooltipEvent ev) {
		Map<String, List<Pair<ResourceLocation, Integer>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(ForgeRegistries.ITEMS.getKey(ev.getItemStack().getItem()));

		if (!restrictions.isEmpty()) {
			List<Component> lines = ev.getToolTip();

			if (ev.getFlags().isAdvanced()) {
				lines.add(1, LocaleUtil.getLocaleMessage("gui.tooltip.skillReq", ChatFormatting.DARK_RED));

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
				lines.add(1, LocaleUtil.getLocaleMessage("gui.tooltip.skillReq.hidden", ChatFormatting.DARK_RED));
			}
		}
	}

	private static void onResourceListenersRegistration(final RegisterClientReloadListenersEvent ev) {
		ev.registerReloadListener(new BestiaryReloadListener());
		ev.registerReloadListener(new MiscellaneousReloadListener());
		ev.registerReloadListener(new RealmstoneInsertsReloadListener());
		ev.registerReloadListener(new AdventGuiThemeReloadListener());
		ev.registerReloadListener((ResourceManagerReloadListener)resourceManager -> {
			if (ModLoader.isLoadingStateValid())
				AoAMiscModels.generateFactories();
		});
	}

	private static void onRenderLayerRegistration(final EntityRenderersEvent.AddLayers ev) {
		for (String skin : ev.getSkins()) {
			LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer = ev.getSkin(skin);

			renderer.addLayer(new PlayerHaloRenderLayer(renderer, ev.getEntityModels()));
		}
	}
}
