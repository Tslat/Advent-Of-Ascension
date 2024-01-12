package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;
import net.tslat.aoa3.util.RenderUtil;

public class LevelRequirementToast implements Toast {
	private static final ResourceLocation BACKGROUND_SPRITE = new ResourceLocation("toast/advancement");

	private final AoASkill skill;
	private final int levelRequired;
	private final Component title;
	private final Component subtitle;

	public LevelRequirementToast(AoASkill relevantSkill, int levelRequirement) {
		this.skill = relevantSkill;
		this.levelRequired = levelRequirement;
		this.title = LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.levelReq.title"), ChatFormatting.DARK_RED);
		this.subtitle = LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.levelReq.subtitle"), relevantSkill.getName(), Component.literal(String.valueOf(levelRequirement)));
	}

	public AoASkill getSkill() {
		return skill;
	}

	public int getLevelReq() {
		return levelRequired;
	}

	@Override
	public Visibility render(GuiGraphics guiGraphics, ToastComponent toastGui, long delta) {
		if (RegistryUtil.getId(this.skill) == null)
			return Visibility.HIDE;

		Minecraft mc = toastGui.getMinecraft();
		PoseStack poseStack = guiGraphics.pose();
		RenderContext renderContext = RenderContext.of(guiGraphics);

		RenderUtil.resetShaderColour();
		guiGraphics.blitSprite(BACKGROUND_SPRITE, 0, 0, width(), height());
		poseStack.pushPose();
		poseStack.scale(0.9f, 0.9f, 1f);
		poseStack.translate(5.5f, 5, 0);
		AoAGuiElementRenderers.getSkillRenderer(skill).renderInHud(renderContext, ClientPlayerDataManager.get().getSkill(skill), mc.getDeltaFrameTime(), AoASkillRenderer.ProgressRenderType.None, false);
		poseStack.popPose();
		renderContext.renderText(title, 30, 7, -11534256, RenderUtil.TextRenderType.NORMAL);

		int subtitleWidth = mc.font.width(subtitle);

		if (subtitleWidth <= 125f) {
			renderContext.renderText(subtitle, 30, 18, ColourUtil.WHITE, RenderUtil.TextRenderType.NORMAL);
		}
		else {
			poseStack.pushPose();

			float scale = 125f / subtitleWidth;

			poseStack.scale(scale, scale, 1);
			renderContext.renderText(subtitle, 30 / scale, 18 / scale, ColourUtil.WHITE, RenderUtil.TextRenderType.NORMAL);
			poseStack.popPose();
		}

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}
}
