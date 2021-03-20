package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ExpeditionTogglePacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;

@OnlyIn(Dist.CLIENT)
public class AdventGuiTabPlayer extends Screen {
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
	private static int levelExtraction = 1;
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

	private LivingEntity entityToRender = null;

	protected AdventGuiTabPlayer() {
		super(new TranslationTextComponent("gui.aoa3.adventGui.stats"));
	}

	@Override
	protected void init() {
		setRenderEntity();
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		this.adjustedMouseX = (int)(mouseX * AdventMainGui.scaleInverse);
		this.adjustedMouseY = (int)(mouseY * AdventMainGui.scaleInverse);

		drawPlayerBox(matrix, AdventMainGui.scaledTabRootX + 63, AdventMainGui.scaledTabRootY + 330, mouseX, mouseY, (int)(30 * AdventMainGui.scaleInverse));

		if (AoAConfig.COMMON.resourcesEnabled.get())
			renderResources(matrix);

		if (AoAConfig.COMMON.skillsEnabled.get())
			renderSkills(matrix);
	}

	private void renderResources(MatrixStack matrix) {
		int x = AdventMainGui.scaledTabRootX + 15;
		int y = AdventMainGui.scaledTabRootY + 20;
		float percentComplete = tributeSelyan / 200f;
		Minecraft mc = Minecraft.getInstance();
		
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.stats.tribute"), x + 50, y - 15, 1.5625f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		mc.getTextureManager().bind(resourcesTextures);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y, 0, 0, 100, 30, 100, 30, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y, 0, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 590);

		percentComplete = tributeLuxon / 200f;

		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 30, 100, 0, 100, 30, 100, 30, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 30, 100, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 590);

