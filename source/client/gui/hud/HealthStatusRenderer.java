package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.*;

import static net.minecraft.client.gui.AbstractGui.GUI_ICONS_LOCATION;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.HEALTH;

public class HealthStatusRenderer {
	private static final ResourceLocation HEALTH_BAR = AdventOfAscension.id("textures/gui/overlay/misc/health_bar.png");
	private static float deltaHealth = 0;
	private static int lastHealthTime = 0;
	private static float lastTickHealth = 0;

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, false, RenderGameOverlayEvent.Pre.class, HealthStatusRenderer::onHealthRender);
	}

	private static void onHealthRender(RenderGameOverlayEvent.Pre ev) {
		if (ev.isCanceled() || ev.getType() != HEALTH)
			return;

		HealthRenderType renderType = AoAConfig.CLIENT.healthRenderType.get();

		if (renderType == HealthRenderType.HEARTS)
			return;

		Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;
		MatrixStack matrix = ev.getMatrixStack();

		int left = (mc.getWindow().getGuiScaledWidth() / 2) - 91;
		int top = mc.getWindow().getGuiScaledHeight() - ForgeIngameGui.left_height;
		ForgeIngameGui.left_height += 10;

		ev.setCanceled(true);
		mc.getProfiler().push("health");
		RenderSystem.enableBlend();

		float currentHealth = player.getHealth();
		float maxHealth = player.getMaxHealth();
		boolean poisoned = mc.player.hasEffect(Effects.POISON);
		boolean withered = mc.player.hasEffect(Effects.WITHER);
		float absorption = mc.player.getAbsorptionAmount();

		if (renderType == HealthRenderType.NUMERIC) {
			renderNumeric(matrix, mc, left, top, currentHealth, maxHealth, poisoned, withered, absorption);
		}
		else {
			renderBar(matrix, mc, left, top, currentHealth, maxHealth, poisoned, withered, absorption);

			if (renderType ==  HealthRenderType.BAR_NUMERIC)
				renderNumeric(matrix, mc, left, top, currentHealth, maxHealth, poisoned, withered, absorption);
		}

		RenderSystem.disableBlend();
		mc.getProfiler().pop();
		mc.textureManager.bind(GUI_ICONS_LOCATION);
		MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(matrix, ev, HEALTH));
	}

	private static void renderBar(MatrixStack matrix, Minecraft mc, int left, int top, float currentHealth, float maxHealth, boolean poisoned, boolean withered, float absorption) {
		int uvY = 0;

		if (absorption > 0) {
			uvY = 48;
		}
		else if (withered) {
			uvY = 84;
		}
		else if (poisoned) {
			uvY = 72;
		}
		else if (mc.player.hasEffect(Effects.HEALTH_BOOST)) {
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
		mc.textureManager.bind(HEALTH_BAR);

		float healthWidth = 81 * (currentHealth / maxHealth);

		if (currentHealth < maxHealth)
			RenderUtil.renderCustomSizedTexture(matrix, 0, 0, 0, 12, 81, 12, 81, 120);

		if (!mc.player.isAlive()) {
			matrix.popPose();

			return;
		}

		RenderUtil.renderCustomSizedTexture(matrix, 0, 0, 0, uvY, healthWidth, 12, 81, 120);

		if (deltaHealth != 0) {
			int deltaUvY = deltaHealth < 0 ? 24 : 36;
			float deltaWidth = mc.player.tickCount - lastHealthTime < 8 ? 1 : ((12 - (mc.player.tickCount - 8 - lastHealthTime)) / (float)12);
			float width = Math.min(Math.abs(deltaHealth), maxHealth) / maxHealth * 81 * deltaWidth;
			float x = deltaHealth < 0 ? healthWidth : healthWidth - width;

			if (deltaHealth < 0 && x + width > 81)
				width = 81 - x;

			RenderUtil.renderScaledCustomSizedTexture(matrix, x, 0, x, deltaUvY, width, 12, width, 12, 81, 120);
		}

		RenderUtil.drawColouredBox(matrix, 0, 0, 0, 81, 11, 0x44000000);
		matrix.popPose();
	}

	private static void renderNumeric(MatrixStack matrix, Minecraft mc, int left, int top, float currentHealth, float maxHealth, boolean poisoned, boolean withered, float absorption) {
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

		if (AoAConfig.CLIENT.healthRenderType.get() == HealthRenderType.NUMERIC) {
			if (absorption > 0)
				left -= 15;

			matrix.translate(left + 15, top + 0.9, 0);
			matrix.scale(0.9f, 0.9f, 1);

			if (currentHealth > 0) {
				renderHeart(matrix, mc, currentHealth, maxHealth, handleHealthState(mc.player, mc.gui, (int)Math.ceil(currentHealth)), poisoned, withered, absorption);

				RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(currentHealth, 1) + "/" + NumberUtil.roundToNthDecimalPlace(maxHealth, 1), 34, 0, 1, healthColour, RenderUtil.StringRenderType.OUTLINED);

				if (absorption > 0) {
					RenderUtil.drawCenteredScaledString(matrix, mc.font, "+", 67, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
					RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(absorption, 1), 83, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
				}
			}
			else {
				RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString("deathScreen.title"), (AoAConfig.CLIENT.healthRenderType.get() == HealthRenderType.BAR_NUMERIC ? 28.5f : 24), 0, 1, ColourUtil.RGB(150, 0, 0), RenderUtil.StringRenderType.OUTLINED);
			}
		}
		else {
			ForgeIngameGui.left_height += 2;

			if (absorption > 0)
				left -= 8;

			matrix.translate(left + 17, top + 1.2, 0);
			matrix.scale(0.8f, 0.8f, 1);

			if (currentHealth > 0) {
				renderHeart(matrix, mc, currentHealth, maxHealth, handleHealthState(mc.player, mc.gui, (int)Math.ceil(currentHealth)), poisoned, withered, absorption);

				RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(currentHealth, 1) + "/" + NumberUtil.roundToNthDecimalPlace(maxHealth, 1), 34, 0, 1, healthColour, RenderUtil.StringRenderType.OUTLINED);

				if (absorption > 0) {
					RenderUtil.drawCenteredScaledString(matrix, mc.font, "+", 62, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
					RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(absorption, 1), 70, 0, 1, ColourUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
				}
			}
			else {
				RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString("deathScreen.title"), (AoAConfig.CLIENT.healthRenderType.get() == HealthRenderType.BAR_NUMERIC ? 28.5f : 24), 0, 1, ColourUtil.RGB(150, 0, 0), RenderUtil.StringRenderType.OUTLINED);
			}
		}

		matrix.popPose();
	}

	private static void renderHeart(MatrixStack matrix, Minecraft mc, float currentHealth, float maxHealth, boolean flashing, boolean poisoned, boolean withered, float absorb) {
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

		if (mc.gui.tickCount % 25 == 0 && mc.player.hasEffect(Effects.REGENERATION))
			y -= 2;

		Minecraft.getInstance().textureManager.bind(GUI_ICONS_LOCATION);
		RenderSystem.color4f(1, 1, 1, 1f);
		RenderUtil.renderCustomSizedTexture(matrix, 0, y, flashing ? 25 : 16, uvY, 9, 9, 256, 256);

		if (flashing)
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + 54, uvY, 9, 9, 256, 256);

		if (absorb > 0) {
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + 144, uvY, 9, 9, 256, 256);
		}
		else {
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + (currentHealth >= maxHealth ? 36 : 45), uvY, 9, 9, 256, 256);
		}
	}

	private static boolean handleHealthState(ClientPlayerEntity player, IngameGui gui, float currentHealth) {
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