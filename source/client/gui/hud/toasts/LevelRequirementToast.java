package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;

public class LevelRequirementToast extends GenericToast {
	private final AoASkill skill;

	public LevelRequirementToast(AoASkill relevantSkill, int levelRequirement) {
		super(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.levelReq.title"), ChatFormatting.DARK_RED), LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.levelReq.subtitle"), relevantSkill.getName(), Component.literal(String.valueOf(levelRequirement))));

		this.skill = relevantSkill;
	}

	@Override
	protected boolean stillValid() {
		return RegistryUtil.getId(this.skill) != null;
	}

	@Override
	protected void drawIcon(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
		final PoseStack poseStack = guiGraphics.pose();

		poseStack.pushPose();
		poseStack.scale(0.9f, 0.9f, 1f);
		poseStack.translate(5.5f, 5, 0);
		AoAGuiElementRenderers.getSkillRenderer(this.skill).renderInHud(RenderContext.of(guiGraphics), ClientPlayerDataManager.get().getSkill(this.skill), Minecraft.getInstance().getDeltaFrameTime(), AoASkillRenderer.ProgressRenderType.None, false);
		poseStack.popPose();
	}
}