		percentComplete = tributeErebon / 200f;

		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 60, 200, 0, 100, 30, 100, 30, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 60, 200, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 590);

		percentComplete = tributePluton / 200f;

		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 90, 300, 0, 100, 30, 100, 30, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 90, 300, percentComplete == 1 ? 60 : 30, percentComplete * 100, 30, percentComplete * 100, 30, 400, 590);

		percentComplete = resourceRage / 200f;

		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y, 0, 190, 50, 50, 62, 62, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y, resourceRage >= 150 ? 50 : 0, 240, percentComplete * 50, 50, percentComplete * 62, 62, 400, 590);

		percentComplete = resourceEnergy / 200f;

		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y + 82, 0, 90, 50, 50, 62, 62, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y + 82, 0, 140, percentComplete * 50, 50, percentComplete * 62, 62, 400, 590);

		percentComplete = resourceCreation / 200f;

		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y + 164, 0, 290, 50, 50, 62, 62, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y + 164, 0, 340, percentComplete * 50, 50, percentComplete * 62, 62, 400, 590);

		percentComplete = resourceSoul / 200f;

		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y + 246, 0, 390, 50, 50, 62, 62, 400, 590);
		RenderUtil.renderScaledCustomSizedTexture(matrix, x + 135, y + 246, 0, 440, percentComplete * 50, 50, percentComplete * 62, 62, 400, 590);

		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.SELYAN), x + 50, y + 11, 1.25f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.NORMAL);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.LUXON), x + 50, y + 41, 1.25f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.NORMAL);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.EREBON), x + 50, y + 71, 1.25f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.NORMAL);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.PLUTON), x + 50, y + 101, 1.25f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.NORMAL);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.RAGE_RESOURCE), x + 166, y - 15, 1.5625f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.ENERGY_RESOURCE), x + 166, y + 67, 1.5625f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.CREATION_RESOURCE), x + 166, y + 149, 1.5625f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, LocaleUtil.getLocaleString(LocaleUtil.Constants.SOUL_RESOURCE), x + 166, y + 231, 1.5625f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, (int)resourceRage + "/" + 200, x + 166, y + 40, 1.125f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, (int)resourceEnergy + "/" + 200, x + 166, y + 122, 1.125f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.floorAndAppendSuffix(resourceCreation, true) + "/" + NumberUtil.floorAndAppendSuffix(200, true), x + 166, y + 204, 1.125f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, NumberUtil.floorAndAppendSuffix(resourceSoul, true) + "/" + NumberUtil.floorAndAppendSuffix(200, true), x + 166, y + 286, 1.125f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
	}

	private void renderSkills(MatrixStack matrix) {
		int x;
		int y;
		Minecraft mc = Minecraft.getInstance();
		mc.getTextureManager().bind(skillsTextures);
		RenderSystem.enableAlphaTest();

		for (Skills skill : Skills.values()) {
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

			RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
			RenderUtil.renderScaledCustomSizedTexture(matrix, x, y, uvX, uvY, 50, 50, 62, 62, 450, 240);
			RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 50, 0, 200, 100, 20, 62, 13, 450, 240);
			RenderUtil.renderScaledCustomSizedTexture(matrix, x, y + 50, 0, 220, progressBarPercent, 20, progressBarPercent / 100f * 62, 13, 450, 240);

			if (optionalUvX >= 0)
				RenderUtil.renderScaledCustomSizedTexture(matrix, x + 38, y, optionalUvX, optionalUvY, 24, 24, 24, 24, 450, 240);
		}

		RenderSystem.color4f(1f, 1f, 1f, 1f);

		for (Skills skill : Skills.values()) {
			y = AdventMainGui.scaledTabRootY + 20 + (int)(Math.floor(skill.id / 5f) * 112);
			x = AdventMainGui.scaledTabRootX + 170 + (100 * (1 + skill.id % 5));
			int lvl = 1;
			float xp = 0;
			boolean hitXpCap = false;
			String skillName = "";

			switch (skill) {
				case ALCHEMY:
					skillName = LocaleUtil.getLocaleString("skills.alchemy.name");
					lvl = levelAlchemy;
					xp = xpAlchemy;
					break;
				case ANIMA:
					skillName = LocaleUtil.getLocaleString("skills.anima.name");
					lvl = levelAnima;
					xp = xpAnima;
					break;
				case AUGURY:
					skillName = LocaleUtil.getLocaleString("skills.augury.name");
					lvl = levelAugury;
					xp = xpAugury;
					break;
				case BUTCHERY:
					skillName = LocaleUtil.getLocaleString("skills.butchery.name");
					lvl = levelButchery;
					xp = xpButchery;
					break;
				case CREATION:
					skillName = LocaleUtil.getLocaleString("skills.creation.name");
					lvl = levelCreation;
					xp = xpCreation;
					break;
				case ENGINEERING:
					skillName = LocaleUtil.getLocaleString("skills.engineering.name");
					lvl = levelEngineering;
					xp = xpEngineering;
					break;
				case EXPEDITION:
					skillName = LocaleUtil.getLocaleString("skills.expedition.name");
					lvl = levelExpedition;
					xp = xpExpedition;
					break;
				case EXTRACTION:
					skillName = LocaleUtil.getLocaleString("skills.extraction.name");
					lvl = levelExtraction;
					xp = xpExtraction;
					break;
				case FORAGING:
					skillName = LocaleUtil.getLocaleString("skills.foraging.name");
					lvl = levelForaging;
					xp = xpForaging;
					break;
				case HAULING:
					skillName = LocaleUtil.getLocaleString("skills.hauling.name");
					lvl = levelHauling;
					xp = xpHauling;
					break;
				case HUNTER:
					skillName = LocaleUtil.getLocaleString("skills.hunter.name");
					lvl = levelHunter;
					xp = xpHunter;
					break;
				case INFUSION:
					skillName = LocaleUtil.getLocaleString("skills.infusion.name");
					lvl = levelInfusion;
					xp = xpInfusion;
					break;
				case INNERVATION:
					skillName = LocaleUtil.getLocaleString("skills.innervation.name");
					lvl = levelInnervation;
					xp = xpInnervation;
					break;
				case LOGGING:
					skillName = LocaleUtil.getLocaleString("skills.logging.name");
					lvl = levelLogging;
					xp = xpLogging;
					break;
				case RUNATION:
					skillName = LocaleUtil.getLocaleString("skills.runation.name");
					lvl = levelRunation;
					xp = xpRunation;
					break;
			}

			if (!AoAConfig.CLIENT.showVanityLevels.get() && lvl >= 100) {
				lvl = 100;
				hitXpCap = true;
			}

			if (lvl >= 1000)
				hitXpCap = true;

			RenderUtil.drawCenteredScaledString(matrix, mc.font, skillName, x + 31, y - 15, 1.5625f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
			matrix.scale(1.25f, 1.25f, 1.25f);

			mc.font.drawShadow(matrix, LocaleUtil.getLocaleString("gui.aoa3.adventGui.stats.level", String.valueOf(lvl)), x * 0.8f, (y + 65) * 0.8f, NumberUtil.RGB(255, 255, 255));

			if (!hitXpCap)
				mc.font.drawShadow(matrix, LocaleUtil.getLocaleString("gui.aoa3.adventGui.stats.xp", NumberUtil.floorAndAppendSuffix(xp, true), NumberUtil.floorAndAppendSuffix(PlayerUtil.getXpRequiredForNextLevel(lvl), true)), x * 0.8f, (y + 77) * 0.8f, NumberUtil.RGB(255, 255, 255));

			matrix.scale(0.8f, 0.8f, 0.8f);
		}

		RenderSystem.disableAlphaTest();
	}

	private void drawPlayerBox(MatrixStack matrix, int posX, int posY, int mouseX, int mouseY, int scale) {
		matrix.pushPose();

		if (entityToRender == null)
			setRenderEntity();

		matrix.scale(1.6f, 1.6f, 1.6f);
		Minecraft mc = Minecraft.getInstance();
		
		ITextComponent name = mc.player.getDisplayName();
		RenderUtil.drawCenteredScaledMessage(matrix, mc.font, name, (int)(posX * 0.625f), (int)((posY - 170) * 0.625f), 1.0f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
		matrix.scale(0.625f, 0.625f, 0.625f);

		matrix.translate((float)posX, (float)posY, 1050.0F);
		matrix.scale(1.0F, 1.0F, -1.0F);

		matrix.translate(0.0D, 0.0D, 1000.0D);
		matrix.scale((float)scale, (float)scale, (float)scale);

		Quaternion quaternion = Vector3f.XP.rotationDegrees(180f);

		matrix.mulPose(quaternion);

		float yawOffset = entityToRender.yBodyRot;
		float rotYaw = entityToRender.yRot;
		float rotPitch = entityToRender.xRot;
		float prevYawHead = entityToRender.yHeadRotO;
		float rotYawHead = entityToRender.yHeadRot;
		entityToRender.yBodyRot = 0;
		entityToRender.yRot = (float)Math.atan(((((AdventMainGui.scaledRootX + 264) / AdventMainGui.scaleInverse) - mouseX) / 40.0F)) * 40.0F;
		entityToRender.xRot = -((float)Math.atan(((((AdventMainGui.scaledRootY + 465) / AdventMainGui.scaleInverse) - 50 - mouseY) / 40.0F))) * 20.0F;
		entityToRender.yHeadRot = entityToRender.yRot;
		entityToRender.yHeadRotO = entityToRender.yRot;
		EntityRendererManager renderManager = Minecraft.getInstance().getEntityRenderDispatcher();

		renderManager.setRenderShadow(false);

		IRenderTypeBuffer.Impl renderBuffer = Minecraft.getInstance().renderBuffers().bufferSource();

		renderManager.render(entityToRender, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrix, renderBuffer, 15728880);
		renderBuffer.endBatch();
		renderManager.setRenderShadow(true);

		entityToRender.yBodyRot = yawOffset;
		entityToRender.yRot = rotYaw;
		entityToRender.xRot = rotPitch;
		entityToRender.yHeadRotO = prevYawHead;
		entityToRender.yHeadRot = rotYawHead;

		matrix.popPose();
	}

	public static void setSkillData(Skills skill, float xp, int lvl, int opt) {
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

	public static void addXp(Skills skill, float additionalXp) {
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

	public static int getSkillLevel(Skills skill) {
		if (!AoAConfig.COMMON.skillsEnabled.get())
			return 100;

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

	public static float getSkillXp(Skills skill) {
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

	public static int getPercentCompleteLevel(Skills skill) {
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

	public static int getOptionalSkillData(Skills skill) {
		switch (skill) {
			case EXPEDITION:
				return optExpedition;
			default:
				return 0;
		}
	}

	@Override
	public void removed() {
		super.removed();

		if (entityToRender != null && !(entityToRender instanceof PlayerEntity))
			entityToRender.remove();

		entityToRender = null;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		int expeditionIconY = AdventMainGui.scaledTabRootY + 20 + (int)(Math.floor(Skills.EXPEDITION.id / 5f) * 112);
		int expeditionIconX = AdventMainGui.scaledTabRootX + 170 + (100 * (1 + Skills.EXPEDITION.id % 5));

		if (adjustedMouseX >= expeditionIconX && adjustedMouseX <= expeditionIconX + 62 && adjustedMouseY >= expeditionIconY && adjustedMouseY <= expeditionIconY + 62) {
			AoAPackets.messageServer(new ExpeditionTogglePacket());

			return true;
		}

		return false;
	}

	private void setRenderEntity() {
		if (entityToRender == null) {
			Minecraft mc = Minecraft.getInstance();
			
			if (optExpedition >= 4) {
				entityToRender = RandomUtil.getRandomSelection(
						AoAEntities.Mobs.ARCWORM,
						AoAEntities.Mobs.CHARGER,
						AoAEntities.Mobs.DESERT_CHARGER,
						AoAEntities.Mobs.HILL_CHARGER,
						AoAEntities.Mobs.OCCULENT,
						AoAEntities.Mobs.SEA_CHARGER,
						AoAEntities.Mobs.SNOW_CHARGER,
						AoAEntities.Mobs.SWAMP_CHARGER,
						AoAEntities.Mobs.VOID_CHARGER,
						AoAEntities.Mobs.CYCLOPS,
						AoAEntities.Mobs.STICKY,
						AoAEntities.Mobs.KRANKY,
						AoAEntities.Mobs.GINGERBREAD_MAN,
						AoAEntities.Mobs.SHADE,
						AoAEntities.Mobs.BOBO,
						AoAEntities.Mobs.CHOCKO,
						AoAEntities.Mobs.STITCHES,
						AoAEntities.Mobs.BUGEYE,
						AoAEntities.Mobs.SEA_TROLL,
						AoAEntities.Mobs.ELITE_SKELE_PIG,
						AoAEntities.Animals.SPEARMINT_SNAIL,
						AoAEntities.Mobs.SHADOW,
						AoAEntities.Mobs.ALARMO,
						AoAEntities.Mobs.ANCIENT_GOLEM
				).get().create(mc.level);
			}
			else {
				entityToRender = mc.player;
			}
		}
	}
}
