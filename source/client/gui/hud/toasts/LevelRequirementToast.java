package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

public class LevelRequirementToast implements Toast {
	private final AoASkill skill;
	private final int levelRequired;
	private final String title;
	private final String subtitle;

	public LevelRequirementToast(AoASkill relevantSkill, int levelRequirement) {
		this.skill = relevantSkill;
		this.levelRequired = levelRequirement;
		this.title = LocaleUtil.getLocaleString("gui.aoatoast.levelReq.title", ChatFormatting.DARK_RED);
		this.subtitle = LocaleUtil.getLocaleString("gui.aoatoast.levelReq.subtitle", relevantSkill.getName().getString(), String.valueOf(levelRequirement));
	}

	public AoASkill getSkill() {
		return skill;
	}

	public int getLevelReq() {
		return levelRequired;
	}

	@Override
	public Visibility render(PoseStack matrix, ToastComponent toastGui, long delta) {
		Minecraft mc = toastGui.getMinecraft();

		RenderUtil.prepRenderTexture(TEXTURE);
		RenderUtil.resetShaderColour();
		toastGui.blit(matrix, 0, 0, 0, 0, 160, 32);
		matrix.pushPose();
		matrix.scale(0.9f, 0.9f, 1f);
		matrix.translate(5.5f, 5, 0);
		AoAGuiElementRenderers.getSkillRenderer(skill).renderInHud(matrix, ClientPlayerDataManager.get().getSkill(skill), mc.getDeltaFrameTime(), AoASkillRenderer.ProgressRenderType.None, false);
		matrix.popPose();
		mc.font.draw(matrix, title, 30, 7, -11534256);

		int subtitleWidth = mc.font.width(subtitle);

		if (subtitleWidth <= 125f) {
			mc.font.draw(matrix, subtitle, 30, 18, ColourUtil.WHITE);
		}
		else {
			matrix.pushPose();

			float scale = 125f / subtitleWidth;

			matrix.scale(scale, scale, 1);
			mc.font.draw(matrix, subtitle, 30 / scale, 18 / scale, ColourUtil.WHITE);
			matrix.popPose();
		}

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}
}
