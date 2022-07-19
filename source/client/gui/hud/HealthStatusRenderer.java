package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.util.*;

public class HealthStatusRenderer {
	private static final ResourceLocation HEALTH_BAR = AdventOfAscension.id("textures/gui/overlay/misc/health_bar.png");
	private static float deltaHealth = 0;
	private static int lastHealthTime = 0;
	private static float lastTickHealth = 0;

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, false, RenderGuiOverlayEvent.Pre.class, HealthStatusRenderer::onHealthRender);
	}

	private static void onHealthRender(RenderGuiOverlayEvent.Pre ev) {
		if (ev.isCanceled() || !ev.getOverlay().id().equals(VanillaGuiOverlay.PLAYER_HEALTH.id()))
			return;

		HealthRenderType renderType = AoAConfigs.CLIENT.healthRenderType.get();

		if (renderType == HealthRenderType.HEARTS)
			return;

		Minecraft mc = Minecraft.getInstance();

		if (!(mc.gui instanceof ForgeGui gui) || mc.options.hideGui || !gui.shouldDrawSurvivalElements())
			return;

		LocalPlayer player = mc.player;
		PoseStack matrix = ev.getPoseStack();

		int left = (mc.getWindow().getGuiScaledWidth() / 2) - 91;
		int top = mc.getWindow().getGuiScaledHeight() - gui.leftHeight;
		gui.leftHeight += 10;

		gui.setupOverlayRenderState(true, false);
		ev.setCanceled(true);

		mc.getProfiler().push("health");
		RenderSystem.enableBlend();

		float currentHealth = player.getHealth();
		float maxHealth = player.getMaxHealth();
		boolean poisoned = mc.player.hasEffect(MobEffects.POISON);
		boolean withered = mc.player.hasEffect(MobEffects.WITHER);
		boolean frozen = mc.player.isFullyFrozen();
		float absorption = mc.player.getAbsorptionAmount();
		boolean bleeding = mc.player.hasEffect(AoAMobEffects.BLEEDING.get());

		if (renderType == HealthRenderType.NUMERIC) {
			renderNumeric(matrix, mc, gui, left, top, currentHealth, maxHealth, poisoned, withered, frozen, absorption);
		}
		else {
			renderBar(matrix, mc, gui, left, top, currentHealth, maxHealth, poisoned, withered, frozen, absorption, bleeding);

			if (renderType ==  HealthRenderType.BAR_NUMERIC)
				renderNumeric(matrix, mc, gui, left, top, currentHealth, maxHealth, poisoned, withered, frozen, absorption);
		}

		RenderSystem.disableBlend();
		mc.getProfiler().pop();
	}

	private static void renderBar(PoseStack matrix, Minecraft mc, ForgeGui gui, int left, int top, float currentHealth, float maxHealth, boolean poisoned, boolean withered, boolean frozen, float absorption, boolean bleeding) {
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
		matrix.pushPose();
		matrix.translate(left, top - 1.9, 0);
		RenderUtil.prepRenderTexture(HEALTH_BAR);

		float healthWidth = 81 * (currentHealth / maxHealth);

		if (currentHealth < maxHealth)
			RenderUtil.renderCustomSizedTexture(matrix, 0, 0, 0, 12, 81, 12, 81, 132);

		if (!mc.player.isAlive()) {
			matrix.popPose();

			return;
		}

		RenderUtil.renderCustomSizedTexture(matrix, 0, 0, 0, uvY, healthWidth, 12, 81, 132);

		if (deltaHealth != 0) {
			int deltaUvY = deltaHealth < 0 ? 24 : 36;
			float deltaWidth = mc.player.tickCount - lastHealthTime < 8 ? 1 : ((12 - (mc.player.tickCount - 8 - lastHealthTime)) / (float)12);
			float width = Math.min(Math.abs(deltaHealth), maxHealth) / maxHealth * 81 * deltaWidth;
			float x = deltaHealth < 0 ? healthWidth : healthWidth - width;

			if (deltaHealth < 0 && x + width > 81)
				width = 81 - x;

			RenderUtil.renderScaledCustomSizedTexture(matrix, x, 0, x, deltaUvY, width, 12, width, 12, 81, 132);
		}

		RenderUtil.drawColouredBox(matrix, 0, 0, 0, 81, 11, 0x44000000);
		matrix.popPose();
	}

	private static void renderNumeric(PoseStack matrix, Minecraft mc, ForgeGui gui, int left, int top, float currentHealth, float maxHealth, boolean poisoned, boolean withered, boolean frozen, float absorption) {
		int healthColour;

		if (poisoned) {
			healthColour = ColourUtil.RGB(117, 113, 0);
		}
		else if (withered) {
			healthColour = ColourUtil.RGB(28, 28, 28);
		}
		else {
			healthColour = ColourUtil.RGB(252, 20, 0);
		}

		matrix.pushPose();

		if (AoAConfigs.CLIENT.healthRenderType.get() == HealthRenderType.NUMERIC) {
			if (absorption > 0)
				left -= 15;

			matrix.translate(left + 15, top + 0.9, 0);
			matrix.scale(0.9f, 0.9f, 1);

			if (currentHealth > 0) {
				renderHeart(matrix, mc, currentHealth, maxHealth, handleHealthState(mc.player, gui, (int)Math.ceil(currentHealth)), poisoned, withered, frozen, absorption);

				RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(currentHealth, 1) + "/" + NumberUtil.roundToNthDecimalPlace(maxHealth, 1), 34, 0, 1, healthColour, RenderUtil.StringRenderType.OUTLINED);

				if (absorption > 0) {
					RenderUtil.drawCenteredScaledString(matrix, mc.font, "+", 67, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
					RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(absorption, 1), 83, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
				}
			}
			else {
				RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString("deathScreen.title"), (AoAConfigs.CLIENT.healthRenderType.get() == HealthRenderType.BAR_NUMERIC ? 28.5f : 24), 0, 1, ColourUtil.RGB(150, 0, 0), RenderUtil.StringRenderType.OUTLINED);
			}
		}
		else {
			gui.leftHeight += 2;

			if (absorption > 0)
				left -= 8;

			matrix.translate(left + 17, top + 1.2, 0);
			matrix.scale(0.8f, 0.8f, 1);

			if (currentHealth > 0) {
				renderHeart(matrix, mc, currentHealth, maxHealth, handleHealthState(mc.player, gui, (int)Math.ceil(currentHealth)), poisoned, withered, frozen, absorption);

				RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(currentHealth, 1) + "/" + NumberUtil.roundToNthDecimalPlace(maxHealth, 1), 34, 0, 1, healthColour, RenderUtil.StringRenderType.OUTLINED);

				if (absorption > 0) {
					RenderUtil.drawCenteredScaledString(matrix, mc.font, "+", 62, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
					RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(absorption, 1), 70, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
				}
			}
			else {
				RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString("deathScreen.title"), (AoAConfigs.CLIENT.healthRenderType.get() == HealthRenderType.BAR_NUMERIC ? 28.5f : 24), 0, 1, ColourUtil.RGB(150, 0, 0), RenderUtil.StringRenderType.OUTLINED);
			}
		}

		matrix.popPose();
	}

	private static void renderHeart(PoseStack matrix, Minecraft mc, float currentHealth, float maxHealth, boolean flashing, boolean poisoned, boolean withered, boolean frozen, float absorb) {
		int uvX = 16;
		int uvY = mc.level.getLevelData().isHardcore() ? 45 : 0;
		int y = -1;

		if (absorb <= 0) {
			if (poisoned) {
				uvX += 36;
			}
			else if (withered) {
				uvX += 72;
			}
		}

		if (currentHealth <= maxHealth * 0.2f && RandomUtil.fiftyFifty())
			y += 1;

		if (mc.gui.tickCount % 25 == 0 && mc.player.hasEffect(MobEffects.REGENERATION))
			y -= 2;

		RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
		RenderUtil.renderCustomSizedTexture(matrix, 0, y, Gui.HeartType.CONTAINER.getX(false, flashing), uvY, 9, 9, 256, 256);

		if (flashing)
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + 54, uvY, 9, 9, 256, 256);

		if (absorb > 0) {
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + 144, uvY, 9, 9, 256, 256);
		}
		else if (frozen) {
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + 162, uvY, 9, 9, 256, 256);
		}
		else {
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + (currentHealth >= maxHealth ? 36 : 45), uvY, 9, 9, 256, 256);
		}
	}

	private static boolean handleHealthState(LocalPlayer player, ForgeGui gui, float currentHealth) {
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