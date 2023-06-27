package net.tslat.aoa3.client.render.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.*;

public interface AoASkillRenderer {
	ResourceLocation SKILL_ICON_BASE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/aoaskill/skill_icon_base.png");

	void renderInHud(RenderContext renderContext, AoASkill.Instance skill, float partialTicks, ProgressRenderType progressRenderType, boolean renderLevel);
	void renderInGui(RenderContext renderContext, AoASkill.Instance skill, float partialTicks, int mouseX, int mouseY, ProgressRenderType progressRenderType, boolean renderLevel);

	default int hudRenderWidth(AoASkill.Instance skill) {
		return 25;
	}

	default int hudRenderHeight(AoASkill.Instance skill) {
		return 25;
	}

	default int guiRenderWidth(AoASkill.Instance skill) {
		return 25;
	}

	default int guiRenderHeight(AoASkill.Instance skill) {
		return 25;
	}

	AoASkillRenderer DEFAULT = new AoASkillRenderer() {
		@Override
		public void renderInHud(RenderContext renderContext, AoASkill.Instance skill, float partialTicks, ProgressRenderType progressRenderType, boolean renderLevel) {
			PoseStack poseStack = renderContext.getGuiGraphics().pose();

			poseStack.pushPose();
			poseStack.scale(0.5f, 0.5f, 0);
			RenderSystem.setShaderColor(1, 1, 1, 1);
			RenderSystem.setShader(GameRenderer::getPositionTexShader);

			Font fontRenderer = Minecraft.getInstance().font;
			int renderWidth = hudRenderWidth(skill) * 2;
			int renderHeight = hudRenderHeight(skill) * 2;
			int level = skill.getLevel(true);

			RenderSystem.setShaderTexture(0, SKILL_ICON_BASE);
			RenderUtil.renderScaledCustomSizedTexture(poseStack, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
			RenderSystem.setShaderTexture(0, new ResourceLocation(RegistryUtil.getId(skill.type()).getNamespace(), "textures/gui/aoaskill/" + RegistryUtil.getId(skill.type()).getPath() + ".png"));
			RenderUtil.renderScaledCustomSizedTexture(poseStack, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth, renderHeight);
			RenderSystem.setShaderTexture(0, SKILL_ICON_BASE);

			if (level == 1000) {
				RenderUtil.renderScaledCustomSizedTexture(poseStack, 0, 0, renderWidth * 3, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
			}
			else {
				float percentComplete = PlayerUtil.getLevelProgressPercentage(skill.getLevel(true), skill.getXp()) / 100f;

				RenderUtil.renderScaledCustomSizedTexture(poseStack, 0, 0, renderWidth, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);

				switch (progressRenderType) {
					case Ring -> RenderUtil.renderScaledCustomSizedTexture(poseStack, 0, renderHeight - percentComplete * renderHeight, renderWidth * 2, renderHeight - percentComplete * renderHeight, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
					case Percent -> {
						String percentMessage = NumberUtil.roundToNthDecimalPlace(percentComplete * 100, 0) + "%";
						RenderUtil.renderScaledText(poseStack, Component.literal(percentMessage), (int)(renderWidth - fontRenderer.width(percentMessage) * 1.5f), (int)(renderHeight - fontRenderer.lineHeight * 1.5f), 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
					}
					case Bar -> {
						RenderUtil.renderScaledCustomSizedTexture(poseStack, 0, renderHeight - 10, 0, 50, 100, 20, renderWidth, 10, renderWidth * 4, renderHeight * 2);
						RenderUtil.renderScaledCustomSizedTexture(poseStack, 0, renderHeight - 10, 0, 70, percentComplete * 100, 20, percentComplete * renderWidth, 10, renderWidth * 4, renderHeight * 2);
					}
					default -> {
					}
				}
			}

			if (renderLevel) {
				RenderUtil.renderScaledText(poseStack, Component.literal(String.valueOf(level)), (int)(renderWidth - fontRenderer.width(String.valueOf(level)) * 1.5f), 1, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);

				if (skill.getCycles() > 0)
					RenderUtil.renderScaledText(poseStack, Component.literal(String.valueOf(skill.getCycles())), 0, 1, 1.5f, ColourUtil.RGB(252, 170, 0), RenderUtil.TextRenderType.OUTLINED);
			}

			poseStack.popPose();
		}

		@Override
		public void renderInGui(RenderContext renderContext, AoASkill.Instance skill, float partialTicks, int mouseX, int mouseY, ProgressRenderType progressRenderType, boolean renderLevel) {
			renderInHud(renderContext, skill, partialTicks, progressRenderType, renderLevel);
		}
	};

	enum ProgressRenderType {
		Ring,
		Bar,
		Percent,
		None
	}
}
