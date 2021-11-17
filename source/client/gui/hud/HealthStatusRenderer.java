package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.potion.Effects;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.RenderUtil;

import static net.minecraft.client.gui.AbstractGui.GUI_ICONS_LOCATION;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.HEALTH;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class HealthStatusRenderer {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void renderHealth(RenderGameOverlayEvent.Pre ev) {
		if (ev.getType() == RenderGameOverlayEvent.ElementType.HEALTH && !ev.isCanceled() && AoAConfig.CLIENT.renderNumericalHealth.get()) {
			Minecraft mc = Minecraft.getInstance();
			ClientPlayerEntity player = mc.player;
			MatrixStack matrix = ev.getMatrixStack();

			int left = (mc.getWindow().getGuiScaledWidth() / 2) - 91;
			int top = mc.getWindow().getGuiScaledHeight() - ForgeIngameGui.left_height;
			ForgeIngameGui.left_height += 10;
			int healthColour;

			float currentHealth = player.getHealth();
			float absorption = player.getAbsorptionAmount();

			if (absorption > 0)
				left -= 22;

			if (mc.player.hasEffect(Effects.POISON)) {
				healthColour = NumberUtil.RGB(117, 113, 0);
			}
			else if (mc.player.hasEffect(Effects.WITHER)) {
				healthColour = NumberUtil.RGB(28, 28, 28);
			}
			else {
				healthColour = NumberUtil.RGB(252, 20, 0);
			}

			ev.setCanceled(true);
			mc.getProfiler().push("health");
			RenderSystem.enableBlend();
			matrix.pushPose();
			matrix.translate(left + 22, top + 1, 0);
			matrix.scale(0.9f, 0.9f, 1);

			if (player.getHealth() > 0) {
				renderHeart(matrix, mc, currentHealth, absorption, handleHealthState(player, mc.gui, currentHealth));

				RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(currentHealth, 1) + "/" + NumberUtil.roundToNthDecimalPlace(player.getMaxHealth(), 1), 34, 0, 1, healthColour, RenderUtil.StringRenderType.OUTLINED);

				if (absorption > 0) {
					RenderUtil.drawCenteredScaledString(matrix, mc.font, "-->", 68, 0, 1, NumberUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
					RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.roundToNthDecimalPlace(absorption, 1), 88, 0, 1, NumberUtil.RGB(255, 204, 0), RenderUtil.StringRenderType.OUTLINED);
				}
			}
			else {
				RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString("deathScreen.title"), 24, 0, 1, NumberUtil.RGB(132, 0, 0), RenderUtil.StringRenderType.OUTLINED);
			}

			matrix.popPose();
			RenderSystem.disableBlend();
			mc.getProfiler().pop();
			mc.textureManager.bind(GUI_ICONS_LOCATION);
			MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(matrix, ev, HEALTH));
		}
	}

	private static void renderHeart(MatrixStack matrix, Minecraft mc, float currentHealth, float absorb, boolean flashing) {
		int uvX = 16;
		int uvY = 0;
		int y = -1;

		if (absorb <= 0) {
			if (mc.player.hasEffect(Effects.POISON)) {
				uvX += 36;
			}
			else if (mc.player.hasEffect(Effects.WITHER)) {
				uvX += 72;
			}
		}

		if (mc.level.getLevelData().isHardcore())
			uvY = 45;

		if (currentHealth <= 4)
			y += RandomUtil.randomNumberUpTo(2);

		if (mc.gui.tickCount % 25 == 0 && mc.player.hasEffect(Effects.REGENERATION))
			y -= 2;

		mc.textureManager.bind(GUI_ICONS_LOCATION);

		RenderUtil.renderCustomSizedTexture(matrix, 0, y, flashing ? 25 : 16, uvY, 9, 9, 256, 256);

		if (flashing)
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + 54, uvY, 9, 9, 256, 256);

		if (absorb > 0) {
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + 144, uvY, 9, 9, 256, 256);
		}
		else {
			RenderUtil.renderCustomSizedTexture(matrix, 0, y, uvX + (currentHealth >= mc.player.getMaxHealth() ? 36 : 45), uvY, 9, 9, 256, 256);
		}
	}

	private static boolean handleHealthState(ClientPlayerEntity player, IngameGui gui, float currentHealth) {
		boolean shouldFlash = gui.healthBlinkTime > (long)gui.tickCount && (gui.healthBlinkTime - (long)gui.tickCount) / 3L %2L == 1L;

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
}
