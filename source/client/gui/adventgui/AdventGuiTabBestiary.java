package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.achievement.StatsUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.stats.StatsCounter;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.trading.Merchant;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.client.gui.lib.ScrollablePane;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.content.entity.base.*;
import net.tslat.aoa3.data.client.BestiaryReloadListener;
import net.tslat.aoa3.util.*;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import static net.minecraft.world.entity.MobType.*;

public class AdventGuiTabBestiary extends Screen implements StatsUpdateListener {
	private static final ResourceLocation iconsTextures = new ResourceLocation("aoa3", "textures/gui/adventgui/icons.png");

	private static final HashMap<String, Function<Entity, Tuple>> registeredEntryHandlers = new HashMap<>(1);

	private final HashMap<String, Entity> instancesMap = new HashMap<>();

	private StatsCounter stats;
	private Object2IntMap<Stat<?>> statsMap;
	private BestiaryMenu scrollMenu;
	private ArrayList<EntityStats> statList;
	private ArrayList<EntityStats> filteredMobList;

	private boolean receivedStats = false;
	private int lastOpenIndex = -1;
	private int openEntryIndex = -1;
	private int openEntryHeight = 0;
	private float lastDistanceScrolled = 0;
	private Entity openEntryInstance = null;
	private List<FormattedCharSequence> openEntryInfoLines = null;
	private List<String> openEntryStatsLines = null;

	private int adjustedMouseX;
	private int adjustedMouseY;

	protected EditBox searchField;

	protected AdventGuiTabBestiary() {
		super(Component.translatable("gui.aoa3.adventGui.bestiary"));
	}

