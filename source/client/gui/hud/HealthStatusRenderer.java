package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.tme.api.util.RandomUtil;

public class HealthStatusRenderer {
	private static final ResourceLocation HEALTH_BAR = AdventOfAscension.id("textures/gui/overlay/misc/health_bar.png");
	private static float deltaHealth = 0;
	private static int lastHealthTime = 0;
	private static float lastTickHealth = 0;

	public static void init() {
		NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, false, RenderGuiLayerEvent.Pre.class, HealthStatusRenderer::onHealthRender);
	}

	private static void onHealthRender(RenderGuiLayerEvent.Pre ev) {
		if (ev.isCanceled() || !ev.getName().equals(VanillaGuiLayers.PLAYER_HEALTH))
			return;

		HealthRenderType renderType = AoAConfigs.CLIENT.healthRenderType.get();

		if (renderType == HealthRenderType.HEARTS)
			return;

		Minecraft mc = Minecraft.getInstance();

		if (mc.options.hideGui || !mc.gameMode.canHurtPlayer())
			return;

		LocalPlayer player = mc.player;
		PoseStack matrix = ev.getGuiGraphics().pose();

		int left = (mc.getWindow().getGuiScaledWidth() / 2) - 91;
		int top = mc.getWindow().getGuiScaledHeight() - mc.gui.leftHeight;
		mc.gui.leftHeight += 10;

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableDepthTest();
		ev.setCanceled(true);

		mc.getProfiler().push("health");
		RenderSystem.enableBlend();

		float currentHealth = player.getHealth();
		float maxHealth = player.getMaxHealth();
		boolean poisoned = mc.player.hasEffect(MobEffects.POISON);
		boolean withered = mc.player.hasEffect(MobEffects.WITHER);
		boolean frozen = mc.player.isFullyFrozen();
		float absorption = mc.player.getAbsorptionAmount();
		boolean bleeding = mc.player.hasEffect(AoAMobEffects.BLEEDING);

		if (renderType == HealthRenderType.NUMERIC) {
			renderNumeric(matrix, mc, left, top, currentHealth, maxHealth, poisoned, withered, frozen, absorption);
		}
		else {
			renderBar(matrix, mc, left, top, currentHealth, maxHealth, poisoned, withered, frozen, absorption, bleeding);

			if (renderType ==  HealthRenderType.BAR_NUMERIC)
				renderNumeric(matrix, mc, left, top, currentHealth, maxHealth, poisoned, withered, frozen, absorption);
		}

		RenderSystem.disableBlend();
		mc.getProfiler().pop();
	}

	private static void renderBar(PoseStack poseStack, Minecraft mc, int left, int top, float currentHealth, float maxHealth, boolean poisoned, boolean withered, boolean frozen, float absorption, boolean bleeding) {
		int uvY = 0;

		if (absorption > 0) {
			uvY = 48;
		}
		else if (frozen) {
			uvY = 96;
		}
		else if (withered) {
			uvY = 84;
		}
		else if (poisoned) {
			uvY = 72;
		}
		else if (bleeding) {
			uvY = 120;
		}
		else if (mc.player.hasEffect(MobEffects.HEALTH_BOOST)) {
			uvY = 60;
		}
		else if (mc.level.getLevelData().isHardcore()) {
			uvY = 108;
		}

		if (!NumberUtil.roundToNthDecimalPlace(lastTickHealth, 1).equals(NumberUtil.roundToNthDecimalPlace(currentHealth, 1))) {
			if (lastTickHealth < currentHealth) {
				if (lastHealthTime == 0) {
					lastTickHealth = currentHealth;
					lastHealthTime = mc.player.tickCount;

					return;
				}

				if (deltaHealth < 0)
					deltaHealth = 0;

				deltaHealth += currentHealth - lastTickHealth;
			}
			else {
				if (deltaHealth > 0)
					deltaHealth = 0;

				deltaHealth -= lastTickHealth - currentHealth;
			}

			lastHealthTime = mc.player.tickCount + 12;
		}

		if (deltaHealth != 0 && lastHealthTime + 20 < mc.player.tickCount)
			deltaHealth = 0;

		lastTickHealth = currentHealth;
		poseStack.pushPose();
		poseStack.translate(left, top - 1.9, 0);
		RenderUtil.prepRenderTexture(HEALTH_BAR);

		float healthWidth = 81 * (currentHealth / maxHealth);

		if (currentHealth < maxHealth)
			RenderUtil.renderCustomSizedTexture(poseStack, 0, 0, 0, 12, 81, 12, 81, 132);

		if (!mc.player.isAlive()) {
			poseStack.popPose();

			return;
		}

		RenderUtil.renderCustomSizedTexture(poseStack, 0, 0, 0, uvY, healthWidth, 12, 81, 132);

		if (deltaHealth != 0) {
			int deltaUvY = deltaHealth < 0 ? 24 : 36;
			float deltaWidth = mc.player.tickCount - lastHealthTime < 8 ? 1 : ((12 - (mc.player.tickCount - 8 - lastHealthTime)) / (float)12);
			float width = Math.min(Math.abs(deltaHealth), maxHealth) / maxHealth * 81 * deltaWidth;
			float x = deltaHealth < 0 ? healthWidth : healthWidth - width;

			if (deltaHealth < 0 && x + width > 81)
				width = 81 - x;

			RenderUtil.renderScaledCustomSizedTexture(poseStack, x, 0, x, deltaUvY, width, 12, width, 12, 81, 132);

			if (absorption <= 0) {
				Component deltaText = deltaHealth > 0 && deltaHealth < 100 ? Component.literal("+").append(NumberUtil.floorAndAppendSuffix(deltaHealth, true)) : Component.literal(NumberUtil.floorAndAppendSuffix(deltaHealth, true));
				int alpha = Math.max(10, (int)(deltaWidth * 255)) << 24;
				int colour = (deltaHealth > 0 ? 0x20B200 : 0xC81400) | alpha;

				RenderUtil.renderScaledText(poseStack, mc.font, deltaText, 2.5f, 3, 0.8f, colour, alpha, RenderUtil.TextRenderType.OUTLINED, LightTexture.FULL_BRIGHT, Minecraft.getInstance().renderBuffers().bufferSource());
			}
		}

		RenderUtil.drawRectangle(poseStack, 0, 0, 81, 11, 0x44000000);
		poseStack.popPose();
	}

	private static void renderNumeric(PoseStack matrix, Minecraft mc, int left, int top, float currentHealth, float maxHealth, boolean poisoned, boolean withered, boolean frozen, float absorption) {
		int healthColour;

		if (poisoned) {
			healthColour = 0x757100;
		}
		else if (withered) {
			healthColour = 0x1C1C1C;
		}
		else {
			healthColour = 0xFC1400;
		}

		matrix.pushPose();

		if (AoAConfigs.CLIENT.healthRenderType.get() == HealthRenderType.NUMERIC) {
			if (absorption > 0)
				left -= 15;

			matrix.translate(left + 15, top + 0.9, 0);
			matrix.scale(0.9f, 0.9f, 1);

			if (currentHealth > 0) {
				renderHeart(matrix, mc, currentHealth, maxHealth, handleHealthState(mc.player, mc.gui, (int)Math.ceil(currentHealth)), poisoned, withered, frozen, absorption);

				RenderUtil.renderCenteredScaledText(matrix, Component.literal(NumberUtil.roundToNthDecimalPlace(currentHealth, 1) + "/" + NumberUtil.roundToNthDecimalPlace(maxHealth, 1)), 34, 0, 1, healthColour, RenderUtil.TextRenderType.OUTLINED);

				if (absorption > 0) {
					RenderUtil.renderCenteredScaledText(matrix, Component.literal("+"), 67, 0, 1, 0xFFCC00, RenderUtil.TextRenderType.OUTLINED);
					RenderUtil.renderCenteredScaledText(matrix, Component.literal(NumberUtil.roundToNthDecimalPlace(absorption, 1)), 83, 0, 1, 0xFFCC00, RenderUtil.TextRenderType.OUTLINED);
				}
			}
			else {
				RenderUtil.renderCenteredScaledText(matrix, LocaleUtil.getLocaleMessage("deathScreen.title"), (AoAConfigs.CLIENT.healthRenderType.get() == HealthRenderType.BAR_NUMERIC ? 28.5f : 24), 0, 1, 0x960000, RenderUtil.TextRenderType.OUTLINED);
			}
		}
		else {
			mc.gui.leftHeight += 2;

			if (absorption > 0)
				left -= 8;

			matrix.translate(left + 17, top + 1.2, 0);
			matrix.scale(0.8f, 0.8f, 1);

			if (currentHealth > 0) {
				renderHeart(matrix, mc, currentHealth, maxHealth, handleHealthState(mc.player, mc.gui, (int)Math.ceil(currentHealth)), poisoned, withered, frozen, absorption);

				RenderUtil.renderCenteredScaledText(matrix, Component.literal(NumberUtil.roundToNthDecimalPlace(currentHealth, 1) + "/" + NumberUtil.roundToNthDecimalPlace(maxHealth, 1)), 34, 0, 1, healthColour, RenderUtil.TextRenderType.OUTLINED);

				if (absorption > 0) {
					RenderUtil.renderCenteredScaledText(matrix, Component.literal("+"), 62, 0, 1, 0xFFCC00, RenderUtil.TextRenderType.OUTLINED);
					RenderUtil.renderCenteredScaledText(matrix, Component.literal(NumberUtil.roundToNthDecimalPlace(absorption, 1)), 70, 0, 1, 0xFFCC00, RenderUtil.TextRenderType.OUTLINED);
				}
			}
			else {
				RenderUtil.renderCenteredScaledText(matrix, LocaleUtil.getLocaleMessage("deathScreen.title"), (AoAConfigs.CLIENT.healthRenderType.get() == HealthRenderType.BAR_NUMERIC ? 28.5f : 24), 0, 1, 0x960000, RenderUtil.TextRenderType.OUTLINED);
			}
		}

		matrix.popPose();
	}

	private static void renderHeart(PoseStack poseStack, Minecraft mc, float currentHealth, float maxHealth, boolean flashing, boolean poisoned, boolean withered, boolean frozen, float absorb) {
		final boolean hardcore = mc.level.getLevelData().isHardcore();
		int y = -1;

		if (currentHealth <= maxHealth * 0.2f && RandomUtil.fiftyFifty())
			y += 1;

		if (mc.gui.tickCount % 25 == 0 && mc.player.hasEffect(MobEffects.REGENERATION))
			y -= 2;

		RenderUtil.renderSprite(poseStack, Gui.HeartType.CONTAINER.getSprite(hardcore, false, flashing), 0, y, 9, 9);
		RenderUtil.renderSprite(poseStack, Gui.HeartType.forPlayer(mc.player).getSprite(hardcore, currentHealth < maxHealth, flashing), 0, y, 9, 9);
	}

	private static boolean handleHealthState(LocalPlayer player, Gui gui, float currentHealth) {
		boolean shouldFlash = gui.healthBlinkTime > (long)gui.tickCount && (gui.healthBlinkTime - (long)gui.tickCount) / 3L % 2L == 1L;

		if (currentHealth < gui.lastHealth && player.invulnerableTime > 0) {
			gui.lastHealthTime = Util.getMillis();
			gui.healthBlinkTime = gui.tickCount + 20;
		}
		else if (currentHealth > gui.lastHealth && player.invulnerableTime > 0) {
			gui.lastHealthTime = Util.getMillis();
			gui.healthBlinkTime = gui.tickCount + 10;
		}

		if (Util.getMillis() - gui.lastHealthTime > 1000L) {
			gui.lastHealth = (int)currentHealth;
			gui.displayHealth = (int)currentHealth;
			gui.lastHealthTime = Util.getMillis();
		}

		gui.lastHealth = (int)currentHealth;

		return shouldFlash;
	}

	public enum HealthRenderType {
		HEARTS,
		BAR,
		NUMERIC,
		BAR_NUMERIC
	}
}