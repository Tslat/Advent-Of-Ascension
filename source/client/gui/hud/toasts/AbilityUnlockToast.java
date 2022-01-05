package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class AbilityUnlockToast implements IToast {
	private final AoASkill skill;
	private final AoAAbility ability;
	private final String title;
	private final String subtitle;

	public AbilityUnlockToast(AoASkill relevantSkill, AoAAbility ability) {
		this.skill = relevantSkill;
		this.ability = ability;
		this.title = LocaleUtil.getLocaleString("gui.aoatoast.abilityUnlock.title", TextFormatting.DARK_RED);
		this.subtitle = LocaleUtil.getLocaleString("gui.aoatoast.abilityUnlock.subtitle", ability.getName().getString());
	}

	public AoASkill getSkill() {
		return skill;
	}

	public AoAAbility getAbility() {
		return ability;
	}

	@Override
	public Visibility render(MatrixStack matrix, ToastGui toastGui, long delta) {
		Minecraft mc = toastGui.getMinecraft();

		mc.getTextureManager().bind(TEXTURE);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		toastGui.blit(matrix, 0, 0, 0, 0, 160, 32);
		matrix.pushPose();
		matrix.scale(0.9f, 0.9f, 1f);
		matrix.translate(5.5f, 5, 0);
		AoAGuiElementRenderers.getSkillRenderer(skill).renderInHud(matrix, ClientPlayerDataManager.get().getSkill(skill), mc.getDeltaFrameTime(), AoASkillRenderer.ProgressRenderType.None, false);
		matrix.popPose();
		mc.font.draw(matrix, title, 30, 7, 1488129);
		mc.font.draw(matrix, subtitle, 30, 18, ColourUtil.WHITE);

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}
}