	@Override
	protected void init() {
		receivedStats = false;
		openEntryIndex = -1;
		stats = getMinecraft().player.getStats();

		if (statsMap == null)
			statsMap = ObfuscationReflectionHelper.getPrivateValue(StatsCounter.class, stats, "f_13013_");

		if (scrollMenu == null)
			scrollMenu = new BestiaryMenu(minecraft, AdventMainGui.scaledTabRootY, AdventMainGui.scaledTabRootX, 340, 764, AdventMainGui.SCALE);

		searchField = new EditBox(font, AdventMainGui.scaledTabRootX + 20, AdventMainGui.scaledTabRootY, (int)((width - 40) / 2d), 15, Component.literal(""));

		searchField.setVisible(false);
		getMinecraft().getConnection().send(new ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action.REQUEST_STATS));
	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);

		this.adjustedMouseX = (int)(mouseX * (1 / AdventMainGui.SCALE));
		this.adjustedMouseY = (int)(mouseY * (1 / AdventMainGui.SCALE));

		scrollMenu.render(matrixStack, adjustedMouseX, adjustedMouseY, partialTicks);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
		if (scrollMenu != null)
			return scrollMenu.handleMouseScroll(-1, -1, scrollAmount);

		return false;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		return scrollMenu != null && scrollMenu.handleMouseClick(adjustedMouseX, adjustedMouseY, mouseButton);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (scrollMenu != null)
			return scrollMenu.handleMouseReleased(mouseX, mouseY, button);

		return false;
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		super.resize(minecraft, width, height);

		if (scrollMenu != null)
			scrollMenu.onResize(this.minecraft, AdventMainGui.scaledTabRootX, AdventMainGui.scaledTabRootY, 764, 340);
	}

	@Override
	public void onStatsUpdated() {
		HashMap<String, EntityStats> statsMap = new HashMap<>();
		statList = new ArrayList<>();

		for (Stat<?> stat : this.statsMap.keySet()) {
			ResourceLocation registryName;

			if (stat.getValue() instanceof EntityType && (registryName = ForgeRegistries.ENTITY_TYPES.getKey(((EntityType<?>)stat.getValue()))) != null) {
				if (AoAConfigs.CLIENT.thirdPartyBestiary.get() || registryName.getNamespace().equals(AdventOfAscension.MOD_ID)) {
					String registryNameString = registryName.toString();

					if (!statsMap.containsKey(registryNameString)) {
						statsMap.put(registryNameString, new EntityStats((Stat<EntityType<?>>)stat, stat.getType() == Stats.ENTITY_KILLED));
					}
					else {
						statsMap.get(registryNameString).matchAndComplete((Stat<EntityType<?>>)stat);
					}
				}
			}
		}

		for (EntityStats stat : statsMap.values()) {
			if (stat.hasKills())
				statList.add(stat);
		}

		statList.sort(Comparator.comparing(stat -> stat.registryName.getPath()));
		filteredMobList = (ArrayList<EntityStats>)statList.clone();
		receivedStats = true;
	}

	@Override
	public void removed() {
		if (searchField != null && searchField.isFocused())
			searchField.setFocused(false);
	}

	@Nullable
	private Entity getEntityFromStat(EntityStats stat) {
		String registryName = stat.registryName.toString();

		if (instancesMap.containsKey(registryName))
			return instancesMap.get(registryName);

		try {
			Entity entity = ForgeRegistries.ENTITY_TYPES.getValue(stat.registryName).create(minecraft.player.clientLevel);

			instancesMap.put(registryName, entity);

			return entity;
		}
		catch (Exception e) {
			Logging.logMessage(Level.DEBUG, "Unable to retrieve entity from entity type: " + registryName);
		}

		return null;
	}

	@Override
	public boolean charTyped(char character, int arg) {
		if (searchField.isFocused()) {
			int searchTextLength = searchField.getValue().length();

			if (searchField.charTyped(character, arg)) {
				if (searchField.getValue().isEmpty()) {
					filteredMobList = (ArrayList<EntityStats>)statList.clone();

					searchField.setTextColor(ColourUtil.WHITE);
				}
				else if (searchField.getValue().length() != searchTextLength) {
					searchFilterBestiaryEntries(searchTextLength < searchField.getValue().length());
				}
			}

			return true;
		}

		return super.charTyped(character, arg);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		boolean success = super.keyPressed(keyCode, scanCode, modifiers);

		if (openEntryIndex < 0 && keyCode == 70 && hasControlDown() && !hasShiftDown() && !hasAltDown())  {
			if (!searchField.isVisible())
				searchField.setVisible(true);

			searchField.setFocused(true);

			return true;
		}

		if (searchField.isFocused()) {
			int searchTextLength = searchField.getValue().length();

			if (searchField.keyPressed(keyCode, scanCode, modifiers)) {
				if (searchField.getValue().isEmpty()) {
					filteredMobList = (ArrayList<EntityStats>)statList.clone();

					searchField.setTextColor(ColourUtil.WHITE);
				}
				else if (searchField.getValue().length() != searchTextLength) {
					searchFilterBestiaryEntries(searchTextLength < searchField.getValue().length());
				}
			}

			return true;
		}

		return success;
	}

	private void searchFilterBestiaryEntries(boolean additiveEdit) {
		searchField.setTextColor(ColourUtil.WHITE);

		if (searchField.getValue().isEmpty()) {
			filteredMobList = (ArrayList<EntityStats>)statList.clone();

			return;
		}

		if (!additiveEdit || filteredMobList == null)
			filteredMobList = (ArrayList<EntityStats>)statList.clone();

		String searchFilter = searchField.getValue().toLowerCase();

		if (searchFilter.startsWith("@")) {
			String modIdFilter = searchFilter.split("@", 2)[1].split(" ")[0];

			filteredMobList.removeIf(stat -> !stat.registryName.getNamespace().startsWith(modIdFilter));

			searchFilter = searchFilter.replace("@" + modIdFilter, "");

			if (searchFilter.startsWith(" "))
				searchFilter = searchFilter.substring(1);

			if (searchFilter.isEmpty()) {
				if (filteredMobList.isEmpty())
					searchField.setTextColor(ColourUtil.RED);

				return;
			}
		}

		final String searchedEntityName = searchFilter;

		filteredMobList.removeIf(stat -> {
			ResourceLocation registryName = stat.registryName;

			String entityName = LocaleUtil.getLocaleString("entity." + registryName.getNamespace() + "." + registryName.getPath()).replace("." + registryName.getNamespace(), "");

			return !entityName.toLowerCase().contains(searchedEntityName);
		});

		if (filteredMobList.isEmpty())
			searchField.setTextColor(ColourUtil.RED);
	}

	private void gatherEntityStats(EntityStats stat) {
		ResourceLocation registryName = stat.registryName;
		String entityModId = registryName.getNamespace();
		openEntryInstance = getEntityFromStat(stat);
		openEntryStatsLines = new ArrayList<>();
		openEntryInfoLines = new ArrayList<FormattedCharSequence>(0);
		LivingEntity livingInstance = openEntryInstance instanceof LivingEntity ? (LivingEntity)openEntryInstance : null;

		if (openEntryInstance != null) {
			if (registeredEntryHandlers.containsKey(entityModId)) {
				try {
					Tuple<List<String>, String> entityData = (Tuple<List<String>, String>)registeredEntryHandlers.get(entityModId).apply(openEntryInstance);
					openEntryStatsLines = entityData.getA();

					if (entityData.getB() != null && entityData.getB().length() > 0)
						openEntryInfoLines = font.split(Component.literal(entityData.getB()), (int)(734 / 1.5f));
				}
				catch (ClassCastException ex) {
					Logging.logMessage(Level.WARN, "Mod '" + entityModId + "' provided invalid bestiary entry handler. Removing support. Report this to the mod author.");

					registeredEntryHandlers.remove(entityModId);

					openEntryIndex = -1;
					openEntryHeight = 0;
				}
			}
			else {
				String type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.other");
				String attribute = "";

				if (openEntryInstance instanceof LivingEntity && openEntryInstance instanceof Enemy) {
					if (openEntryInstance instanceof FlyingMob) {
						if (((LivingEntity)openEntryInstance).getAttribute(Attributes.ATTACK_DAMAGE) != null) {
							type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.flyingMelee");
						}
						else {
							type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.flyingRanged");
						}
					}
					else if (openEntryInstance instanceof AoAWaterRangedMob) {
						type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.swimmingRanged");
					}
					else if (openEntryInstance instanceof AoAWaterMeleeMob || ((LivingEntity)openEntryInstance).getMobType() == WATER) {
						type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.swimmingMelee");
					}
					else {
						if (((LivingEntity)openEntryInstance).getAttribute(Attributes.ATTACK_DAMAGE) != null) {
							if (openEntryInstance instanceof RangedAttackMob) {
								type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.hybrid");
							}
							else {
								type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.melee");
							}
						}
						else {
							type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.ranged");
						}
					}
				}
				else if (openEntryInstance instanceof Merchant) {
					type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.trader");
				}
				else if (openEntryInstance instanceof Animal) {
					type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.animal");
				}
				else if (openEntryInstance instanceof AmbientCreature || openEntryInstance instanceof WaterAnimal) {
					type = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type.ambient");
				}

				if (livingInstance != null) {
					MobType creatureAttribute = (livingInstance).getMobType();

					if (creatureAttribute == ARTHROPOD) {
						attribute = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.attribute.arthropod");
					}
					else if (creatureAttribute == ILLAGER) {
						attribute = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.attribute.illager");
					}
					else if (creatureAttribute == UNDEAD) {
						attribute = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.attribute.undead");
					}
					else if (creatureAttribute == UNDEFINED || creatureAttribute == WATER) {
						attribute = LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.attribute.none");
					}
				}

				openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.type") + ChatFormatting.RESET + " " + type);

				openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.attribute") + ChatFormatting.RESET + " " + attribute);
				openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.size") + ChatFormatting.RESET + " " + ((int)(openEntryInstance.getBbWidth() * 1000)) / 1000f + "x" + ((int)(openEntryInstance.getBbHeight() * 1000)) / 1000f);
				openEntryStatsLines.add("");

				if (livingInstance != null) {
					openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.health") + ChatFormatting.RESET + " " + livingInstance.getMaxHealth());

					if (EntityUtil.safelyGetAttributeValue(livingInstance, Attributes.ARMOR) > 0)
						openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.armour") + ChatFormatting.RESET + " " + livingInstance.getAttribute(Attributes.ARMOR).getValue());

					if (EntityUtil.safelyGetAttributeValue(livingInstance, Attributes.ARMOR_TOUGHNESS) > 0)
						openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.toughness") + ChatFormatting.RESET + " " + livingInstance.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue());

					if (EntityUtil.safelyGetAttributeValue(livingInstance, Attributes.KNOCKBACK_RESISTANCE) > 0)
						openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.knockback") + ChatFormatting.RESET + " " + (livingInstance.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue() * 100) + "%");

					AttributeInstance attackAttribute = livingInstance.getAttribute(Attributes.ATTACK_DAMAGE);

					if (attackAttribute != null && attackAttribute.getValue() > 0)
						openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.strength") + ChatFormatting.RESET + " " + attackAttribute.getValue());

					if (openEntryInstance instanceof AoARangedAttacker) {
						if (openEntryInstance instanceof AoARangedMob) {
							openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.projectileStrength") + ChatFormatting.RESET + " " + ((AoARangedMob)openEntryInstance).getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
						}
						else if (openEntryInstance instanceof AoAFlyingRangedMob) {
							openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.projectileStrength") + ChatFormatting.RESET + " " + ((AoAFlyingRangedMob)openEntryInstance).getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
						}
					}

					openEntryStatsLines.add(ChatFormatting.BOLD + LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.speed") + ChatFormatting.RESET + " " + (int)(livingInstance.getAttribute(Attributes.MOVEMENT_SPEED).getValue() * 1000) / 1000f);
				}

				String bestiaryInfo = BestiaryReloadListener.BESTIARY.get(registryName);

				if (bestiaryInfo != null)
					openEntryInfoLines = font.split(Component.literal(bestiaryInfo), (int)(734 / 1.5f));
			}
		}
		else {
			openEntryIndex = -1;
			openEntryHeight = 0;
		}
	}

	public static void registerThirdPartyBestiaryHandler(String modId, Function<Entity, Tuple> entityHandlerFunction) {
		if (!modId.equalsIgnoreCase("aoa3"))
			registeredEntryHandlers.put(modId, entityHandlerFunction);
	}

	private static class EntityStats {
		@Nonnull
		private final ResourceLocation registryName;
		private Stat<EntityType<?>> killStat = null;
		private Stat<EntityType<?>> deathStat = null;

		private EntityStats(Stat<EntityType<?>> stat, boolean killStat) {
			this.registryName = ForgeRegistries.ENTITY_TYPES.getKey(stat.getValue());

			if (killStat) {
				this.killStat = stat;
			}
			else {
				this.deathStat = stat;
			}
		}

		private void matchAndComplete(Stat<EntityType<?>> stat) {
			if (killStat == null) {
				this.killStat = stat;
			}
			else {
				this.deathStat = stat;
			}
		}

		private boolean hasKills() {
			return killStat != null;
		}

		private int getKills(StatsCounter statsManager) {
			return Math.max(0, statsManager.getValue(killStat));
		}

		private int getDeaths(StatsCounter statsManager) {
			return deathStat != null ? Math.max(0, statsManager.getValue(deathStat)) : 0;
		}
	}

	private class BestiaryMenu extends ScrollablePane {
		public BestiaryMenu(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float renderingScale) {
			super(minecraft, top, left, viewHeight, viewWidth, renderingScale);
		}

		@Override
		public int getFullPaneHeight() {
			return receivedStats ? openEntryIndex < 0 ? (filteredMobList.size() + filteredMobList.size() % 2) * 100 + 20 : openEntryHeight : 0;
		}

		@Override
		public void drawPaneContents(PoseStack matrix, int top, int left, int right, int bottom, float scrollDistance, float partialTicks) {
			matrix.pushPose();

			if (!receivedStats) {
				RenderUtil.drawCenteredScaledString(matrix, font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.downloading"), left + (int)(viewWidth / 2f), top + (int)(viewHeight / 2f) - 20, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
				RenderUtil.drawCenteredScaledString(matrix, font, LOADING_SYMBOLS[(int)(Util.getMillis() / 150L % (long)LOADING_SYMBOLS.length)], left + (int)(viewWidth / 2f), top + (int)(viewHeight / 2f), 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			}
			else if (filteredMobList.isEmpty()) {
				if (!statList.isEmpty()) {
					RenderUtil.drawColouredBox(matrix, left, AdventMainGui.scaledTabRootY, 0, 20, 20, 0xFF202020);
					RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
					RenderSystem.setShaderTexture(0, iconsTextures);
					RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
					RenderUtil.renderScaledCustomSizedTexture(matrix, left + 2, AdventMainGui.scaledTabRootY + 2, 0, 32, 16, 16, 16, 16, 16, 48);

					if (searchField.isVisible()) {
						searchField.setX((int)((left + 20) / 2d));
						searchField.setY((int)(AdventMainGui.scaledTabRootY / 2d));
						searchField.setWidth((int)((right - left - 40) / 2d));
						matrix.scale(2, 2, 2);
						searchField.render(matrix, adjustedMouseX, adjustedMouseX, partialTicks);
						matrix.scale(0.5f, 0.5f, 0.5f);
					}

					RenderUtil.drawCenteredScaledString(matrix, font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.emptySearch"), left + (int)(viewWidth / 2f), top + (int)(viewHeight / 2f) - 20, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
				}
				else {
					RenderUtil.drawCenteredScaledString(matrix, font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.bestiary.empty"), left + (int)(viewWidth / 2f), top + (int)(viewHeight / 2f) - 20, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
				}
			}
			else {
				if (openEntryIndex < 0) {
					for (int i = Math.max(0, (int)(scrollDistance / 200f) * 2); i * 100 <= bottom - top && i < filteredMobList.size(); i += 2) {
						EntityStats entityStat = filteredMobList.get(i);
						ResourceLocation registryName = entityStat.registryName;
						int rowTop = top + 20 + i * 100;
						Entity entity = getEntityFromStat(entityStat);
						Component entityName = entity != null ? entityStat.killStat.getValue().getDescription() : LocaleUtil.getLocaleMessage("entity." + registryName.getNamespace().replace(".minecraft", "") + "." + registryName.getPath());

						RenderUtil.drawColouredBox(matrix, left + 40, rowTop + 30, 0, 320, 150, 0xFF202020);

						if (entity != null)
							drawEntity(matrix, entity, left + 200, rowTop + 170, 50f);

						matrix.pushPose();
						matrix.translate(0, 0, 100);
						RenderUtil.drawColouredBox(matrix, left + 40, rowTop, 0, 320, 30, 0xFF010101);
						RenderUtil.drawCenteredScaledMessage(matrix, font, entityName, left + 200, rowTop + 8, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
						RenderSystem.setShaderTexture(0, iconsTextures);
						//RenderSystem.enableAlphaTest();
						RenderUtil.renderScaledCustomSizedTexture(matrix, left + 300, rowTop + 160, 0, 0, 16, 16, 16, 16, 16, 48);
						RenderUtil.renderScaledCustomSizedTexture(matrix, left + 43, rowTop + 160, 0, 16, 16, 16, 16, 16, 16, 48);
						//RenderSystem.disableAlphaTest();
						RenderUtil.drawScaledString(matrix, font, NumberUtil.floorAndAppendSuffix(stats.getValue(entityStat.killStat), true), left + 60, rowTop + 163, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
						RenderUtil.drawScaledString(matrix, font, NumberUtil.floorAndAppendSuffix(stats.getValue(entityStat.deathStat), true), left + 320, rowTop + 163, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
						matrix.popPose();

						if (i + 1 < filteredMobList.size()) {
							entityStat = filteredMobList.get(i + 1);
							entity = getEntityFromStat(entityStat);
							entityName = entity != null ? entity.getDisplayName() : LocaleUtil.getLocaleMessage("entity." + entityStat.registryName.getNamespace().replace(".minecraft", "") + "." + entityStat.registryName.getPath());

							RenderUtil.drawColouredBox(matrix, right - 360, rowTop + 30, 0, 320, 150, 0xFF202020);

							if (entity != null)
								drawEntity(matrix, entity, right - 200, rowTop + 170, 50f);

							matrix.pushPose();
							matrix.translate(0, 0, 100);
							RenderUtil.drawColouredBox(matrix, right - 360, rowTop, 0, 320, 30, 0xFF010101);
							RenderUtil.drawCenteredScaledMessage(matrix, font, entityName, right - 200, rowTop + 8, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
							RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
							RenderSystem.setShaderTexture(0, iconsTextures);
							//RenderSystem.enableAlphaTest();
							RenderUtil.renderScaledCustomSizedTexture(matrix, right - 100, rowTop + 160, 0, 0, 16, 16, 16, 16, 16, 48);
							RenderUtil.renderScaledCustomSizedTexture(matrix, right - 357, rowTop + 160, 0, 16, 16, 16, 16, 16, 16, 48);
							//RenderSystem.disableAlphaTest();
							RenderUtil.drawScaledString(matrix, font, NumberUtil.floorAndAppendSuffix(stats.getValue(entityStat.killStat), true), right - 340, rowTop + 163, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
							RenderUtil.drawScaledString(matrix, font, NumberUtil.floorAndAppendSuffix(stats.getValue(entityStat.deathStat), true), right - 80, rowTop + 163, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
							matrix.popPose();
						}
					}

					RenderSystem.disableDepthTest();
					RenderUtil.drawColouredBox(matrix, left, AdventMainGui.scaledTabRootY, 0, 20, 20, 0xFF202020);
					RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
					RenderSystem.setShaderTexture(0, iconsTextures);
					RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
					RenderUtil.renderScaledCustomSizedTexture(matrix, left + 2, AdventMainGui.scaledTabRootY + 2, 0, 32, 16, 16, 16, 16, 16, 48);

					if (searchField.isVisible()) {
						searchField.setX((int)((left + 20) / 2d));
						searchField.setY((int)(AdventMainGui.scaledTabRootY / 2d));
						searchField.setWidth((int)((right - left - 40) / 2d));
						matrix.scale(2, 2, 2);
						searchField.render(matrix, adjustedMouseX, adjustedMouseX, partialTicks);
						matrix.scale(0.5f, 0.5f, 0.5f);
					}

					RenderSystem.enableDepthTest();
				}
				else {
					EntityStats entityStat = filteredMobList.get(openEntryIndex);
					ResourceLocation registryName = entityStat.registryName;
					openEntryHeight = Math.max(viewHeight - 30, 320 + (int)(openEntryInfoLines.size() * font.lineHeight * 1.5f));

					RenderUtil.drawColouredBox(matrix, left, top + 30, 0, right - left, bottom - top, 0xFF202020);
					RenderUtil.drawColouredBox(matrix, left, top, 0, right - left, 30, 0xFF010101);
					Component entityName = openEntryInstance != null ? openEntryInstance.getName() : LocaleUtil.getLocaleMessage("entity." + registryName.getNamespace().replace(".minecraft", "") + "." + registryName.getPath());

					RenderUtil.drawCenteredScaledMessage(matrix, font, entityName, left + (int)(viewWidth / 2f), top + 8, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);

					if (openEntryInstance != null)
						drawEntity(matrix, openEntryInstance, left + 200, top + 240, 75f);

					RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
					RenderSystem.setShaderTexture(0, iconsTextures);
					//RenderSystem.enableAlphaTest();
					RenderUtil.renderScaledCustomSizedTexture(matrix, left + 425, top + 45, 0, 16, 16, 16, 16, 16, 16, 48);
					RenderUtil.renderScaledCustomSizedTexture(matrix, left + 425, top + 65, 0, 0, 16, 16, 16, 16, 16, 48);
					//RenderSystem.disableAlphaTest();
					RenderUtil.drawScaledString(matrix, font, "X", right - 20, top + 5, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
					RenderUtil.drawScaledString(matrix, font, NumberUtil.floorAndAppendSuffix(stats.getValue(entityStat.killStat), true), left + 445, top + 48, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);
					RenderUtil.drawScaledString(matrix, font, NumberUtil.floorAndAppendSuffix(stats.getValue(entityStat.deathStat), true), left + 445, top + 68, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.NORMAL);

					matrix.scale(1.5f, 1.5f, 1.5f);

					for (int i = 0; i < openEntryStatsLines.size(); i++) {
						font.drawShadow(matrix, openEntryStatsLines.get(i), (int)((left + 425) / 1.5f), (int)((top + 100 + 14 * i) / 1.5f), ColourUtil.WHITE);
					}

					for (int i = 0; i < openEntryInfoLines.size(); i++) {
						font.draw(matrix, openEntryInfoLines.get(i), (int)((left + 20) / 1.5f), (int)((top + 300 + i * 14) / 1.5f), ColourUtil.WHITE);
					}
				}
			}

			matrix.popPose();
		}

		@Override
		public void drawBackground(PoseStack matrix) {}

		private void drawEntity(PoseStack matrix, Entity entity, int posX, int posY, float scale) {
			matrix.pushPose();
			matrix.translate((float)posX, (float)posY, 1050.0F);
			matrix.scale(1.0F, 1.0F, -1.0F);

			Minecraft mc = Minecraft.getInstance();

			float sizeFactor = Math.max(entity.getBbWidth(), entity.getBbHeight());

			if (sizeFactor > 2.5D)
				scale /= sizeFactor / 2.5;

			matrix.translate(0.0D, 0.0D, 1000.0D);
			matrix.scale(scale, scale, scale);
			matrix.mulPose(Axis.XP.rotationDegrees(180f));

			entity.tickCount = mc.player.tickCount;
			entity.setYRot(0);
			entity.setXRot(0);
			EntityRenderDispatcher renderManager = mc.getEntityRenderDispatcher();

			renderManager.setRenderShadow(false);

			MultiBufferSource.BufferSource renderBuffer = mc.renderBuffers().bufferSource();

			renderManager.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrix, renderBuffer, LightTexture.FULL_BRIGHT);
			renderBuffer.endBatch();
			renderManager.setRenderShadow(true);
			matrix.popPose();
		}

		@Override
		public boolean handleMouseClick(double mouseX, double mouseY, int button) {
			super.handleMouseClick(mouseX, mouseY, button);

			if (!receivedStats)
				return false;

			int relativeMouseX = (int)mouseX - left + 2;

			if (relativeMouseX < 0)
				return false;

			if (openEntryIndex < 0) {
				if (relativeMouseX <= 20 && mouseY - top + 2 < 20) {
					searchField.setVisible(!searchField.isVisible());

					if (searchField.isVisible()) {
						searchField.setFocused(true);
					}
					else {
						searchField.setValue("");
						searchFilterBestiaryEntries(false);
					}
				}
				else if (searchField.isVisible()) {
					if (searchField.mouseClicked((int)((relativeMouseX + left) / 2d), (int)((mouseY - 2) / 2d), 0))
						return true;
				}

				if (relativeMouseX < 40 || relativeMouseX > right - left - 40)
					return false;
			}

			int newTop = top - Math.max(0, (int)distanceScrolled);
			int relativeMouseY = (int)mouseY - newTop + 2;

			if (mouseY - top < 0 || mouseY > top + viewHeight)
				return false;

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

				if (selectedIndex >= 0 && filteredMobList.size() > selectedIndex) {
					openEntryIndex = selectedIndex;
					openEntryHeight = 600;
					lastDistanceScrolled = distanceScrolled;
					distanceScrolled = 0;

					if (lastOpenIndex != selectedIndex) {
						if (openEntryInstance != null)
							openEntryInstance.discard();

						gatherEntityStats(filteredMobList.get(selectedIndex));
					}

					lastOpenIndex = selectedIndex;
					searchField.setVisible(false);
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

			return true;
		}
	}
}
