package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.aoa3.util.StringUtil;
import net.tslat.aoa3.util.constant.Skills;

public class LevelRequirementToast implements IToast {
	private static final ResourceLocation skillsTextures = new ResourceLocation("aoa3", "textures/gui/maingui/skills.png");

	private final Skills skill;
	private int iconUvX = 0;
	private int iconUvY = 0;
	private final int levelRequired;
	private final String title;
	private final String subtitle;

	public LevelRequirementToast(Skills relevantSkill, int levelRequirement) {
		this.skill = relevantSkill;
		this.levelRequired = levelRequirement;
		this.title = LocaleUtil.getLocaleString("gui.aoatoast.levelReq.title", TextFormatting.DARK_RED);
		this.subtitle = LocaleUtil.getLocaleString("gui.aoatoast.levelReq.subtitle", StringUtil.toSentenceCase(relevantSkill.toString()), String.valueOf(levelRequirement));

		applyIconUvs(relevantSkill);
	}

	public Skills getSkill() {
		return skill;
	}

	public int getLevelReq() {
		return levelRequired;
	}

	@Override
	public Visibility draw(ToastGui toastGui, long delta) {

		toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		toastGui.blit(0, 0, 0, 0, 160, 32);
		toastGui.getMinecraft().getTextureManager().bindTexture(skillsTextures);
		RenderUtil.renderScaledCustomSizedTexture(6, 6, iconUvX, iconUvY, 50, 50, 20, 20, 450, 240);
		toastGui.getMinecraft().fontRenderer.drawString(title, 30, 7, -11534256);
		toastGui.getMinecraft().fontRenderer.drawString(subtitle, 30, 18, NumberUtil.RGB(255, 255, 255));

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}

	private void applyIconUvs(Skills skill) {
		switch (skill) {
			case ALCHEMY:
				iconUvX = 0;
				iconUvY = 0;
				break;
			case ANIMA:
				iconUvX = 50;
				iconUvY = 0;
				break;
			case AUGURY:
				iconUvX = 100;
				iconUvY = 0;
				break;
			case BUTCHERY:
				iconUvX = 150;
				iconUvY = 0;
				break;
			case CREATION:
				iconUvX = 200;
				iconUvY = 0;
				break;
			case ENGINEERING:
				iconUvX = 250;
				iconUvY = 0;
				break;
			case EXPEDITION:
				iconUvX = 300;
				iconUvY = 0;
				break;
			case EXTRACTION:
				iconUvX = 350;
				iconUvY = 0;
				break;
			case FORAGING:
				iconUvX = 400;
				iconUvY = 0;
				break;
			case HAULING:
				iconUvX = 0;
				iconUvY = 100;
				break;
			case HUNTER:
				iconUvX = 50;
				iconUvY = 100;
				break;
			case INFUSION:
				iconUvX = 100;
				iconUvY = 100;
				break;
			case INNERVATION:
				iconUvX = 150;
				iconUvY = 100;
				break;
			case LOGGING:
				iconUvX = 200;
				iconUvY = 100;
				break;
			case RUNATION:
				iconUvX = 250;
				iconUvY = 100;
				break;
		}
	}
}
