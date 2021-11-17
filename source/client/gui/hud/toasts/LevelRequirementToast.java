package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.aoa3.util.StringUtil;

public class LevelRequirementToast implements IToast {
	private static final ResourceLocation skillsTextures = new ResourceLocation("aoa3", "textures/gui/maingui/skills.png");

	private final AoASkill skill;
	private int iconUvX = 0;
	private int iconUvY = 0;
	private final int levelRequired;
	private final String title;
	private final String subtitle;

	public LevelRequirementToast(AoASkill relevantSkill, int levelRequirement) {
		this.skill = relevantSkill;
		this.levelRequired = levelRequirement;
		this.title = LocaleUtil.getLocaleString("gui.aoatoast.levelReq.title", TextFormatting.DARK_RED);
		this.subtitle = LocaleUtil.getLocaleString("gui.aoatoast.levelReq.subtitle", StringUtil.toSentenceCase(relevantSkill.toString()), String.valueOf(levelRequirement));
	}

	public AoASkill getSkill() {
		return skill;
	}

	public int getLevelReq() {
		return levelRequired;
	}

	@Override
	public Visibility render(MatrixStack matrix, ToastGui toastGui, long delta) {
		toastGui.getMinecraft().getTextureManager().bind(TEXTURE);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		toastGui.blit(matrix, 0, 0, 0, 0, 160, 32);
		toastGui.getMinecraft().getTextureManager().bind(skillsTextures);
		RenderUtil.renderScaledCustomSizedTexture(matrix, 6, 6, iconUvX, iconUvY, 50, 50, 20, 20, 450, 240);
		toastGui.getMinecraft().font.draw(matrix, title, 30, 7, -11534256);
		toastGui.getMinecraft().font.draw(matrix, subtitle, 30, 18, NumberUtil.RGB(255, 255, 255));

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}
}
