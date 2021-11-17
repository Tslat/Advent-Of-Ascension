package net.tslat.aoa3.client.render.custom;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RenderUtil;

public interface AoASkillRenderer {
	ResourceLocation SKILL_ICON_BASE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/aoaskill/skill_icon_base.png");

	void renderInHud(MatrixStack matrix, AoASkill.Instance skill, float partialTicks, ProgressRenderType progressRenderType, boolean renderLevel);
	void renderInGui(MatrixStack matrix, AoASkill.Instance skill, float partialTicks, int mouseX, int mouseY, ProgressRenderType progressRenderType, boolean renderLevel);

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
		public void renderInHud(MatrixStack matrix, AoASkill.Instance skill, float partialTicks, ProgressRenderType progressRenderType, boolean renderLevel) {
			matrix.pushPose();
			matrix.scale(0.5f, 0.5f, 0);
			RenderSystem.color4f(1, 1, 1, 1);

			TextureManager textureManager = Minecraft.getInstance().getTextureManager();
			FontRenderer fontRenderer = Minecraft.getInstance().font;
			int renderWidth = hudRenderWidth(skill) * 2;
			int renderHeight = hudRenderHeight(skill) * 2;
			int level = skill.getLevel(true);

			textureManager.bind(SKILL_ICON_BASE);
			RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
			textureManager.bind(new ResourceLocation(skill.type().getRegistryName().getNamespace(), "textures/gui/aoaskill/" + skill.type().getRegistryName().getPath() + ".png"));
			RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth, renderHeight);
			textureManager.bind(SKILL_ICON_BASE);

			if (level == 1000) {
				RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, renderWidth * 3, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
			}
			else {
				float percentComplete = PlayerUtil.getLevelProgressPercentage(skill.getLevel(true), skill.getXp()) / 100f;

				RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, renderWidth, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);

				switch (progressRenderType) {
					case Ring:
						RenderUtil.renderScaledCustomSizedTexture(matrix, 0, renderHeight - percentComplete * renderHeight, renderWidth * 2, renderHeight - percentComplete * renderHeight, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
						break;
					case Percent:
						String percentMessage = NumberUtil.roundToNthDecimalPlace(percentComplete * 100, 0) + "%";
						RenderUtil.drawScaledString(matrix, fontRenderer, percentMessage, (int)(renderWidth - fontRenderer.width(percentMessage) * 1.5f), (int)(renderHeight - fontRenderer.lineHeight * 1.5f), 1.5f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
						break;
					case Bar:
						RenderUtil.renderScaledCustomSizedTexture(matrix, 0, renderHeight - 10, 0, 50, 100, 20, renderWidth, 10, renderWidth * 4, renderHeight * 2);
						RenderUtil.renderScaledCustomSizedTexture(matrix, 0, renderHeight - 10, 0, 70, percentComplete * 100, 20, percentComplete * renderWidth, 10, renderWidth * 4, renderHeight * 2);
						break;
					case None:
					default:
						break;
				}
			}

			if (renderLevel) {
				RenderUtil.drawScaledString(matrix, fontRenderer, String.valueOf(level), (int)(renderWidth - fontRenderer.width(String.valueOf(level)) * 1.5f), 1, 1.5f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);

				if (skill.getCycles() > 0)
					RenderUtil.drawScaledString(matrix, fontRenderer, String.valueOf(skill.getCycles()), 0, 1, 1.5f, NumberUtil.RGB(252, 170, 0), RenderUtil.StringRenderType.OUTLINED);
			}

			matrix.popPose();
		}

		@Override
		public void renderInGui(MatrixStack matrix, AoASkill.Instance skill, float partialTicks, int mouseX, int mouseY, ProgressRenderType progressRenderType, boolean renderLevel) {
			renderInHud(matrix, skill, partialTicks, progressRenderType, renderLevel);
		}
	};

	enum ProgressRenderType {
		Ring,
		Bar,
		Percent,
		None
	}
}
