package net.tslat.aoa3.client.gui.mainwindow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.lib.ScrollablePane;
import net.tslat.aoa3.entity.base.*;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.HunterUtil;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SideOnly(Side.CLIENT)
public class AdventGuiTabBestiary extends GuiScreen implements IProgressMeter {
	private static final ResourceLocation iconsTextures = new ResourceLocation("aoa3", "textures/gui/maingui/icons.png");

	public static String currentLanguage = FMLCommonHandler.instance().getCurrentLanguage();
	private StatisticsManager stats;
	private BestiaryMenu scrollMenu;
	private ArrayList<EntityList.EntityEggInfo> mobList;

	private boolean receivedStats = false;
	private int lastOpenIndex = -1;
	private int openEntryIndex = -1;
	private int openEntryHeight = 0;
	private long lastSelectionTime = 0;
	private float lastDistanceScrolled = 0;
	private EntityLivingBase openEntryInstance = null;
	private List<String> openEntryInfoLines = null;
	private List<String> openEntryStatsLines = null;

	private int adjustedMouseX;
	private int adjustedMouseY;

	@Override
	public void initGui() {
		receivedStats = false;
		openEntryIndex = -1;
		stats = mc.player.getStatFileWriter();
		currentLanguage = FMLCommonHandler.instance().getCurrentLanguage();

		if (scrollMenu == null)
			scrollMenu = new BestiaryMenu(mc, AdventMainGui.scaledTabRootY, AdventMainGui.scaledTabRootX, 340, 764, AdventMainGui.scale);

		mc.getConnection().sendPacket(new CPacketClientStatus(CPacketClientStatus.State.REQUEST_STATS));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		this.adjustedMouseX = (int)(mouseX * AdventMainGui.scaleInverse);
		this.adjustedMouseY = (int)(mouseY * AdventMainGui.scaleInverse);

		scrollMenu.drawScreen(adjustedMouseX, adjustedMouseY, partialTicks);
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		scrollMenu.handleMouseInput(-1, -1);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (scrollMenu != null)
			scrollMenu.handleMouseInput(adjustedMouseX, adjustedMouseY);
	}

	@Override
	public void onResize(Minecraft mcIn, int w, int h) {
		super.onResize(mcIn, w, h);

		if (scrollMenu != null)
			scrollMenu.onResize(mc, AdventMainGui.scaledTabRootX, AdventMainGui.scaledTabRootY, 764, 340);
	}

	@Override
	public void onStatsUpdated() {
		mobList = new ArrayList<EntityList.EntityEggInfo>();

		for (EntityList.EntityEggInfo entityEggInfo : EntityList.ENTITY_EGGS.values()) {
			if (stats.readStat(entityEggInfo.killEntityStat) > 0)
				mobList.add(entityEggInfo);
		}

		mobList.sort(Comparator.comparing(eggInfo -> eggInfo.spawnedID.getPath()));

		receivedStats = true;
	}

	@Nullable
	private EntityLivingBase getEntityFromEgg(EntityList.EntityEggInfo eggInfo) {
		EntityLivingBase entity = null;

		try {
			entity = (EntityLivingBase)ForgeRegistries.ENTITIES.getValue(eggInfo.spawnedID).getEntityClass().getConstructor(World.class).newInstance(mc.player.world);
		}
		catch (Exception e) {
			AdventOfAscension.logOptionalMessage("Unable to retrieve entity from egg info: " + eggInfo.spawnedID.toString());
		}

		return entity;
	}

