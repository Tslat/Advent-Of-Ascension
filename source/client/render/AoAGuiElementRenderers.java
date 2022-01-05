package net.tslat.aoa3.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.render.custom.AoAResourceRenderer;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
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

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderGameOverlayEvent.Post.class, AoAGuiElementRenderers::onHudRender);
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

	private static int renderResources(Minecraft mc, MatrixStack matrix, MainWindow window, float partialTicks) {
		int horizontalAdjuster = AoAConfig.CLIENT.hudResourcesHorizontal.get() ? 1 : 0;
		int verticalAdjuster = horizontalAdjuster == 1 ? 0 : 1;
		int potionRenderOffset = 0;
		int x = 0;
		int y = 0;

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

		matrix.pushPose();
		matrix.translate(x, y, 0);

		x = 0;
		y = 0;

		if (AoAKeybinds.statusResourceGui) {
			int lastHeight = 0;

			for (AoAResource.Instance resource : ClientPlayerDataManager.get().getResources()) {
				AoAResourceRenderer renderer = AoAGuiElementRenderers.getResourceRenderer(resource.type());
				x += (renderer.hudRenderWidth(resource) * horizontalAdjuster);
				y += (renderer.hudRenderHeight(resource) * verticalAdjuster);
				lastHeight = y + renderer.hudRenderHeight(resource);

				matrix.pushPose();
				matrix.translate(x, y, 0);
				renderer.renderInHud(matrix, resource, partialTicks, null);
				matrix.popPose();
			}

			y = lastHeight;
		}
		else if (AoAKeybinds.statusResourceGuiMessage & AoAKeybinds.RESOURCE_GUI.getKey().getValue() != -1) {
			matrix.scale(0.5f, 0.5f, 0);
			TranslationTextComponent locale = LocaleUtil.getLocaleMessage("gui.aoa3.resources.showtip", AoAKeybinds.RESOURCE_GUI.getTranslatedKeyMessage());

			RenderUtil.drawCenteredScaledMessage(matrix, mc.font, locale, -(int)(mc.font.width(locale) * 0.75f), (int)((y + 4) / 2f), 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);

			y = y + 4 + mc.font.lineHeight / 2;
		}

		matrix.popPose();

		return (AoAConfig.CLIENT.hudResourcesPosition.get() == AoAResourceRenderer.HudResourcesPosition.Top_Right ? y : 0) + potionRenderOffset;
	}

	private static void renderSkills(Minecraft mc, MatrixStack matrix, MainWindow window, float partialTicks) {
		int x = 0;
		int y = 0;

		matrix.pushPose();
		matrix.translate(window.getGuiScaledWidth(), 0, 0);

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

				matrix.pushPose();
				matrix.translate(x, y, 0);
				renderer.renderInHud(matrix, skill, partialTicks, AoAConfig.CLIENT.hudSkillProgressRenderType.get(), true);
				matrix.popPose();
			}
		}
		else if (AoAKeybinds.statusSkillGuiMessage & AoAKeybinds.SKILL_GUI.getKey().getValue() != -1) {
			matrix.scale(0.5f, 0.5f, 1);
			TranslationTextComponent locale = LocaleUtil.getLocaleMessage("gui.aoa3.skills.showtip", AoAKeybinds.SKILL_GUI.getTranslatedKeyMessage());

			RenderUtil.drawCenteredScaledMessage(matrix, mc.font, locale, -(int)(mc.font.width(locale) * 0.75f), (int)(y / 2f) + 1, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}

		matrix.popPose();
	}

	private static void onHudRender(final RenderGameOverlayEvent.Post ev) {
		if (ev.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			Minecraft mc = Minecraft.getInstance();

			if (mc.player == null || mc.player.isSpectator())
				return;

			MatrixStack matrix = ev.getMatrixStack();

			matrix.pushPose();
			RenderSystem.disableDepthTest();
			RenderSystem.enableAlphaTest();

			int resourcesOffset = renderResources(mc, matrix, mc.getWindow(), ev.getPartialTicks());

			if (resourcesOffset == 0)
				resourcesOffset = RenderUtil.getPotionGuiRenderOffset();

			matrix.translate(0, resourcesOffset, 0);

			renderSkills(mc, matrix, mc.getWindow(), ev.getPartialTicks());

			RenderSystem.enableDepthTest();
			RenderSystem.disableAlphaTest();

			matrix.popPose();
		}
	}
}
