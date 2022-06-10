package net.tslat.aoa3.client.render;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.render.custom.AoAResourceRenderer;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.client.render.custom.EnergyResourceRenderer;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.HashMap;

public final class AoAGuiElementRenderers {
	private static final HashMap<AoASkill, AoASkillRenderer> SKILL_RENDERERS = new HashMap<AoASkill, AoASkillRenderer>();
	private static final HashMap<AoAResource, AoAResourceRenderer> RESOURCE_RENDERERS = new HashMap<AoAResource, AoAResourceRenderer>();

	public static int resourcesRenderHeightOffset = 0;

	public static void init() {
		OverlayRegistry.registerOverlayTop("AoA Resources", AoAGuiElementRenderers::renderResources);
		OverlayRegistry.registerOverlayTop("AoA Skills", AoAGuiElementRenderers::renderSkills);
		registerResourceRenderer(AoAResources.ENERGY.get(), new EnergyResourceRenderer());
	}

	public static AoAResourceRenderer getResourceRenderer(AoAResource resource) {
		return RESOURCE_RENDERERS.getOrDefault(resource, AoAResourceRenderer.DEFAULT);
	}

	public static AoASkillRenderer getSkillRenderer(AoASkill skill) {
		return SKILL_RENDERERS.getOrDefault(skill, AoASkillRenderer.DEFAULT);
	}

	public static void registerResourceRenderer(AoAResource resource, AoAResourceRenderer renderer) {
		RESOURCE_RENDERERS.put(resource, renderer);
	}

	public static void registerSkillRenderer(AoASkill skill, AoASkillRenderer renderer) {
		SKILL_RENDERERS.put(skill, renderer);
	}

	private static void renderResources(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		int horizontalAdjuster = AoAConfig.CLIENT.hudResourcesHorizontal.get() ? 1 : 0;
		int verticalAdjuster = horizontalAdjuster == 1 ? 0 : 1;
		int potionRenderOffset = 0;
		int x = 0;
		int y = 0;

		if (mc.player == null || mc.player.isSpectator())
			return;

		switch (AoAConfig.CLIENT.hudResourcesPosition.get()) {
			case Bottom_Right:
				horizontalAdjuster *= -1;
				verticalAdjuster *= -1;
				x = window.getGuiScaledWidth();
				y = window.getGuiScaledHeight();
				break;
			case Bottom_Left:
				verticalAdjuster *= -1;
				y = window.getGuiScaledHeight();
				break;
			case Top_Right:
				horizontalAdjuster *= -1;
				x = window.getGuiScaledWidth();
				potionRenderOffset = RenderUtil.getPotionGuiRenderOffset();
				y = potionRenderOffset;
			case Top_Left:
			default:
				break;
		}

		poseStack.pushPose();
		poseStack.translate(x, y, 0);

		x = 0;
		y = 0;

		if (AoAKeybinds.statusResourceGui) {
			int lastHeight = 0;

			for (AoAResource.Instance resource : ClientPlayerDataManager.get().getResources()) {
				AoAResourceRenderer renderer = AoAGuiElementRenderers.getResourceRenderer(resource.type());
				x += (renderer.hudRenderWidth(resource) * horizontalAdjuster);
				y += (renderer.hudRenderHeight(resource) * verticalAdjuster);
				lastHeight = y + renderer.hudRenderHeight(resource);

				poseStack.pushPose();
				poseStack.translate(x, y, 0);
				renderer.renderInHud(poseStack, resource, partialTick, null);
				poseStack.popPose();
			}

			y = lastHeight;
		}
		else if (AoAKeybinds.statusResourceGuiMessage & AoAKeybinds.RESOURCE_GUI.getKey().getValue() != -1) {
			poseStack.scale(0.5f, 0.5f, 0);
			MutableComponent locale = LocaleUtil.getLocaleMessage("gui.aoa3.resources.showtip", AoAKeybinds.RESOURCE_GUI.getTranslatedKeyMessage());

			RenderUtil.drawCenteredScaledMessage(poseStack, mc.font, locale, -(int)(mc.font.width(locale) * 0.75f), (int)((y + 4) / 2f), 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);

			y = y + 4 + mc.font.lineHeight / 2;
		}

		poseStack.popPose();

		resourcesRenderHeightOffset = (AoAConfig.CLIENT.hudResourcesPosition.get() == AoAResourceRenderer.HudResourcesPosition.Top_Right ? y : 0) + potionRenderOffset;
	}

	private static void renderSkills(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		int x = 0;
		int y = 0;

		if (mc.player == null || mc.player.isSpectator())
			return;

		poseStack.pushPose();
		poseStack.translate(window.getGuiScaledWidth(), resourcesRenderHeightOffset, 0);
		RenderSystem.disableDepthTest();

		if (AoAKeybinds.statusSkillGui) {
			int maxHeight = 0;
			int cumulativeXOffset = 0;

			for (AoASkill.Instance skill : ClientPlayerDataManager.get().getSkills()) {
				AoASkillRenderer renderer = AoAGuiElementRenderers.getSkillRenderer(skill.type());
				int xOffset = renderer.hudRenderWidth(skill);
				cumulativeXOffset += xOffset;
				x -= xOffset;
				maxHeight = Math.max(maxHeight, renderer.hudRenderHeight(skill));

				if (cumulativeXOffset >= xOffset * 6) {
					y += maxHeight;
					x += cumulativeXOffset - xOffset;
					maxHeight = 0;
					cumulativeXOffset = 0;
				}

				poseStack.pushPose();
				poseStack.translate(x, y, 0);
				renderer.renderInHud(poseStack, skill, partialTick, AoAConfig.CLIENT.hudSkillProgressRenderType.get(), true);
				poseStack.popPose();
			}
		}
		else if (AoAKeybinds.statusSkillGuiMessage & AoAKeybinds.SKILL_GUI.getKey().getValue() != -1) {
			poseStack.scale(0.5f, 0.5f, 1);
			MutableComponent locale = LocaleUtil.getLocaleMessage("gui.aoa3.skills.showtip", AoAKeybinds.SKILL_GUI.getTranslatedKeyMessage());

			RenderUtil.drawCenteredScaledMessage(poseStack, mc.font, locale, -(int)(mc.font.width(locale) * 0.75f), (int)(y / 2f) + 1, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}

		RenderSystem.enableDepthTest();
		poseStack.popPose();
	}
}