	private void gatherEntityStats(EntityList.EntityEggInfo eggInfo) {
		openEntryInstance = getEntityFromEgg(eggInfo);
		openEntryStatsLines = new ArrayList<String>();
		openEntryInfoLines = new ArrayList<String>(0);

		if (openEntryInstance != null) {
			String type;
			String attribute;

			if (openEntryInstance instanceof BossEntity) {
				type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.boss");
			}
			else if (openEntryInstance instanceof AoAMeleeMob) {
				if (openEntryInstance instanceof AoARangedAttacker) {
					type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.hybrid");
				}
				else {
					type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.melee");
				}
			}
			else if (openEntryInstance instanceof AoARangedMob) {
				type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.ranged");
			}
			else if (openEntryInstance instanceof AoAFlyingMeleeMob) {
				type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.flyingMelee");
			}
			else if (openEntryInstance instanceof AoAFlyingRangedMob) {
				type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.flyingRanged");
			}
			else if (openEntryInstance instanceof EntityAnimal) {
				type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.animal");
			}
			else if (openEntryInstance instanceof AoAAmbientNPC) {
				type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.ambient");
			}
			else if (openEntryInstance instanceof AoATrader) {
				type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.trader");
			}
			else {
				if (eggInfo.spawnedID.getNamespace().equals("aoa3")) {
					type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.other");
				}
				else if (openEntryInstance instanceof IRangedAttackMob) {
					type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.ranged");
				}
				else {
					type = StringUtil.getLocaleString("gui.aoamain.bestiary.type.melee");
				}
			}

			switch (openEntryInstance.getCreatureAttribute()) {
				case ARTHROPOD:
					attribute = StringUtil.getLocaleString("gui.aoamain.bestiary.attribute.arthropod");
					break;
				case ILLAGER:
					attribute = StringUtil.getLocaleString("gui.aoamain.bestiary.attribute.illager");
					break;
				case UNDEAD:
					attribute = StringUtil.getLocaleString("gui.aoamain.bestiary.attribute.undead");
					break;
				case UNDEFINED:
				default:
					attribute = StringUtil.getLocaleString("gui.aoamain.bestiary.attribute.none");
					break;
			}

			openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.type") + TextFormatting.RESET + " " + type);

			if (openEntryInstance instanceof SpecialPropertyEntity) {
				int hunterLvl = HunterUtil.getHunterLevel(openEntryInstance);

				if (hunterLvl > 0)
					openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.hunterReq") + TextFormatting.RESET + " " + hunterLvl);

				openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.immunity.immunities"));

				for (Enums.MobProperties property : ((SpecialPropertyEntity)openEntryInstance).getMobProperties()) {
					openEntryStatsLines.add("    " + StringUtil.getLocaleString("gui.aoamain.bestiary.immunity." + property.toString().toLowerCase()));
				}
			}

			openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.attribute") + TextFormatting.RESET + " " + attribute);
			openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.size") + TextFormatting.RESET + " " + ((int)(openEntryInstance.width * 1000)) / 1000f + "x" + ((int)(openEntryInstance.height * 1000)) / 1000f);
			openEntryStatsLines.add("");
			openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.health") + TextFormatting.RESET + " " + openEntryInstance.getMaxHealth());

			if (openEntryInstance.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue() > 0)
				openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.armour") + TextFormatting.RESET + " " + openEntryInstance.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue());

			if (openEntryInstance.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue() > 0)
				openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.knockback") + TextFormatting.RESET + " " + (int)(openEntryInstance.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue() * 100) + "%");

			IAttributeInstance attackAttribute = openEntryInstance.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

			if (attackAttribute != null && attackAttribute.getAttributeValue() > 0)
				openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.strength") + TextFormatting.RESET + " " + openEntryInstance.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());

			if (openEntryInstance instanceof AoARangedAttacker) {
				if (openEntryInstance instanceof AoARangedMob) {
					openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.projectileStrength") + TextFormatting.RESET + " " + ((AoARangedMob)openEntryInstance).getBaseProjectileDamage());
				}
				else if (openEntryInstance instanceof AoAFlyingRangedMob) {
					openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.projectileStrength") + TextFormatting.RESET + " " + ((AoAFlyingRangedMob)openEntryInstance).getBaseProjectileDamage());
				}
			}

			openEntryStatsLines.add(TextFormatting.BOLD + StringUtil.getLocaleString("gui.aoamain.bestiary.speed") + TextFormatting.RESET + " " + (int)(openEntryInstance.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 1000) / 1000f);

			if (eggInfo.spawnedID.getNamespace().equals("aoa3")) {
				String info = ModUtil.getTextFromResourceFile(new ResourceLocation("aoa3", "lang/other/" + currentLanguage + "/bestiary/" + eggInfo.spawnedID.getPath().toLowerCase()), "txt", new ResourceLocation("aoa3", "lang/other/en_us/bestiary/" + eggInfo.spawnedID.getPath().toLowerCase()));

				if (info != null)
					openEntryInfoLines = mc.fontRenderer.listFormattedStringToWidth(info, (int)(734 / 1.5f));
			}
		}
		else {
			openEntryIndex = -1;
			openEntryHeight = 0;
		}
	}

	private class BestiaryMenu extends ScrollablePane {
		public BestiaryMenu(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float... renderingScale) {
			super(mc, top, left, viewHeight, viewWidth, renderingScale);
		}

		@Override
		public int getFullPaneHeight() {
			return receivedStats ? openEntryIndex < 0 ? (mobList.size() + mobList.size() % 2) * 100 + 20 : openEntryHeight : 0;
		}

