package net.tslat.aoa3.client.render;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.render.custom.AoAResourceRenderer;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.client.render.custom.EnergyResourceRenderer;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.library.object.RenderContext;
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
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterGuiOverlaysEvent.class, ev -> {
			ev.registerAbove(VanillaGuiOverlay.POTION_ICONS.id(), AdventOfAscension.id("aoa_resources"), AoAGuiElementRenderers::renderResources);
			ev.registerAbove(VanillaGuiOverlay.POTION_ICONS.id(), AdventOfAscension.id("aoa_skills"), AoAGuiElementRenderers::renderSkills);
		});
	}

	public static void lateInit() {
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

	private static void renderResources(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int width, int height) {
		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		int horizontalAdjuster = AoAConfigs.CLIENT.hudResourcesHorizontal.get() ? 1 : 0;
		int verticalAdjuster = horizontalAdjuster == 1 ? 0 : 1;
		int potionRenderOffset = 0;
		int x = 0;
		int y = 0;
		PoseStack poseStack = guiGraphics.pose();

		if (mc.player == null || mc.player.isSpectator())
			return;

		if (AoAConfigs.CLIENT.hudResourcesPosition.get() == AoAResourceRenderer.HudResourcesPosition.Top_Right) {
			horizontalAdjuster *= -1;
			x = window.getGuiScaledWidth();
			potionRenderOffset = RenderUtil.getPotionGuiRenderOffset();
			y = potionRenderOffset;

			if (horizontalAdjuster == 0)
				x -= 25;
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

			RenderUtil.renderCenteredScaledText(poseStack, locale, -(int)(mc.font.width(locale) * 0.75f), (int)((y + 4) / 2f), 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);

			y = y + 4 + mc.font.lineHeight / 2;
		}

		poseStack.popPose();

		resourcesRenderHeightOffset = (AoAConfigs.CLIENT.hudResourcesPosition.get() == AoAResourceRenderer.HudResourcesPosition.Top_Right ? y : 0) + potionRenderOffset;
	}

	private static void renderSkills(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int width, int height) {
		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		int x = 0;
		int y = 0;
		PoseStack poseStack = guiGraphics.pose();

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
				renderer.renderInHud(RenderContext.of(guiGraphics), skill, partialTick, AoAConfigs.CLIENT.hudSkillProgressRenderType.get(), true);
				poseStack.popPose();
			}
		}
		else if (AoAKeybinds.statusSkillGuiMessage & AoAKeybinds.SKILL_GUI.getKey().getValue() != -1) {
			poseStack.scale(0.5f, 0.5f, 1);
			MutableComponent locale = LocaleUtil.getLocaleMessage("gui.aoa3.skills.showtip", AoAKeybinds.SKILL_GUI.getTranslatedKeyMessage());

			RenderUtil.renderCenteredScaledText(poseStack, locale, -(int)(mc.font.width(locale) * 0.75f), (int)(y / 2f) + 1, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
		}

		RenderSystem.enableDepthTest();
		poseStack.popPose();
	}
}
