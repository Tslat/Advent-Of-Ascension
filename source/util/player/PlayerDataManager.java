package net.tslat.aoa3.util.player;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ResourceDataPacket;
import net.tslat.aoa3.common.packet.packets.SkillDataPacket;
import net.tslat.aoa3.common.packet.packets.TributeDataPacket;
import net.tslat.aoa3.common.packet.packets.XpGainPacket;
import net.tslat.aoa3.common.registration.AoAAdvancementTriggers;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.library.misc.PortalCoordinatesContainer;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.skill.ButcheryUtil;
import net.tslat.aoa3.util.skill.InnervationUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public final class PlayerDataManager {
	private final ServerPlayerEntity player;

	private int nextEnergyRegenTime = 0;
	private int nextRageRegenTime = 0;

	private int revengeTimer = 0;
	private LivingEntity revengeTarget;

	private HashMap<DimensionType, PortalCoordinatesContainer> portalCoordinatesMap = new HashMap<DimensionType, PortalCoordinatesContainer>();

	private HashSet<ItemStack> interventionData = null;

	private final PlayerEquipment equipment;
	private final PlayerStats stats;
	private final PlayerBuffs buffs;

	private int nextMessageTime = 0;
	private TranslationTextComponent lastMessage = null;

	private boolean resourcesUpdated = false;

	public PlayerDataManager(ServerPlayerEntity player) {
		this.player = player;
		this.equipment = new PlayerEquipment(this);
		this.stats = new PlayerStats(this);
		this.buffs = new PlayerBuffs();
	}

	public ServerPlayerEntity player() {
		return player;
	}

	public PlayerEquipment equipment() {
		return equipment;
	}

	public PlayerStats stats() {
		return stats;
	}

	public PlayerBuffs buffs() {
		return buffs;
	}

	public void tickPlayer() {
		if (player == null || player.isSpectator() || player.world.isRemote)
			return;

		equipment.handleEquipmentCheck(this);
		equipment.tickEquipment(this);

		if (AoAConfig.COMMON.resourcesEnabled.get())
			stats.regenResources();

		stats.doTributeBuffs();

		if (revengeTimer > 0) {
			--revengeTimer;

			if (revengeTimer == 0)
				disableRevenge();
		}

		if (resourcesUpdated)
			AoAPackets.messagePlayer(player, new ResourceDataPacket(stats.resources.get(Resources.CREATION), stats.resources.get(Resources.ENERGY), stats.resources.get(Resources.RAGE), stats.resources.get(Resources.SOUL), isRevengeActive()));
	}

	public void sendThrottledChatMessage(String langKey, @Nonnull Object... args) {
		Style style = null;
		int styleArgs = 0;

		for (Object arg : args) {
			if (arg.getClass() == TextFormatting.class) {
				if (style == null)
					style = new Style();

				switch ((TextFormatting)arg) {
					case UNDERLINE:
						style.setUnderlined(true);
						break;
					case RESET:
						break;
					case ITALIC:
						style.setItalic(true);
						break;
					case STRIKETHROUGH:
						style.setStrikethrough(true);
						break;
					case BOLD:
						style.setBold(true);
						break;
					case OBFUSCATED:
						style.setObfuscated(true);
						break;
					default:
						style.setColor((TextFormatting)arg);
						break;
				}

				styleArgs++;
			}
		}

		Object[] arguments = new Object[args.length - styleArgs];
		int i = 0;

		for (Object arg : args) {
			if (arg.getClass() != TextFormatting.class) {
				arguments[i] = arg;
				i++;
			}
		}

		TranslationTextComponent message = new TranslationTextComponent(langKey, arguments);

		if (style != null)
			message.setStyle(style);

		if (message.equals(lastMessage) && (nextMessageTime > GlobalEvents.tick || GlobalEvents.tick + 300 < nextMessageTime))
			return;

		nextMessageTime = GlobalEvents.tick + 200;
		lastMessage = message;
		player.sendMessage(message);
	}

	private void storeInterventionItems() {
		if (interventionData == null)
			interventionData = new HashSet<ItemStack>();

		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack stack = player.inventory.getStackInSlot(i);

			if (EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.INTERVENTION.get(), stack) > 0) {
				if (RandomUtil.oneInNChance(5))
					stack = ItemUtil.removeEnchantment(stack, AoAEnchantments.INTERVENTION.get());

				interventionData.add(stack);
				player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
			}
		}

		for (int i = 0; i < 4; i++) {
			ItemStack stack = player.inventory.armorInventory.get(i);

			if (EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.INTERVENTION.get(), stack) > 0) {
				if (RandomUtil.oneInNChance(5))
					stack = ItemUtil.removeEnchantment(stack, AoAEnchantments.INTERVENTION.get());

				interventionData.add(stack);
				player.inventory.armorInventory.set(i, ItemStack.EMPTY);
			}
		}
	}

	private void retrieveInterventionItems() {
		if (interventionData != null) {
			for (ItemStack stack : interventionData) {
				ItemUtil.givePlayerItemOrDrop(player, stack);
			}

			interventionData = null;
		}
	}

	public void storeInterventionItem(@Nonnull ItemStack stack) {
		if (stack.isEmpty())
			return;

		if (interventionData == null)
			interventionData = new HashSet<ItemStack>();

		interventionData.add(stack);
	}

	public void setPortalReturnLocation(DimensionType toDim, PortalCoordinatesContainer coords) {
		portalCoordinatesMap.put(toDim, coords);
	}

	public void removePortalReturnLocation(DimensionType toDim) {
		portalCoordinatesMap.remove(toDim);
	}

	public void flushPortalReturnLocations() {
		portalCoordinatesMap.clear();
	}

	public PortalCoordinatesContainer getPortalReturnLocation(DimensionType toDim) {
		return portalCoordinatesMap.get(toDim);
	}

	public void handleIncomingAttack(final LivingAttackEvent ev) {
		if (!ev.isCanceled())
			equipment.handleIncomingAttack(ev);
	}

	public void handleIncomingDamage(final LivingHurtEvent ev) {
		if (!ev.isCanceled())
			equipment.handleIncomingDamage(ev);
	}

	public void handleOutgoingDamage(final LivingHurtEvent ev) {
		if (!ev.isCanceled())
			equipment.handleOutgoingDamage(ev);
	}

	public void handleDamageTriggers(final LivingDamageEvent ev) {
		if (!ev.isCanceled())
			equipment.handleDamageTriggers(ev);
	}

	public void handlePlayerFalling(final LivingFallEvent ev) {
		if (!ev.isCanceled())
			equipment.handlePlayerFalling(ev);
	}

	public void handlePlayerDeath(final LivingDeathEvent ev) {
		equipment.handlePlayerDeath(ev);
		storeInterventionItems();

		if (AoAConfig.COMMON.hardcoreMode.get()) {
			Skills skill = RandomUtil.getRandomSelection(Skills.values());

			stats.levels.put(skill, Math.max(1, stats.levels.get(skill) - 1));
		}
	}

	public void handlePlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		retrieveInterventionItems();
	}

	private void checkAndUpdateLegitimacy() {
		if (player instanceof ServerPlayerEntity) {
			Advancement adv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/by_the_books"));

			if (adv == null)
				return;

			boolean legit = player.getAdvancements().getProgress(adv).isDone();
			PlayerAdvancements plAdv = player.getAdvancements();
			int opt = stats.optionals.get(Skills.EXPEDITION);

			if (opt > 3 && legit) {
				plAdv.revokeCriterion(adv, "legitimate");
			}
			else if (!legit) {
				Advancement rootAdv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/root"));

				if (!plAdv.getProgress(rootAdv).isDone()) {
					plAdv.grantCriterion(rootAdv, "playerjoin");
					plAdv.grantCriterion(adv, "legitimate");
				}
				else {
					stats.optionals.put(Skills.EXPEDITION, (opt % 4) + 4);
				}
			}
		}
	}

	private void applyLegitimacyPenalties() {
		stats.optionals.put(Skills.EXPEDITION, (stats.optionals.get(Skills.EXPEDITION) % 4) + 4);
		Advancement byTheBooksAdv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/by_the_books"));

		if (byTheBooksAdv != null)
			player.getAdvancements().revokeCriterion(byTheBooksAdv, "legitimate");

		AoAPackets.messagePlayer(player, new SkillDataPacket(Skills.EXPEDITION.id, stats.levels.get(Skills.EXPEDITION), stats.xp.get(Skills.EXPEDITION), stats.optionals.get(Skills.EXPEDITION)));
	}

	public void enableRevenge(final LivingEntity target) {
		revengeTimer = 400;
		revengeTarget = target;
		resourcesUpdated = true;
	}

	public boolean isRevengeActive() {
		return revengeTimer > 0 && revengeTarget != null;
	}

	public void disableRevenge() {
		revengeTimer = 0;
		revengeTarget = null;
		resourcesUpdated = true;
	}

	public LivingEntity getRevengeTarget() {
		return revengeTarget;
	}

	void cloneFromExistingPlayerData(PlayerDataManager sourcePlayerData) {
		interventionData = sourcePlayerData.interventionData;

		for (Skills skill : Skills.values()) {
			stats.levels.put(skill, sourcePlayerData.stats.levels.get(skill));
			stats.xp.put(skill, sourcePlayerData.stats.getExp(skill));
		}

		for (Map.Entry<Skills, Integer> skillDataEntry : sourcePlayerData.stats.optionals.entrySet()) {
			stats.optionals.put(skillDataEntry.getKey(), skillDataEntry.getValue());
		}

		for (Deities deity : Deities.values()) {
			stats.tribute.put(deity, sourcePlayerData.stats.tribute.get(deity));
		}

		equipment.cooldowns = sourcePlayerData.equipment.cooldowns;
		portalCoordinatesMap = sourcePlayerData.portalCoordinatesMap;

		if (AoAConfig.COMMON.skillsEnabled.get()) {
			EntityUtil.applyAttributeModifierSafely(player, SharedMonsterAttributes.MAX_HEALTH, InnervationUtil.getHealthModifier(stats.getLevel(Skills.INNERVATION)));
			player.setHealth(player.getMaxHealth());
		}
	}

	public CompoundNBT saveToNBT() {
		CompoundNBT baseTag = new CompoundNBT();

		stats.saveToNBT(baseTag);

		if (!portalCoordinatesMap.isEmpty()) {
			CompoundNBT portalCoordinatesNBT = new CompoundNBT();

			for (Map.Entry<DimensionType, PortalCoordinatesContainer> entry : portalCoordinatesMap.entrySet()) {
				CompoundNBT portalReturnTag = new CompoundNBT();
				PortalCoordinatesContainer container = entry.getValue();

				portalReturnTag.putString("FromDim", DimensionType.getKey(container.fromDim).toString());
				portalReturnTag.putDouble("PosX", container.x);
				portalReturnTag.putDouble("PosY", container.y);
				portalReturnTag.putDouble("PosZ", container.z);

				portalCoordinatesNBT.put(DimensionType.getKey(entry.getKey()).toString(), portalReturnTag);
			}

			baseTag.put("PortalMap", portalCoordinatesNBT);
		}

		return baseTag;
	}

	public void loadFromNBT(CompoundNBT baseTag) {
		stats.loadFromNBT(baseTag);

		if (baseTag.contains("PortalMap")) {
			CompoundNBT portalMapTag = baseTag.getCompound("PortalMap");

			for (String s : portalMapTag.keySet()) {
				CompoundNBT portalReturnTag = portalMapTag.getCompound(s);
				ResourceLocation fromDim = new ResourceLocation(portalReturnTag.getString("FromDim"));
				double x = portalReturnTag.getDouble("PosX");
				double y = portalReturnTag.getDouble("PosY");
				double z = portalReturnTag.getDouble("PosZ");

				try {
					portalCoordinatesMap.put(DimensionType.byName(new ResourceLocation(s)), new PortalCoordinatesContainer(DimensionType.byName(fromDim), x, y, z));
				}
				catch (NumberFormatException e) {
					Logging.logMessage(Level.WARN, "Found invalid portal map data, has someone been tampering with files? Data: " + s);
				}
			}
		}

		checkAndUpdateLegitimacy();
	}

	public final class PlayerEquipment {
		private final PlayerDataManager playerDataManager;

		private HashMap<String, Integer> cooldowns = new HashMap<String, Integer>(1);
		private HashMap<AdventArmour.Type, ArmourEffectWrapper> armourMap = new HashMap<AdventArmour.Type, ArmourEffectWrapper>(4);

		private boolean checkEquipment = true;

		private AdventArmour currentFullSet = null;

		private AdventArmour helmet = null;
		private AdventArmour body = null;
		private AdventArmour legs = null;
		private AdventArmour boots = null;

		private PlayerEquipment(PlayerDataManager playerData) {
			this.playerDataManager = playerData;
		}

		public void markDirty() {
			this.checkEquipment = true;
		}

		public boolean isCooledDown(String counter) {
			return !cooldowns.containsKey(counter);
		}

		public int getCooldown(String counter) {
			return cooldowns.getOrDefault(counter, 0);
		}

		public void setCooldown(String counter, int cooldownTicks) {
			cooldowns.put(counter, cooldownTicks);
		}

		public AdventArmour.Type getCurrentFullArmourSet() {
			return currentFullSet != null ? currentFullSet.setType() : AdventArmour.Type.NONE;
		}

		private void handleIncomingAttack(final LivingAttackEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPreAttackReceived(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPreAttackReceived(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		private void handleIncomingDamage(final LivingHurtEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onAttackReceived(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onAttackReceived(playerDataManager, wrapper.currentSlots, ev);
			}

			buffs.applyDefensiveBuffs(ev);
		}

		private void handleOutgoingDamage(final LivingHurtEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onDamageDealt(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onDamageDealt(playerDataManager, wrapper.currentSlots, ev);
			}

			buffs.applyOffensiveBuffs(ev);
		}

		private void handleDamageTriggers(final LivingDamageEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPostAttackReceived(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPostAttackReceived(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		private void handlePlayerFalling(final LivingFallEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPlayerLandingFall(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPlayerLandingFall(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		private void handlePlayerDeath(final LivingDeathEvent ev) {
			if (currentFullSet != null)
				currentFullSet.onPlayerDeath(playerDataManager, null, ev);

			for (ArmourEffectWrapper wrapper : armourMap.values()) {
				wrapper.armour.onPlayerDeath(playerDataManager, wrapper.currentSlots, ev);
			}
		}

		void handleEquipmentCheck(PlayerDataManager playerDataManager) {
			if (!checkEquipment)
				return;

			boolean armourChanged;

			armourChanged = checkAndHandleArmourSwap(boots, player.inventory.armorInventory.get(0).getItem(), EquipmentSlotType.FEET);
			armourChanged |= checkAndHandleArmourSwap(legs, player.inventory.armorInventory.get(1).getItem(), EquipmentSlotType.LEGS);
			armourChanged |= checkAndHandleArmourSwap(body, player.inventory.armorInventory.get(2).getItem(), EquipmentSlotType.CHEST);
			armourChanged |= checkAndHandleArmourSwap(helmet, player.inventory.armorInventory.get(3).getItem(), EquipmentSlotType.HEAD);

			AdventArmour oldSet = currentFullSet;

			if (armourChanged) {
				if (boots != null && legs != null && body != null && helmet != null && boots.setType() == legs.setType() && legs.setType() == body.setType() && body.isSetHelmet(helmet)) {
					currentFullSet = boots;

					if (currentFullSet != oldSet) {
						if (oldSet != null)
							unequipAdventArmour(playerDataManager, oldSet, null);

						equipAdventArmour(playerDataManager, currentFullSet, null);
					}
				}
				else {
					currentFullSet = null;

					if (oldSet != null)
						unequipAdventArmour(playerDataManager, oldSet, null);
				}
			}

			checkEquipment = false;
		}

		private boolean checkAndHandleArmourSwap(@Nullable AdventArmour currentPiece, @Nonnull Item newPiece, @Nonnull EquipmentSlotType slot) {
			boolean changed = false;

			if (newPiece != currentPiece) {
				changed = true;

				if (currentPiece != null)
					unequipAdventArmour(playerDataManager, currentPiece, slot);

				switch (slot) {
					case FEET:
						boots = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (ItemUtil.hasLevelForItem(boots, playerDataManager))
							equipAdventArmour(playerDataManager, boots, slot);
						break;
					case LEGS:
						legs = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (ItemUtil.hasLevelForItem(legs, playerDataManager))
							equipAdventArmour(playerDataManager, legs, slot);
						break;
					case CHEST:
						body = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (ItemUtil.hasLevelForItem(body, playerDataManager))
							equipAdventArmour(playerDataManager, body, slot);
						break;
					case HEAD:
						helmet = newPiece instanceof AdventArmour ? (AdventArmour)newPiece : null;

						if (ItemUtil.hasLevelForItem(helmet, playerDataManager))
							equipAdventArmour(playerDataManager, helmet, slot);
						break;
				}
			}

			return changed;
		}

		private void tickEquipment(PlayerDataManager playerDataManager) {
			if (!player.isAlive() || player.getHealth() <= 0)
				return;

			boolean doArmourTick = true;

			if (PlayerUtil.shouldPlayerBeAffected(player)) {
				for (Hand hand : Hand.values()) {
					ItemStack heldStack = player.getHeldItem(hand);

					if (heldStack.getItem() instanceof SkillItem) {
						SkillItem item = (SkillItem)heldStack.getItem();

						if (stats.levels.get(item.getSkill()) < item.getLevelReq()) {
							ItemHandlerHelper.giveItemToPlayer(player, heldStack);
							player.setHeldItem(hand, ItemStack.EMPTY);
						}
					}
				}

				if (boots != null && !ItemUtil.hasLevelForItem(boots, playerDataManager)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armorInventory.get(0));
					player.inventory.armorInventory.set(0, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, boots, EquipmentSlotType.FEET);
				}

				if (legs != null && !ItemUtil.hasLevelForItem(legs, playerDataManager)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armorInventory.get(1));
					player.inventory.armorInventory.set(1, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, legs, EquipmentSlotType.LEGS);
				}

				if (body != null && !ItemUtil.hasLevelForItem(body, playerDataManager)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armorInventory.get(2));
					player.inventory.armorInventory.set(2, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, body, EquipmentSlotType.CHEST);
				}

				if (helmet != null && !ItemUtil.hasLevelForItem(helmet, playerDataManager)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armorInventory.get(3));
					player.inventory.armorInventory.set(3, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, helmet, EquipmentSlotType.HEAD);
				}
			}

			if (doArmourTick) {
				if (currentFullSet != null)
					currentFullSet.onEffectTick(playerDataManager, null);

				for (ArmourEffectWrapper wrapper : armourMap.values()) {
					wrapper.armour.onEffectTick(playerDataManager, wrapper.currentSlots);
				}
			}

			handleCooldowns();
			player.container.detectAndSendChanges();
		}

		private void equipAdventArmour(PlayerDataManager plData, AdventArmour item, @Nullable EquipmentSlotType slot) {
			item.onEquip(plData, slot);
			item.addBuffs(buffs, slot);

			ArmourEffectWrapper armourEffectWrapper = armourMap.get(item.setType());

			if (slot != null) {
				if (armourEffectWrapper == null) {
					armourMap.put(item.setType(), new ArmourEffectWrapper(item, slot));
				}
				else {
					armourEffectWrapper.currentSlots.add(slot);
				}
			}
		}

		private void unequipAdventArmour(PlayerDataManager plData, AdventArmour item, @Nullable EquipmentSlotType slot) {
			item.onUnequip(plData, slot);
			item.removeBuffs(buffs, slot);

			ArmourEffectWrapper armourEffectWrapper = armourMap.get(item.setType());

			if (armourEffectWrapper != null && slot != null) {
				if (armourEffectWrapper.currentSlots.size() <= 1) {
					armourMap.remove(item.setType());
				}
				else {
					armourEffectWrapper.currentSlots.remove(slot);
				}
			}
		}

		private void handleCooldowns() {
			Iterator<Map.Entry<String, Integer>> cooldownsIterator = cooldowns.entrySet().iterator();

			while (cooldownsIterator.hasNext()) {
				Map.Entry<String, Integer> entry = cooldownsIterator.next();

				if (entry.getValue() <= 1) {
					cooldownsIterator.remove();
				}
				else {
					entry.setValue(entry.getValue() - 1);
				}
			}
		}

		private class ArmourEffectWrapper {
			private final AdventArmour armour;
			private final HashSet<EquipmentSlotType> currentSlots = new HashSet<EquipmentSlotType>(4);

			private ArmourEffectWrapper(AdventArmour armour, EquipmentSlotType firstSlotEquipped) {
				this.armour = armour;

				currentSlots.add(firstSlotEquipped);
			}
		}
	}

	public final class PlayerStats {
		private final PlayerDataManager playerDataManager;

		private final HashMap<Skills, Float> xp = new HashMap<Skills, Float>(Skills.values().length);
		private final HashMap<Skills, Integer> levels = new HashMap<Skills, Integer>(Skills.values().length);
		private final HashMap<Skills, Integer> optionals = new HashMap<Skills, Integer>(1);
		private final HashMap<Deities, Integer> tribute = new HashMap<Deities, Integer>(Deities.values().length);
		private final HashMap<Resources, Float> resources = new HashMap<Resources, Float>(Resources.values().length);

		private PlayerStats(PlayerDataManager dataManager) {
			this.playerDataManager = dataManager;

			for (Skills skill : Skills.values()) {
				xp.put(skill, 0f);
				levels.put(skill, 1);
			}

			for (Deities deity : Deities.values()) {
				tribute.put(deity, 0);
			}

			for (Resources resource : Resources.values()) {
				resources.put(resource, 0f);
			}

			optionals.put(Skills.EXPEDITION, 1);
		}

		private void regenResources() {
			if (resources.get(Resources.ENERGY) < 200 && (nextEnergyRegenTime < GlobalEvents.tick || GlobalEvents.tick + 60 < nextEnergyRegenTime))
				regenResource(Resources.ENERGY, 0.32f);

			if (resources.get(Resources.CREATION) < 200)
				regenResource(Resources.CREATION, 0.033f);

			if (resources.get(Resources.RAGE) < 200 && (nextRageRegenTime < GlobalEvents.tick || GlobalEvents.tick + 120 < nextRageRegenTime))
				regenResource(Resources.RAGE, ButcheryUtil.getTickRegen(levels.get(Skills.BUTCHERY)));

			if (resources.get(Resources.SOUL) < 200)
				regenResource(Resources.SOUL, 0.01f);
		}

		private void doTributeBuffs() {
			if (player.world.getDimension().getType() == DimensionType.OVERWORLD && !player.world.isDaytime()) {
				if (tribute.get(Deities.LUXON) == 200)
					EntityUtil.applyPotions(player, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 5).isAmbient());

				if (tribute.get(Deities.EREBON) == 200)
					EntityUtil.applyPotions(player, new PotionUtil.EffectBuilder(Effects.STRENGTH, PotionUtil.AMBIENT_POTION_DURATION).isAmbient());

				if (tribute.get(Deities.PLUTON) == 200)
					EntityUtil.applyPotions(player, new PotionUtil.EffectBuilder(Effects.LUCK, PotionUtil.AMBIENT_POTION_DURATION).level(2).isAmbient());

				if (tribute.get(Deities.SELYAN) == 200)
					EntityUtil.applyPotions(player, new PotionUtil.EffectBuilder(Effects.SPEED, PotionUtil.AMBIENT_POTION_DURATION).isAmbient());
			}
		}

		public int getLevel(Skills skill) {
			return AoAConfig.COMMON.skillsEnabled.get() ? Math.min(100, levels.get(skill)) : 100;
		}

		public int getLevelForDisplay(Skills skill) {
			return levels.get(skill);
		}

		public float getExp(Skills skill) {
			return xp.get(skill);
		}

		public float getResourceValue(Resources resource) {
			return AoAConfig.COMMON.resourcesEnabled.get() ? resources.get(resource) : 200;
		}

		public int getTribute(Deities deity) {
			return AoAConfig.COMMON.resourcesEnabled.get() ? tribute.get(deity) : 200;
		}

		@Nullable
		public Integer getSkillData(Skills skill) {
			return optionals.get(skill);
		}

		public void addXp(Skills skill, float xp, boolean isUnnatural, boolean ignoreXpBuffs) {
			if (!AoAConfig.COMMON.skillsEnabled.get())
				return;

			int lvl = levels.get(skill);

			if (lvl >= 1000)
				return;

			if (!isUnnatural && !ignoreXpBuffs)
				xp = buffs.applyXpBuffs(skill, xp);

			float remaining = Math.min(544132359, xp);
			int newLevels = 0;
			float xpRemaining = PlayerUtil.getXpRemainingUntilLevel(playerDataManager, skill);

			if (remaining > xpRemaining) {
				remaining -= xpRemaining;
				newLevels++;

				float stillRemaining;

				while ((stillRemaining = remaining - PlayerUtil.getXpRequiredForNextLevel(lvl + newLevels)) >= 0 && lvl + newLevels < 1000) {
					remaining = stillRemaining;
					newLevels++;
				}
			}

			if (newLevels > 0)
				levelUp(skill, lvl, lvl + newLevels, !isUnnatural);

			if (isUnnatural) {
				applyLegitimacyPenalties();
			}
			else {
				AoAAdvancementTriggers.xpGainTrigger.trigger(player, skill, (int)xp);
			}

			this.xp.put(skill, this.xp.get(skill) + remaining);

			checkAndUpdateLegitimacy();
			AoAPackets.messagePlayer(player, new SkillDataPacket(skill.id, levels.get(skill), this.xp.get(skill), optionals.get(skill)));
			AoAPackets.messagePlayer(player, new XpGainPacket(skill.id, xp, newLevels > 0));
		}

		public void subtractXp(Skills skill, float xp) {
			int lvl = levels.get(skill);
			float remaining = Math.min(544132359, xp);
			int newLevels = 0;

			if (lvl > 1 && remaining > this.xp.get(skill)) {
				remaining -= this.xp.get(skill);
				newLevels++;

				float stillRemaining;

				while ((stillRemaining = remaining - PlayerUtil.getXpRequiredForNextLevel(lvl - newLevels - 1)) >= 0 && lvl - newLevels > 1) {
					remaining = stillRemaining;
					newLevels++;
				}
			}

			if (newLevels > 0) {
				levels.put(skill, lvl - newLevels);
				AoAEvents.playerLevelChange(this.playerDataManager, lvl, lvl - newLevels, skill, false);
			}

			this.xp.put(skill, Math.max(0, PlayerUtil.getXpRequiredForNextLevel(lvl - newLevels) - remaining));
			applyLegitimacyPenalties();
			AoAPackets.messagePlayer(player, new SkillDataPacket(skill.id, levels.get(skill), this.xp.get(skill), optionals.get(skill)));
		}

		public void levelUp(Skills skill, int oldLevel, int newLevel, boolean isNaturalLevel) {
			if (newLevel < 100) {
				player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), AoASounds.PLAYER_LEVEL_UP.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}
			else if (newLevel == 100 || newLevel == 1000) {
				player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), AoASounds.PLAYER_LEVEL_100.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}

			levels.put(skill, newLevel);
			xp.put(skill, 0.0f);

			AoAEvents.playerLevelChange(this.playerDataManager, oldLevel, newLevel, skill, isNaturalLevel);
			AoAPackets.messagePlayer(player, new SkillDataPacket(skill.id, newLevel, xp.get(skill), optionals.get(skill)));
			AoAAdvancementTriggers.levelUpTrigger.trigger(player, skill, newLevel);
		}

		public void setLevel(Skills skill, int newLevel) {
			int oldLevel = levels.get(skill);

			levels.put(skill, MathHelper.clamp(newLevel, 1, 1000));
			xp.put(skill, 0f);

			AoAEvents.playerLevelChange(this.playerDataManager, oldLevel, newLevel, skill, false);
			applyLegitimacyPenalties();
			AoAPackets.messagePlayer(player, new SkillDataPacket(skill.id, newLevel, 0, optionals.get(skill)));
		}

		public boolean consumeResource(Resources resource, float value, boolean force) {
			if (!AoAConfig.COMMON.resourcesEnabled.get())
				return true;

			if (resource != Resources.RAGE && !PlayerUtil.shouldPlayerBeAffected(player))
				return true;

			float current = resources.get(resource);
			value = buffs.applyResourceFortitudeBuffs(resource, value);

			if (current < value && !force) {
				PlayerUtil.notifyPlayerOfInsufficientResources(player, resource, value);

				return false;
			}

			resources.put(resource, Math.max(current - value, 0));

			if (resource == Resources.ENERGY)
				nextEnergyRegenTime = GlobalEvents.tick + 50;

			if (resource == Resources.RAGE)
				nextRageRegenTime = GlobalEvents.tick + 100;

			resourcesUpdated = true;

			return !force || current > value;
		}

		public void regenResource(Resources resource, float amount) {
			float current = resources.get(resource);
			float max = 200;
			amount = buffs.applyResourceRegenBuffs(resource, amount);

			resources.put(resource, Math.min(current + amount, max));

			if (current != max)
				resourcesUpdated = true;
		}

		public void addTribute(Deities deity, int amount) {
			if (ItemUtil.isHoldingItem(player, AoAWeapons.HOLY_SWORD.get()))
				amount *= 2;

			tribute.put(deity, MathHelper.clamp(tribute.get(deity) + amount, 0, 200));
			AoAPackets.messagePlayer(player, new TributeDataPacket(tribute.get(Deities.EREBON), tribute.get(Deities.LUXON), tribute.get(Deities.PLUTON), tribute.get(Deities.SELYAN)));
		}

		public void consumeTribute(Deities deity, int amount) {
			tribute.put(deity, MathHelper.clamp(tribute.get(deity) - amount, 0, 200));
			AoAPackets.messagePlayer(player, new TributeDataPacket(tribute.get(Deities.EREBON), tribute.get(Deities.LUXON), tribute.get(Deities.PLUTON), tribute.get(Deities.SELYAN)));
		}

		public void resetAllTribute() {
			for (Deities deity : Deities.values()) {
				tribute.put(deity, 0);
			}

			AoAPackets.messagePlayer(player, new TributeDataPacket(tribute.get(Deities.EREBON), tribute.get(Deities.LUXON), tribute.get(Deities.PLUTON), tribute.get(Deities.SELYAN)));
		}

		public void incrementExpeditionBoost() {
			int optExpedition = optionals.get(Skills.EXPEDITION);

			if (optExpedition > 3) {
				++optExpedition;

				if (optExpedition > 7)
					optExpedition = 4;
			}
			else {
				++optExpedition;

				if (optExpedition > 3)
					optExpedition = 0;
			}

			optionals.put(Skills.EXPEDITION, optExpedition);
			AoAPackets.messagePlayer(player, new SkillDataPacket(Skills.EXPEDITION.id, levels.get(Skills.EXPEDITION), xp.get(Skills.EXPEDITION), optExpedition));
		}

		private void saveToNBT(CompoundNBT baseTag) {
			for (Skills skill : Skills.values()) {
				CompoundNBT skillNBT = new CompoundNBT();

				skillNBT.putInt("Level", Math.max(1, levels.get(skill)));
				skillNBT.putFloat("Exp", Math.max(0, xp.get(skill)));

				if (optionals.containsKey(skill))
					skillNBT.putInt("Opt", optionals.get(skill));

				baseTag.put(skill.toString(), skillNBT);
			}

			for (Deities deity : Deities.values()) {
				baseTag.putInt(deity.toString(), tribute.get(deity));
			}

			if (interventionData != null) {
				CompoundNBT interventionTag = new CompoundNBT();
				int i = 0;

				for (ItemStack stack : interventionData) {
					interventionTag.put(String.valueOf(i), stack.serializeNBT());
					i++;
				}

				baseTag.put("InterventionData", interventionTag);
			}
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			for (Skills skill : Skills.values()) {
				CompoundNBT skillTag = baseTag.getCompound(skill.toString());

				levels.put(skill, MathHelper.clamp(skillTag.getInt("Level"), 1, 1000));
				xp.put(skill, Math.max(0, skillTag.getFloat("Exp")));

				if (skillTag.contains("Opt"))
					optionals.put(skill, skillTag.getInt("Opt"));
			}

			for (Deities deity : Deities.values()) {
				tribute.put(deity, MathHelper.clamp(baseTag.getInt(deity.toString()), 0, 200));
			}

			if (baseTag.contains("InterventionData")) {
				interventionData = new HashSet<ItemStack>();
				CompoundNBT interventionTag = baseTag.getCompound("InterventionData");
				int i = 0;

				while (interventionTag.contains(String.valueOf(i))) {
					interventionData.add(ItemStack.read(interventionTag.getCompound(String.valueOf(i))));

					i++;
				}
			}
		}
	}

	public static final class PlayerBuffs {
		private HashMap<String, Float> defensiveBuffs = null;
		private HashMap<String, Float> offensiveBuffs = null;
		private HashMap<String, Float> miscBuffs = null;

		private float globalXpMod = 1;
		private HashMap<Skills, Float> xpMods = null;

		private HashMap<Resources, Float> resourceRegenBuffs = null;
		private HashMap<Resources, Float> resourceFortitudeBuffs = null;

		private void applyDefensiveBuffs(LivingHurtEvent ev) {
			if (defensiveBuffs != null && defensiveBuffs.containsKey(ev.getSource().getDamageType()))
				ev.setAmount(ev.getAmount() * (1 - defensiveBuffs.get(ev.getSource().getDamageType())));
		}

		private void applyOffensiveBuffs(LivingHurtEvent ev) {
			if (offensiveBuffs != null && offensiveBuffs.containsKey(ev.getSource().getDamageType()))
				ev.setAmount(ev.getAmount() * (1 + offensiveBuffs.get(ev.getSource().getDamageType())));
		}

		private float applyXpBuffs(Skills skill, float baseXp) {
			baseXp *= AoAConfig.SERVER.globalXpModifier.get();
			baseXp *= globalXpMod;

			if (xpMods != null && xpMods.containsKey(skill))
				baseXp *= (1 + xpMods.get(skill));

			return baseXp;
		}

		private float applyResourceRegenBuffs(Resources resource, float amountToRegen) {
			if (resourceRegenBuffs != null && resourceRegenBuffs.containsKey(resource))
				amountToRegen *= (1 + resourceRegenBuffs.get(resource));

			return amountToRegen;
		}

		private float applyResourceFortitudeBuffs(Resources resource, float amountToConsume) {
			if (resourceFortitudeBuffs != null && resourceFortitudeBuffs.containsKey(resource))
				amountToConsume *= (1 - resourceFortitudeBuffs.get(resource));

			return amountToConsume;
		}

		public void addDefensiveBuff(String damageType, float modifier) {
			if (defensiveBuffs == null)
				defensiveBuffs = new HashMap<String, Float>(2);

			defensiveBuffs.merge(damageType, modifier, Float::sum);
		}

		public void addOffensiveBuff(String damageType, float modifier) {
			if (offensiveBuffs == null)
				offensiveBuffs = new HashMap<String, Float>(2);

			offensiveBuffs.merge(damageType, modifier, Float::sum);
		}

		public void removeDefensiveBuff(String damageType, float modifier) {
			if (defensiveBuffs != null) {
				defensiveBuffs.merge(damageType, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (defensiveBuffs.get(damageType) == 0)
					defensiveBuffs.remove(damageType);
			}
		}

		public void removeOffensiveBuff(String damageType, float modifier) {
			if (offensiveBuffs != null) {
				offensiveBuffs.merge(damageType, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (offensiveBuffs.get(damageType) == 0)
					offensiveBuffs.remove(damageType);
			}
		}

		public void addMiscBuff(String buffName, float modifier) {
			if (miscBuffs == null)
				miscBuffs = new HashMap<String, Float>(2);

			miscBuffs.merge(buffName, modifier, Float::sum);
		}

		public void removeMiscBuff(String buffName, float modifier) {
			if (miscBuffs != null) {
				miscBuffs.merge(buffName, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (miscBuffs.get(buffName) == 0)
					miscBuffs.remove(buffName);
			}
		}

		public float getGlobalXpModifier() {
			return globalXpMod;
		}

		public void addGlobalXpModifier(float modifier) {
			globalXpMod += modifier;
		}

		public void removeGlobalXpModifier(float modifier) {
			globalXpMod -= modifier;
		}

		public void addXpModifier(Skills skill, float modifier) {
			if (xpMods == null)
				xpMods = new HashMap<Skills, Float>(2);

			xpMods.merge(skill, modifier, Float::sum);
		}

		public void removeXpModifier(Skills skill, float modifier) {
			if (xpMods != null) {
				xpMods.merge(skill, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (xpMods.get(skill) == 0)
					xpMods.remove(skill);
			}
		}

		public void addResourceRegenModifier(Resources resource, float modifier) {
			if (resourceRegenBuffs == null)
				resourceRegenBuffs = new HashMap<Resources, Float>(2);

			resourceRegenBuffs.merge(resource, modifier, Float::sum);
		}

		public void removeResourceRegenModifier(Resources resource, float modifier) {
			if (resourceRegenBuffs != null) {
				resourceRegenBuffs.merge(resource, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (resourceRegenBuffs.get(resource) == 0)
					resourceRegenBuffs.remove(resource);
			}
		}

		public void addResourceFortitudeModifier(Resources resource, float modifier) {
			if (resourceFortitudeBuffs == null)
				resourceFortitudeBuffs = new HashMap<Resources, Float>(2);

			resourceFortitudeBuffs.merge(resource, modifier, Float::sum);
		}

		public void removeResourceFortitudeModifier(Resources resource, float modifier) {
			if (resourceFortitudeBuffs != null) {
				resourceFortitudeBuffs.merge(resource, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (resourceFortitudeBuffs.get(resource) == 0)
					resourceFortitudeBuffs.remove(resource);
			}
		}
	}
}