		@Override
		public void drawPaneContents(int top, int left, int right, int bottom, float scrollDistance) {
			GlStateManager.pushMatrix();

			if (!receivedStats) {
				RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.bestiary.downloading"), left + (int)(viewWidth / 2f), top + (int)(viewHeight / 2f) - 20, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
				RenderUtil.drawCenteredScaledString(mc.fontRenderer, LOADING_STRINGS[(int)(Minecraft.getSystemTime() / 150L % (long)LOADING_STRINGS.length)], left + (int)(viewWidth / 2f), top + (int)(viewHeight / 2f), 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			}
			else if (mobList.isEmpty()) {
				RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.bestiary.empty"), left + (int)(viewWidth / 2f), top + (int)(viewHeight / 2f) - 20, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			}
			else {
				if (openEntryIndex < 0) {
					for (int i = Math.max(0, (int)(scrollDistance / 200f) * 2); i * 100 <= bottom - top && i < mobList.size(); i += 2) {
						EntityList.EntityEggInfo entityEggInfo = mobList.get(i);
						int rowTop = top + 20 + i * 100;
						int rowBottom = rowTop + 180;
						EntityLivingBase entity = getEntityFromEgg(entityEggInfo);
						String entityName = entity != null ? entity.getName() : StringUtil.getLocaleString("entity." + entityEggInfo.spawnedID.getNamespace() + "." + entityEggInfo.spawnedID.getPath() + ".name").replace(".minecraft", "");

						drawRect(left + 40, rowTop + 30, left + 360, rowBottom, 0xFF202020);

						if (entity != null)
							drawEntity(entity, left + 200, rowTop + 170, 50f);

						drawRect(left + 40, rowTop, left + 360, rowTop + 30, 0xFF010101);
						RenderUtil.drawCenteredScaledString(mc.fontRenderer, entityName, left + 200, rowTop + 8, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);
						mc.getTextureManager().bindTexture(iconsTextures);
						RenderUtil.drawScaledCustomSizeModalRect(left + 300, rowTop + 160, 0, 0, 16, 16, 16, 16, 16, 32);
						RenderUtil.drawScaledCustomSizeModalRect(left + 43, rowTop + 160, 0, 16, 16, 16, 16, 16, 16, 32);
						RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.floorAndAppendSuffix(stats.readStat(entityEggInfo.killEntityStat), true), left + 60, rowTop + 163, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);
						RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.floorAndAppendSuffix(stats.readStat(entityEggInfo.entityKilledByStat), true), left + 320, rowTop + 163, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);

						if (i + 1 < mobList.size()) {
							entityEggInfo = mobList.get(i + 1);
							entity = getEntityFromEgg(entityEggInfo);
							entityName = entity != null ? entity.getName() : StringUtil.getLocaleString("entity." + entityEggInfo.spawnedID.getNamespace() + "." + entityEggInfo.spawnedID.getPath() + ".name").replace(".minecraft", "");

							drawRect(right - 360, rowTop + 30, right - 40, rowBottom, 0xFF202020);

							if (entity != null)
								drawEntity(entity, right - 200, rowTop + 170, 50f);

							drawRect(right - 360, rowTop, right - 40, rowTop + 30, 0xFF010101);
							RenderUtil.drawCenteredScaledString(mc.fontRenderer, entityName, right - 200, rowTop + 8, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);
							mc.getTextureManager().bindTexture(iconsTextures);
							RenderUtil.drawScaledCustomSizeModalRect(right - 100, rowTop + 160, 0, 0, 16, 16, 16, 16, 16, 32);
							RenderUtil.drawScaledCustomSizeModalRect(right - 357, rowTop + 160, 0, 16, 16, 16, 16, 16, 16, 32);
							RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.floorAndAppendSuffix(stats.readStat(entityEggInfo.killEntityStat), true), right - 340, rowTop + 163, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);
							RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.floorAndAppendSuffix(stats.readStat(entityEggInfo.entityKilledByStat), true), right - 80, rowTop + 163, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);
						}
					}
				}
				else {
					EntityList.EntityEggInfo entityEggInfo = mobList.get(openEntryIndex);
					openEntryHeight = Math.max(viewHeight - 30, 320 + (int)(openEntryInfoLines.size() * mc.fontRenderer.FONT_HEIGHT * 1.5f));

					drawRect(left, top + 30, right, bottom, 0xFF202020);
					drawRect(left, top, right, top + 30, 0xFF010101);
					String entityName = openEntryInstance != null ? openEntryInstance.getName() : StringUtil.getLocaleString("entity." + entityEggInfo.spawnedID.getNamespace() + "." + entityEggInfo.spawnedID.getPath() + ".name").replace(".minecraft", "");

					RenderUtil.drawCenteredScaledString(mc.fontRenderer, entityName, left + (int)(viewWidth / 2f), top + 8, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);

					if (openEntryInstance != null)
						drawEntity(openEntryInstance, left + 200, top + 240, 75f);

					mc.getTextureManager().bindTexture(iconsTextures);
					RenderUtil.drawScaledCustomSizeModalRect(left + 425, top + 45, 0, 16, 16, 16, 16, 16, 16, 32);
					RenderUtil.drawScaledCustomSizeModalRect(left + 425, top + 65, 0, 0, 16, 16, 16, 16, 16, 32);
					RenderUtil.drawScaledString(mc.fontRenderer, "X", right - 20, top + 5, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);
					RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.floorAndAppendSuffix(stats.readStat(entityEggInfo.killEntityStat), true), left + 445, top + 48, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);
					RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.floorAndAppendSuffix(stats.readStat(entityEggInfo.entityKilledByStat), true), left + 445, top + 68, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.NORMAL);

