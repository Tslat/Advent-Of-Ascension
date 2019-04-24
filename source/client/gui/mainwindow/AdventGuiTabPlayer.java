package net.tslat.aoa3.client.gui.mainwindow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.PacketExpeditionToggle;
import net.tslat.aoa3.entity.boss.skeletalarmy.EntityEliteSkelePig;
import net.tslat.aoa3.entity.mobs.abyss.EntityOcculent;
import net.tslat.aoa3.entity.mobs.candyland.EntityGingerbreadMan;
import net.tslat.aoa3.entity.mobs.candyland.EntitySpearmintSlug;
import net.tslat.aoa3.entity.mobs.celeve.*;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSkeleton;
import net.tslat.aoa3.entity.mobs.overworld.*;
import net.tslat.aoa3.entity.mobs.shyrelands.EntityArcworm;
import net.tslat.aoa3.entity.mobs.shyrelands.EntityShyreTroll;
import net.tslat.aoa3.entity.mobs.voxponds.EntityAlarmo;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.AuguryUtil;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class AdventGuiTabPlayer extends GuiScreen {
	private static final ResourceLocation resourcesTextures = new ResourceLocation("aoa3", "textures/gui/maingui/resources.png");
	private static final ResourceLocation skillsTextures = new ResourceLocation("aoa3", "textures/gui/maingui/skills.png");

	private static int levelAlchemy = 1;
	private static float xpAlchemy = 0;
	private static int percentCompleteAlchemy = 0;
	private static int levelAnima = 1;
	private static float xpAnima = 0;
	private static int percentCompleteAnima = 0;
	private static int levelAugury = 1;
	private static float xpAugury = 0;
	private static int percentCompleteAugury = 0;
	private static int levelButchery = 1;
	private static float xpButchery = 0;
	private static int percentCompleteButchery = 0;
	private static int levelCreation = 1;
	private static float xpCreation = 0;
	private static int percentCompleteCreation = 0;
	private static int levelEngineering = 1;
	private static float xpEngineering = 0;
	private static int percentCompleteEngineering = 0;
	private static int levelExpedition = 1;
	private static float xpExpedition = 0;
	private static int percentCompleteExpedition = 0;
	private static int optExpedition = 0;
	public static int levelExtraction = 1;
	private static float xpExtraction = 0;
	private static int percentCompleteExtraction = 0;
	private static int levelForaging = 1;
	private static float xpForaging = 0;
	private static int percentCompleteForaging = 0;
	private static int levelHauling = 1;
	private static float xpHauling = 0;
	private static int percentCompleteHauling = 0;
	private static int levelHunter = 1;
	private static float xpHunter = 0;
	private static int percentCompleteHunter = 0;
	private static int levelInfusion = 1;
	private static float xpInfusion = 0;
	private static int percentCompleteInfusion = 0;
	private static int levelInnervation = 1;
	private static float xpInnervation = 0;
	private static int percentCompleteInnervation = 0;
	private static int levelLogging = 1;
	private static float xpLogging = 0;
	private static int percentCompleteLogging = 0;
	private static int levelRunation = 1;
	private static float xpRunation = 0;
	private static int percentCompleteRunation = 0;

	public static int tributeErebon = 0;
	public static int tributeLuxon = 0;
	public static int tributePluton = 0;
	public static int tributeSelyan = 0;

	public static float resourceEnergy = 0;
	public static float resourceCreation = 0;
	public static float resourceSoul = 0;
	public static float resourceRage = 0;

	private int adjustedMouseX;
	private int adjustedMouseY;

	private EntityLivingBase entityToRender = null;

	@Override
	public void initGui() {
		super.initGui();

		setRenderEntity();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.adjustedMouseX = (int)(mouseX * AdventMainGui.scaleInverse);
		this.adjustedMouseY = (int)(mouseY * AdventMainGui.scaleInverse);

		drawPlayerBox(AdventMainGui.scaledTabRootX + 50, AdventMainGui.scaledTabRootY + 330, mouseX, mouseY, (int)(30 * AdventMainGui.scaleInverse));
		renderResources();
		renderSkills();
	}

	private void renderResources() {
		int x = AdventMainGui.scaledTabRootX + 5;
		int y = AdventMainGui.scaledTabRootY + 20;
		float percentComplete = tributeSelyan / 200f;

		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.tribute"), x + 50, y - 15, 1.5625f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		mc.getTextureManager().bindTexture(resourcesTextures);
		drawScaledCustomSizeModalRect(x, y, 0, 0, 100, 30, 100, 30, 400, 490);
		drawScaledCustomSizeModalRect(x, y, 0, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 490);

		percentComplete = tributeLuxon / 200f;

		drawScaledCustomSizeModalRect(x, y + 30, 100, 0, 100, 30, 100, 30, 400, 490);
		drawScaledCustomSizeModalRect(x, y + 30, 100, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 490);

		percentComplete = tributeErebon / 200f;

		drawScaledCustomSizeModalRect(x, y + 60, 200, 0, 100, 30, 100, 30, 400, 490);
		drawScaledCustomSizeModalRect(x, y + 60, 200, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 490);

		percentComplete = tributePluton / 200f;

		drawScaledCustomSizeModalRect(x, y + 90, 300, 0, 100, 30, 100, 30, 400, 490);
		drawScaledCustomSizeModalRect(x, y + 90, 300, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 490);

		percentComplete = resourceRage / 200f;

		drawScaledCustomSizeModalRect(x + 130, y, 0, 190, 50, 50, 62, 62, 400, 490);
		drawScaledCustomSizeModalRect(x + 130, y, resourceRage >= 150 ? 50 : 0, 240, percentComplete * 50, 50, percentComplete * 62, 62, 400, 490);

		percentComplete = resourceEnergy / 200f;

		drawScaledCustomSizeModalRect(x + 130, y + 82, 0, 90, 50, 50, 62, 62, 400, 490);
		drawScaledCustomSizeModalRect(x + 130, y + 82, 0, 140, percentComplete * 50, 50, percentComplete * 62, 62, 400, 490);

		float maxCreation = AuguryUtil.getMaxCreation(levelAugury);
		percentComplete = resourceCreation / maxCreation;

		drawScaledCustomSizeModalRect(x + 130, y + 164, 0, 290, 50, 50, 62, 62, 400, 490);
		drawScaledCustomSizeModalRect(x + 130, y + 164, 0, 340, percentComplete * 50, 50, percentComplete * 62, 62, 400, 490);

		float maxSoul = AuguryUtil.getMaxSoul(levelAugury);
		percentComplete = resourceSoul / maxSoul;

		drawScaledCustomSizeModalRect(x + 130, y + 246, 0, 390, 50, 50, 62, 62, 400, 490);
		drawScaledCustomSizeModalRect(x + 130, y + 246, 0, 440, percentComplete * 50, 50, percentComplete * 62, 62, 400, 490);

		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.selyan"), x + 50, y + 11, 1.25f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.NORMAL);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.luxon"), x + 50, y + 41, 1.25f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.NORMAL);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.erebon"), x + 50, y + 71, 1.25f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.NORMAL);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.pluton"), x + 50, y + 101, 1.25f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.NORMAL);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.rage"), x + 161, y - 15, 1.5625f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.energy"), x + 161, y + 67, 1.5625f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.creation"), x + 161, y + 149, 1.5625f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.soul"), x + 161, y + 231, 1.5625f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, (int)resourceRage + "/" + 200, x + 161, y + 40, 1.125f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, (int)resourceEnergy + "/" + 200, x + 161, y + 122, 1.125f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.numberToSuffixFormat(resourceCreation) + "/" + StringUtil.numberToSuffixFormat(maxCreation), x + 161, y + 204, 1.125f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
		AdventMainGui.drawCenteredScaledString(mc.fontRenderer, StringUtil.numberToSuffixFormat(resourceSoul) + "/" + StringUtil.numberToSuffixFormat(maxSoul), x + 161, y + 286, 1.125f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
	}

	private void renderSkills() {
		int x;
		int y;
		mc.getTextureManager().bindTexture(skillsTextures);

		for (Enums.Skills skill : Enums.Skills.values()) {
			y = AdventMainGui.scaledTabRootY + 20 + (int)(Math.floor(skill.id / 5f) * 112);
			x = AdventMainGui.scaledTabRootX + 170 + (100 * (1 + skill.id % 5));
			int progressBarPercent = 0;
			int uvX = 0;
			int uvY = 0;
			int optionalUvX = -1;
			int optionalUvY = -1;

			switch (skill) {
				case ALCHEMY:
					progressBarPercent = percentCompleteAlchemy;
					uvX = 0;
					uvY = levelAlchemy >= 100 ? 50 : 0;
					break;
				case ANIMA:
					progressBarPercent = percentCompleteAnima;
					uvX = 50;
					uvY = levelAnima >= 100 ? 50 : 0;
					break;
				case AUGURY:
					progressBarPercent = percentCompleteAugury;
					uvX = 100;
					uvY = levelAugury >= 100 ? 50 : 0;
					break;
				case BUTCHERY:
					progressBarPercent = percentCompleteButchery;
					uvX = 150;
					uvY = levelButchery >= 100 ? 50 : 0;
					break;
				case CREATION:
					progressBarPercent = percentCompleteCreation;
					uvX = 200;
					uvY = levelCreation >= 100 ? 50 : 0;
					break;
				case ENGINEERING:
					progressBarPercent = percentCompleteEngineering;
					uvX = 250;
					uvY = levelEngineering >= 100 ? 50 : 0;
					break;
				case EXPEDITION:
					progressBarPercent = percentCompleteExpedition;
					uvX = 300;
					uvY = levelExpedition >= 100 ? 50 : 0;

					switch (optExpedition % 4) {
						case 1:
							optionalUvX = 300;
							optionalUvY = 100;
							break;
						case 2:
							optionalUvX = 324;
							optionalUvY = 100;
							break;
						case 3:
							optionalUvX = 348;
							optionalUvY = 100;
							break;
						default:
							break;
					}
					break;
				case EXTRACTION:
					progressBarPercent = percentCompleteExtraction;
					uvX = 350;
					uvY = levelExtraction >= 100 ? 50 : 0;
					break;
				case FORAGING:
					progressBarPercent = percentCompleteForaging;
					uvX = 400;
					uvY = levelForaging >= 100 ? 50 : 0;
					break;
				case HAULING:
					progressBarPercent = percentCompleteHauling;
					uvX = 0;
					uvY = 100 + (levelHauling >= 100 ? 50 : 0);
					break;
				case HUNTER:
					progressBarPercent = percentCompleteHunter;
					uvX = 50;
					uvY = 100 + (levelHunter >= 100 ? 50 : 0);
					break;
				case INFUSION:
					progressBarPercent = percentCompleteInfusion;
					uvX = 100;
					uvY = 100 + (levelInfusion >= 100 ? 50 : 0);
					break;
				case INNERVATION:
					progressBarPercent = percentCompleteInnervation;
					uvX = 150;
					uvY = 100 + (levelInnervation >= 100 ? 50 : 0);
					break;
				case LOGGING:
					progressBarPercent = percentCompleteLogging;
					uvX = 200;
					uvY = 100 + (levelLogging >= 100 ? 50 : 0);
					break;
				case RUNATION:
					progressBarPercent = percentCompleteRunation;
					uvX = 250;
					uvY = 100 + (levelRunation >= 100 ? 50 : 0);
					break;
			}

			drawScaledCustomSizeModalRect(x, y, uvX, uvY, 50, 50, 62, 62, 450, 240);
			drawScaledCustomSizeModalRect(x, y + 50, 0, 200, 100, 20, 62, 13, 450, 240);
			drawScaledCustomSizeModalRect(x, y + 50, 0, 220, progressBarPercent, 20, progressBarPercent / 100f * 62, 13, 450, 240);

			if (optionalUvX >= 0)
				drawScaledCustomSizeModalRect(x + 38, y, optionalUvX, optionalUvY, 24, 24, 24, 24, 450, 240);
		}

		GlStateManager.color(1f, 1f, 1f, 1f);

		for (Enums.Skills skill : Enums.Skills.values()) {
			y = AdventMainGui.scaledTabRootY + 20 + (int)(Math.floor(skill.id / 5f) * 112);
			x = AdventMainGui.scaledTabRootX + 170 + (100 * (1 + skill.id % 5));
			int lvl = 1;
			float xp = 0;
			String skillName = "";

			switch (skill) {
				case ALCHEMY:
					skillName = StringUtil.getLocaleString("skills.alchemy.name");
					lvl = levelAlchemy;
					xp = xpAlchemy;
					break;
				case ANIMA:
					skillName = StringUtil.getLocaleString("skills.anima.name");
					lvl = levelAnima;
					xp = xpAnima;
					break;
				case AUGURY:
					skillName = StringUtil.getLocaleString("skills.augury.name");
					lvl = levelAugury;
					xp = xpAugury;
					break;
				case BUTCHERY:
					skillName = StringUtil.getLocaleString("skills.butchery.name");
					lvl = levelButchery;
					xp = xpButchery;
					break;
				case CREATION:
					skillName = StringUtil.getLocaleString("skills.creation.name");
					lvl = levelCreation;
					xp = xpCreation;
					break;
				case ENGINEERING:
					skillName = StringUtil.getLocaleString("skills.engineering.name");
					lvl = levelEngineering;
					xp = xpEngineering;
					break;
				case EXPEDITION:
					skillName = StringUtil.getLocaleString("skills.expedition.name");
					lvl = levelExpedition;
					xp = xpExpedition;
					break;
				case EXTRACTION:
					skillName = StringUtil.getLocaleString("skills.extraction.name");
					lvl = levelExtraction;
					xp = xpExtraction;
					break;
				case FORAGING:
					skillName = StringUtil.getLocaleString("skills.foraging.name");
					lvl = levelForaging;
					xp = xpForaging;
					break;
				case HAULING:
					skillName = StringUtil.getLocaleString("skills.hauling.name");
					lvl = levelHauling;
					xp = xpHauling;
					break;
				case HUNTER:
					skillName = StringUtil.getLocaleString("skills.hunter.name");
					lvl = levelHunter;
					xp = xpHunter;
					break;
				case INFUSION:
					skillName = StringUtil.getLocaleString("skills.infusion.name");
					lvl = levelInfusion;
					xp = xpInfusion;
					break;
				case INNERVATION:
					skillName = StringUtil.getLocaleString("skills.innervation.name");
					lvl = levelInnervation;
					xp = xpInnervation;
					break;
				case LOGGING:
					skillName = StringUtil.getLocaleString("skills.logging.name");
					lvl = levelLogging;
					xp = xpLogging;
					break;
				case RUNATION:
					skillName = StringUtil.getLocaleString("skills.runation.name");
					lvl = levelRunation;
					xp = xpRunation;
					break;
			}

			AdventMainGui.drawCenteredScaledString(mc.fontRenderer, skillName, x + 31, y - 15, 1.5625f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.DROP_SHADOW);
			GlStateManager.scale(1.25, 1.25, 1.25);
			mc.fontRenderer.drawString(StringUtil.getLocaleStringWithArguments("gui.aoamain.player.lvl", String.valueOf(ConfigurationUtil.vanityLevels ? lvl : Math.min(lvl, 100))), x * 0.8f, (y + 65) * 0.8f, Enums.RGBIntegers.WHITE, true);
			mc.fontRenderer.drawString(StringUtil.getLocaleStringWithArguments("gui.aoamain.player.xp", StringUtil.numberToSuffixFormat(xp), (!ConfigurationUtil.vanityLevels && lvl >= 100) ? "0" : StringUtil.numberToSuffixFormat(PlayerUtil.getXpRequiredForNextLevel(lvl))), x * 0.8f, (y + 77) * 0.8f, Enums.RGBIntegers.WHITE, true);
			GlStateManager.scale(0.8, 0.8, 0.8);
		}
	}

	private void drawPlayerBox(int posX, int posY, int mouseX, int mouseY, int scale) {
		if (entityToRender == null)
			setRenderEntity();

		GlStateManager.scale(1.6, 1.6, 1.6);

		String name = mc.player.getDisplayNameString();
		AdventMainGui.drawOutlinedText(mc.fontRenderer, name, (int)((posX - 10 - mc.fontRenderer.getStringWidth(name) / 2) * 0.625f), (int)((posY - 170) * 0.625f), Enums.RGBIntegers.WHITE, 1.6f);
		GlStateManager.scale(0.625, 0.625, 0.625);

		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)posX, (float)posY, 100f);
		GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotate(-125.0F, 0.0F, 1.0F, 0.0F);

		float oldRenderYawOffset = entityToRender.renderYawOffset;
		float oldRotationYaw = entityToRender.rotationYaw;
		float oldRotationPitch = entityToRender.rotationPitch;
		float oldPrevRotationYawHead = entityToRender.prevRotationYawHead;
		float oldRotationYawHead = entityToRender.rotationYawHead;
		float oldLimbSwingAmount = entityToRender.limbSwingAmount;
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		entityToRender.renderYawOffset = 0;

		entityToRender.rotationYaw = (float)Math.atan((double)((((AdventMainGui.scaledRootX + 273) / AdventMainGui.scaleInverse) - mouseX) / 40.0F)) * 40.0F;
		entityToRender.rotationPitch = -((float)Math.atan((double)((((AdventMainGui.scaledRootY + 465) / AdventMainGui.scaleInverse) - 50 - mouseY) / 40.0F))) * 20.0F;
		entityToRender.rotationYawHead = entityToRender.rotationYaw;
		entityToRender.prevRotationYawHead = entityToRender.rotationYaw;
		entityToRender.limbSwingAmount = 0;

		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.renderEntity(entityToRender, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
		rendermanager.setRenderShadow(true);

		entityToRender.renderYawOffset = oldRenderYawOffset;
		entityToRender.rotationYaw = oldRotationYaw;
		entityToRender.rotationPitch = oldRotationPitch;
		entityToRender.prevRotationYawHead = oldPrevRotationYawHead;
		entityToRender.rotationYawHead = oldRotationYawHead;
		entityToRender.limbSwingAmount = oldLimbSwingAmount;

		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	public static void setSkillData(Enums.Skills skill, float xp, int lvl, int opt) {
		switch (skill) {
			case ALCHEMY:
				levelAlchemy = lvl;
				xpAlchemy = xp;
				percentCompleteAlchemy = PlayerUtil.getLevelProgressPercentage(levelAlchemy, xpAlchemy);
				break;
			case ANIMA:
				levelAnima = lvl;
				xpAnima = xp;
				percentCompleteAnima = PlayerUtil.getLevelProgressPercentage(levelAnima, xpAnima);
				break;
			case AUGURY:
				levelAugury = lvl;
				xpAugury = xp;
				percentCompleteAugury = PlayerUtil.getLevelProgressPercentage(levelAugury, xpAugury);
				break;
			case BUTCHERY:
				levelButchery = lvl;
				xpButchery = xp;
				percentCompleteButchery = PlayerUtil.getLevelProgressPercentage(levelButchery, xpButchery);
				break;
			case CREATION:
				levelCreation = lvl;
				xpCreation = xp;
				percentCompleteCreation = PlayerUtil.getLevelProgressPercentage(levelCreation, xpCreation);
				break;
			case ENGINEERING:
				levelEngineering = lvl;
				xpEngineering = xp;
				percentCompleteEngineering = PlayerUtil.getLevelProgressPercentage(levelEngineering, xpEngineering);
				break;
			case EXPEDITION:
				levelExpedition = lvl;
				xpExpedition = xp;
				optExpedition = opt;
				percentCompleteExpedition = PlayerUtil.getLevelProgressPercentage(levelExpedition, xpExpedition);
				break;
			case EXTRACTION:
				levelExtraction = lvl;
				xpExtraction = xp;
				percentCompleteExtraction = PlayerUtil.getLevelProgressPercentage(levelExtraction, xpExtraction);
				break;
			case FORAGING:
				levelForaging = lvl;
				xpForaging = xp;
				percentCompleteForaging = PlayerUtil.getLevelProgressPercentage(levelForaging, xpForaging);
				break;
			case HAULING:
				levelHauling = lvl;
				xpHauling = xp;
				percentCompleteHauling = PlayerUtil.getLevelProgressPercentage(levelHauling, xpHauling);
				break;
			case HUNTER:
				levelHunter = lvl;
				xpHunter = xp;
				percentCompleteHunter = PlayerUtil.getLevelProgressPercentage(levelHunter, xpHunter);
				break;
			case INFUSION:
				levelInfusion = lvl;
				xpInfusion = xp;
				percentCompleteInfusion = PlayerUtil.getLevelProgressPercentage(levelInfusion, xpInfusion);
				break;
			case INNERVATION:
				levelInnervation = lvl;
				xpInnervation = xp;
				percentCompleteInnervation = PlayerUtil.getLevelProgressPercentage(levelInnervation, xpInnervation);
				break;
			case LOGGING:
				levelLogging = lvl;
				xpLogging = xp;
				percentCompleteLogging = PlayerUtil.getLevelProgressPercentage(levelLogging, xpLogging);
				break;
			case RUNATION:
				levelRunation = lvl;
				xpRunation = xp;
				percentCompleteRunation = PlayerUtil.getLevelProgressPercentage(levelRunation, xpRunation);
				break;
		}
	}

	public static void addXp(Enums.Skills skill, float additionalXp) {
		switch (skill) {
			case ALCHEMY:
				xpAlchemy += additionalXp;
				percentCompleteAlchemy = PlayerUtil.getLevelProgressPercentage(levelAlchemy, xpAlchemy);
				break;
			case ANIMA:
				xpAnima += additionalXp;
				percentCompleteAnima = PlayerUtil.getLevelProgressPercentage(levelAnima, xpAnima);
				break;
			case AUGURY:
				xpAugury += additionalXp;
				percentCompleteAugury = PlayerUtil.getLevelProgressPercentage(levelAugury, xpAugury);
				break;
			case BUTCHERY:
				xpButchery += additionalXp;
				percentCompleteButchery = PlayerUtil.getLevelProgressPercentage(levelButchery, xpButchery);
				break;
			case CREATION:
				xpCreation += additionalXp;
				percentCompleteCreation = PlayerUtil.getLevelProgressPercentage(levelCreation, xpCreation);
				break;
			case ENGINEERING:
				xpEngineering += additionalXp;
				percentCompleteEngineering = PlayerUtil.getLevelProgressPercentage(levelEngineering, xpEngineering);
				break;
			case EXPEDITION:
				xpExpedition += additionalXp;
				percentCompleteExpedition = PlayerUtil.getLevelProgressPercentage(levelExpedition, xpExpedition);
				break;
			case EXTRACTION:
				xpExtraction += additionalXp;
				percentCompleteExtraction = PlayerUtil.getLevelProgressPercentage(levelExtraction, xpExtraction);
				break;
			case FORAGING:
				xpForaging += additionalXp;
				percentCompleteForaging = PlayerUtil.getLevelProgressPercentage(levelForaging, xpForaging);
				break;
			case HAULING:
				xpHauling += additionalXp;
				percentCompleteHauling = PlayerUtil.getLevelProgressPercentage(levelHauling, xpHauling);
				break;
			case HUNTER:
				xpHunter += additionalXp;
				percentCompleteHunter = PlayerUtil.getLevelProgressPercentage(levelHunter, xpHunter);
				break;
			case INFUSION:
				xpInfusion += additionalXp;
				percentCompleteInfusion = PlayerUtil.getLevelProgressPercentage(levelInfusion, xpInfusion);
				break;
			case INNERVATION:
				xpInnervation += additionalXp;
				percentCompleteInnervation = PlayerUtil.getLevelProgressPercentage(levelInnervation, xpInnervation);
				break;
			case LOGGING:
				xpLogging += additionalXp;
				percentCompleteLogging = PlayerUtil.getLevelProgressPercentage(levelLogging, xpLogging);
				break;
			case RUNATION:
				xpRunation += additionalXp;
				percentCompleteRunation = PlayerUtil.getLevelProgressPercentage(levelRunation, xpRunation);
				break;
		}
	}

	public static int getSkillLevel(Enums.Skills skill) {
		switch (skill) {
			case ALCHEMY:
				return levelAlchemy;
			case ANIMA:
				return levelAnima;
			case AUGURY:
				return levelAugury;
			case BUTCHERY:
				return levelButchery;
			case CREATION:
				return levelCreation;
			case ENGINEERING:
				return levelEngineering;
			case EXPEDITION:
				return levelExpedition;
			case EXTRACTION:
				return levelExtraction;
			case FORAGING:
				return levelForaging;
			case HAULING:
				return levelHauling;
			case HUNTER:
				return levelHunter;
			case INFUSION:
				return levelInfusion;
			case INNERVATION:
				return levelInnervation;
			case LOGGING:
				return levelLogging;
			case RUNATION:
				return levelRunation;
			default:
				return 1;
		}
	}

	public static float getSkillXp(Enums.Skills skill) {
		switch (skill) {
			case ALCHEMY:
				return xpAlchemy;
			case ANIMA:
				return xpAnima;
			case AUGURY:
				return xpAugury;
			case BUTCHERY:
				return xpButchery;
			case CREATION:
				return xpCreation;
			case ENGINEERING:
				return xpEngineering;
			case EXPEDITION:
				return xpExpedition;
			case EXTRACTION:
				return xpExtraction;
			case FORAGING:
				return xpForaging;
			case HAULING:
				return xpHauling;
			case HUNTER:
				return xpHunter;
			case INFUSION:
				return xpInfusion;
			case INNERVATION:
				return xpInnervation;
			case LOGGING:
				return xpLogging;
			case RUNATION:
				return xpRunation;
			default:
				return 0;
		}
	}

	public static int getPercentCompleteLevel(Enums.Skills skill) {
		switch (skill) {
			case ALCHEMY:
				return percentCompleteAlchemy;
			case ANIMA:
				return percentCompleteAnima;
			case AUGURY:
				return percentCompleteAugury;
			case BUTCHERY:
				return percentCompleteButchery;
			case CREATION:
				return percentCompleteCreation;
			case ENGINEERING:
				return percentCompleteEngineering;
			case EXPEDITION:
				return percentCompleteExpedition;
			case EXTRACTION:
				return percentCompleteExtraction;
			case FORAGING:
				return percentCompleteForaging;
			case HAULING:
				return percentCompleteHauling;
			case HUNTER:
				return percentCompleteHunter;
			case INFUSION:
				return percentCompleteInfusion;
			case INNERVATION:
				return percentCompleteInnervation;
			case LOGGING:
				return percentCompleteLogging;
			case RUNATION:
				return percentCompleteRunation;
			default:
				return 0;
		}
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();

		if (!(entityToRender instanceof EntityPlayer)) {
			entityToRender.setDead();
			entityToRender = null;
		}
		else {
			entityToRender = null;
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		int expeditionIconY = AdventMainGui.scaledTabRootY + 20 + (int)(Math.floor(Enums.Skills.EXPEDITION.id / 5f) * 112);
		int expeditionIconX = AdventMainGui.scaledTabRootX + 170 + (100 * (1 + Enums.Skills.EXPEDITION.id % 5));

		if (adjustedMouseX >= expeditionIconX && adjustedMouseX <= expeditionIconX + 62 && adjustedMouseY >= expeditionIconY && adjustedMouseY <= expeditionIconY + 62)
			PacketUtil.network.sendToServer(new PacketExpeditionToggle());
	}

	private void setRenderEntity() {
		if (entityToRender == null) {
			if (optExpedition >= 4) {
				switch (AdventOfAscension.rand.nextInt(29)) {
					case 0:
						entityToRender = new EntityArcworm(mc.world);
						break;
					case 1:
						entityToRender = new EntityCharger(mc.world);
						break;
					case 2:
						entityToRender = new EntityDesertCharger(mc.world);
						break;
					case 3:
						entityToRender = new EntityHillCharger(mc.world);
						break;
					case 4:
						entityToRender = new EntityOcculent(mc.world);
						break;
					case 5:
						entityToRender = new EntitySeaCharger(mc.world);
						break;
					case 6:
						entityToRender = new EntitySnowCharger(mc.world);
						break;
					case 7:
						entityToRender = new EntitySwampCharger(mc.world);
						break;
					case 8:
						entityToRender = new EntityVoidCharger(mc.world);
						break;
					case 9:
						entityToRender = new EntityFacelessRunner(mc.world);
						break;
					case 10:
						entityToRender = new EntityGrunt(mc.world);
						break;
					case 11:
						entityToRender = new EntityCyclops(mc.world);
						break;
					case 12:
						entityToRender = new EntityBrute(mc.world);
						break;
					case 13:
						entityToRender = new EntityKranky(mc.world);
						break;
					case 14:
						entityToRender = new EntityGingerbreadMan(mc.world);
						break;
					case 15:
						entityToRender = new EntityShade(mc.world);
						break;
					case 16:
						entityToRender = new EntityBobo(mc.world);
						break;
					case 17:
						entityToRender = new EntityChocko(mc.world);
						break;
					case 18:
						entityToRender = new EntityStitches(mc.world);
						break;
					case 19:
						entityToRender = new EntityBugeye(mc.world);
						break;
					case 20:
						entityToRender = new EntitySeaTroll(mc.world);
						break;
					case 21:
						entityToRender = new EntityShyreTroll(mc.world);
						break;
					case 22:
						entityToRender = new EntityEliteSkelePig(mc.world);
						break;
					case 23:
						entityToRender = new EntitySpearmintSlug(mc.world);
						break;
					case 24:
						entityToRender = new EntityShadow(mc.world);
						break;
					case 25:
						entityToRender = new EntitySeaSkeleton(mc.world);
						break;
					case 26:
						entityToRender = new EntityAlarmo(mc.world);
						break;
					case 27:
						entityToRender = new EntityAncientGolem(mc.world);
						break;
					case 28:
						entityToRender = new EntitySticky(mc.world);
						break;
				}
			}
			else {
				entityToRender = mc.player;
			}
		}
	}

	public static void drawScaledCustomSizeModalRect(double x, double y, float u, float v, float uWidth, float vHeight, double width, double height, float tileWidth, float tileHeight) {
		float f = 1.0F / tileWidth;
		float f1 = 1.0F / tileHeight;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();

		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + height, 0.0D).tex((double)(u * f), (double)((v + vHeight) * f1)).endVertex();
		bufferbuilder.pos(x + width, y + height, 0.0D).tex((double)((u + uWidth) * f), (double)((v + vHeight) * f1)).endVertex();
		bufferbuilder.pos(x + width, y, 0.0D).tex((double)((u + uWidth) * f), (double)(v * f1)).endVertex();
		bufferbuilder.pos(x, y, 0.0D).tex((double)(u * f), (double)(v * f1)).endVertex();
		tessellator.draw();
	}
}
