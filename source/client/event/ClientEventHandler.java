package net.tslat.aoa3.client.event;

import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tslat.aoa3.advent.AoAResourceCaching;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.client.gui.overlay.ScreenEffectRenderer;
import net.tslat.aoa3.client.player.ClientPlayerDataManager;
import net.tslat.aoa3.client.render.dimension.AoADimensionEffectsRenderer;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.HaloSelectPacket;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Map;

public final class ClientEventHandler {
	public static void init() {
		final IEventBus forgeBus = NeoForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, ClientTickEvent.Post.class, ClientEventHandler::onClientTick);
		forgeBus.addListener(EventPriority.NORMAL, false, ClientPlayerNetworkEvent.LoggingIn.class, ClientEventHandler::onPlayerJoin);
		forgeBus.addListener(EventPriority.NORMAL, false, ClientPlayerNetworkEvent.LoggingOut.class, ClientEventHandler::onPlayerLogout);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDeathEvent.class, ClientEventHandler::onPlayerDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTooltipEvent.class, ClientEventHandler::onTooltip);
		forgeBus.addListener(EventPriority.NORMAL, false, ViewportEvent.RenderFog.class, ClientEventHandler::onFogRender);
		forgeBus.addListener(EventPriority.NORMAL, false, EntityTickEvent.Post.class, ClientEventHandler::onEntityTick);
	}

	private static void onClientTick(final ClientTickEvent.Post ev) {
		if (!Minecraft.getInstance().hasSingleplayerServer()) {
			GlobalEvents.tick++;
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
		}

		AoANetworking.sendToServer(new HaloSelectPacket(AoAConfigs.CLIENT.personalHaloPreference.get()));
		ClientPlayerDataManager.get().updatePlayerInstance(ev.getPlayer());
	}

	private static void onPlayerLogout(ClientPlayerNetworkEvent.LoggingOut ev) {
		if (ev.getConnection() != null)
			AoAResourceCaching.clientLogout();
	}

	private static void onPlayerDeath(LivingDeathEvent ev) {
		if (ev.getEntity() == Minecraft.getInstance().player)
			ScreenEffectRenderer.clearOverlays();
	}

	private static void onTooltip(final ItemTooltipEvent ev) {
		Map<String, List<ObjectIntPair<ResourceLocation>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(RegistryUtil.getId(ev.getItemStack().getItem()));

		if (!restrictions.isEmpty()) {
			List<Component> lines = ev.getToolTip();

			if (ev.getFlags().isAdvanced()) {
				lines.add(1, LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.skillReq"), ChatFormatting.DARK_RED));

				int index = 2;

				for (Map.Entry<String, List<ObjectIntPair<ResourceLocation>>> reqEntry : restrictions.entrySet()) {
					lines.add(index++, Component.literal("  ").withStyle(ChatFormatting.RED).append(LocaleUtil.getLocaleMessage(Util.makeDescriptionId("ability", AoAAbilities.LEVEL_RESTRICTION.getId()) + ".description." + reqEntry.getKey())).append(":"));

					for (ObjectIntPair<ResourceLocation> pair : reqEntry.getValue()) {
						AoASkill skill = AoASkills.getSkill(pair.first());

						lines.add(index++, Component.literal("    ").withStyle(ChatFormatting.GOLD).append(pair.valueInt() + " ").append(skill.getName()));
					}
				}

				lines.add(index, Component.literal(""));
			}
			else {
				lines.add(1, LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.skillReq.hidden"), ChatFormatting.DARK_RED));
			}
		}
	}

	private static void onFogRender(final ViewportEvent.RenderFog ev) {
		final ClientLevel level = (ClientLevel)ClientOperations.getLevel();

		if (level.effects() instanceof AoADimensionEffectsRenderer aoaEffects) {
			aoaEffects.adjustFogRender(level, ev.getMode(), ev.getType(), ev.getCamera(), farPlaneValue -> {
				ev.setFarPlaneDistance(farPlaneValue);
				ev.setCanceled(true);
			}, nearPlaneValue -> {
				ev.setNearPlaneDistance(nearPlaneValue);
				ev.setCanceled(true);
			});
		}
	}

	private static void onEntityTick(EntityTickEvent.Post ev) {
		if (AoAConfigs.CLIENT.partyDeaths.get() && ev.getEntity() instanceof LivingEntity entity && entity.deathTime >= 10) {
			AABB boundingBox = entity.getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				ParticleBuilder.forRandomPosInBounds(AoAParticleTypes.GENERIC_DUST.get(), entity.getBoundingBox())
						.scaleMod(0.1f)
						.power(new Vec3(RandomUtil.scaledGaussianValue(0.05d), 0, RandomUtil.scaledGaussianValue(0.05d)))
						.colourTint(RandomUtil.colour(0.75f, 0.75f, 1f))
						.spawnClientParticles(entity.level());
			}
		}
	}
}