					GlStateManager.scale(1.5f, 1.5f, 1.5f);

					for (int i = 0; i < openEntryStatsLines.size(); i++) {
						mc.fontRenderer.drawString(openEntryStatsLines.get(i), (int)((left + 425) / 1.5f), (int)((top + 100 + 14 * i) / 1.5f), Enums.RGBIntegers.WHITE, true);
					}

					for (int i = 0; i < openEntryInfoLines.size(); i++) {
						mc.fontRenderer.drawString(openEntryInfoLines.get(i), (int)((left + 20) / 1.5f), (int)((top + 300 + i * 14) / 1.5f), Enums.RGBIntegers.WHITE);
					}
				}
			}

			GlStateManager.popMatrix();
		}

		@Override
		public void drawBackground() {}

		private void drawEntity(EntityLivingBase entity, int posX, int posY, float scale) {
			GlStateManager.pushMatrix();
			GlStateManager.enableDepth();
			GlStateManager.enableTexture2D();
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			GlStateManager.enableColorMaterial();
			GlStateManager.translate((float)posX, (float)posY, 200f);

			if (entity.height > 2.5)
				scale *= 2.3 / entity.height;

			if (entity.width > 3)
				scale *= 3 / entity.width;

			GlStateManager.scale(-scale, scale, scale);
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
			RenderHelper.enableStandardItemLighting();
			GlStateManager.rotate(-125.0F, 0.0F, 1.0F, 0.0F);

			RenderManager rendermanager = mc.getRenderManager();
			entity.ticksExisted = mc.player.ticksExisted;
			entity.rotationYaw = 0;
			entity.rotationPitch = 0;
			entity.rotationYawHead = 0;

			rendermanager.setPlayerViewY(180.0F);
			rendermanager.setRenderShadow(false);
			rendermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
			rendermanager.setRenderShadow(true);

			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.disableTexture2D();
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			GlStateManager.popMatrix();
		}

		@Override
		public void handleMouseInput(int mouseX, int mouseY) {
			super.handleMouseInput(mouseX, mouseY);

			if (mouseX != -1) {

				int relativeMouseX = mouseX - left + 2;

				if (openEntryIndex < 0 && (relativeMouseX < 40 || relativeMouseX > right - left - 40))
					return;

				int newTop = top - Math.max(0, (int)distanceScrolled);
				int relativeMouseY = mouseY - newTop + 2;

				if (mouseY - top < 0 || mouseY > top + viewHeight)
					return;

				if (openEntryIndex < 0) {
					int selectedIndex = -1;

					if (relativeMouseX < 360) {
						float preIndexY = relativeMouseY / 200f;

						if (preIndexY - ((int)preIndexY) > 0.11)
							selectedIndex = 2 * (int)preIndexY;
					}
					else if (relativeMouseX > right - left - 360) {
						float preIndexY = relativeMouseY / 200f;

						if (preIndexY - ((int)preIndexY) > 0.11)
							selectedIndex = 1 + (int)preIndexY * 2;
					}

					if (selectedIndex >= 0 && mobList.size() > selectedIndex) {
						openEntryIndex = selectedIndex;
						lastSelectionTime = System.currentTimeMillis();
						openEntryHeight = 600;
						lastDistanceScrolled = distanceScrolled;
						distanceScrolled = 0;

						if (lastOpenIndex != selectedIndex) {
							if (openEntryInstance != null)
								openEntryInstance.setDead();

							gatherEntityStats(mobList.get(selectedIndex));
						}

						lastOpenIndex = selectedIndex;
					}
				}
				else  {
					if (relativeMouseY <= 30) {
						lastOpenIndex = openEntryIndex;
						openEntryIndex = -1;
						openEntryHeight = 0;
						distanceScrolled = lastDistanceScrolled;
					}
				}
			}
		}
	}
}
