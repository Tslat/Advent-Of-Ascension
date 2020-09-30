package net.tslat.aoa3.utils.player;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.PacketResourceData;
import net.tslat.aoa3.common.packet.PacketSkillData;
import net.tslat.aoa3.common.packet.PacketTributeData;
import net.tslat.aoa3.common.packet.PacketXpGain;
import net.tslat.aoa3.common.registration.AdvancementTriggerRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.event.AoAEvents;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.leaderboard.AoALeaderboard;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.library.misc.PortalCoordinatesContainer;
import net.tslat.aoa3.utils.*;
import net.tslat.aoa3.utils.skills.ButcheryUtil;
import net.tslat.aoa3.utils.skills.InnervationUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public final class PlayerDataManager {
	private final EntityPlayer player;

	private int nextEnergyRegenTime = 0;
	private int nextRageRegenTime = 0;

	private int revengeTimer = 0;
	private EntityLivingBase revengeTarget;

	private HashMap<Integer, PortalCoordinatesContainer> portalCoordinatesMap = new HashMap<Integer, PortalCoordinatesContainer>();

	private HashSet<ItemStack> interventionData = null;

	private final PlayerEquipment equipment;
	private final PlayerStats stats;
	private final PlayerBuffs buffs;

	private int nextMessageTime = 0;
	private TextComponentTranslation lastMessage = null;

	private boolean resourcesUpdated = false;

	public PlayerDataManager(EntityPlayer player) {
		this.player = player;
		this.equipment = new PlayerEquipment(this);
		this.stats = new PlayerStats(this);
		this.buffs = new PlayerBuffs();
	}

	public EntityPlayer player() {
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

		if (ConfigurationUtil.MainConfig.resourcesEnabled)
			stats.regenResources();

		stats.doTributeBuffs();

		if (revengeTimer > 0) {
			--revengeTimer;

			if (revengeTimer == 0)
				disableRevenge();
		}

		if (resourcesUpdated && player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketResourceData(stats.resources.get(Enums.Resources.CREATION), stats.resources.get(Enums.Resources.ENERGY), stats.resources.get(Enums.Resources.RAGE), stats.resources.get(Enums.Resources.SOUL), isRevengeActive()), (EntityPlayerMP)player);
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

		TextComponentTranslation message = new TextComponentTranslation(langKey, arguments);

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

			if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.INTERVENTION, stack) > 0) {
				if (AdventOfAscension.rand.nextInt(5) == 0)
					stack = ItemUtil.removeEnchantment(stack, EnchantmentsRegister.INTERVENTION);

				interventionData.add(stack);
				player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
			}
		}

		for (int i = 0; i < 4; i++) {
			ItemStack stack = player.inventory.armorInventory.get(i);

			if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.INTERVENTION, stack) > 0) {
				if (AdventOfAscension.rand.nextInt(5) == 0)
					stack = ItemUtil.removeEnchantment(stack, EnchantmentsRegister.INTERVENTION);

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

	public void setPortalReturnLocation(Integer toDimId, PortalCoordinatesContainer coords) {
		portalCoordinatesMap.put(toDimId, coords);
	}

	public void removePortalReturnLocation(Integer toDimId) {
		portalCoordinatesMap.remove(toDimId);
	}

	public void flushPortalReturnLocations() {
		portalCoordinatesMap.clear();
	}

	public PortalCoordinatesContainer getPortalReturnLocation(Integer toDimId) {
		return portalCoordinatesMap.get(toDimId);
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

		if (ConfigurationUtil.MainConfig.funOptions.hardcoreMode) {
			Enums.Skills skill = Enums.Skills.values()[AdventOfAscension.rand.nextInt(Enums.Skills.values().length)];

			stats.levels.put(skill, Math.max(1, stats.levels.get(skill) - 1));
		}
	}

	public void handlePlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		retrieveInterventionItems();
	}

	private void checkAndUpdateLegitimacy() {
		if (player instanceof EntityPlayerMP) {
			Advancement adv = ModUtil.getAdvancement("overworld/by_the_books");

			if (adv == null)
				return;

			boolean legit = ((EntityPlayerMP)player).getAdvancements().getProgress(adv).isDone();
			PlayerAdvancements plAdv = ((EntityPlayerMP)player).getAdvancements();
			int opt = stats.optionals.get(Enums.Skills.EXPEDITION);

			if (opt > 3 && legit) {
				plAdv.revokeCriterion(adv, "legitimate");
			}
			else if (!legit) {
				Advancement rootAdv = ModUtil.getAdvancement("overworld/root");

				if (!plAdv.getProgress(rootAdv).isDone()) {
					plAdv.grantCriterion(rootAdv, "playerjoin");
					plAdv.grantCriterion(adv, "legitimate");
				}
				else {
					stats.optionals.put(Enums.Skills.EXPEDITION, (opt % 4) + 4);
				}
			}
		}
	}

	private void applyLegitimacyPenalties() {
		stats.optionals.put(Enums.Skills.EXPEDITION, (stats.optionals.get(Enums.Skills.EXPEDITION) % 4) + 4);
		AoALeaderboard.removePlayer(player);

		if (player instanceof EntityPlayerMP) {
			Advancement byTheBooksAdv = FMLCommonHandler.instance().getMinecraftServerInstance().getAdvancementManager().getAdvancement(new ResourceLocation("aoa3", "overworld/by_the_books"));

			if (byTheBooksAdv != null)
				((EntityPlayerMP)player).getAdvancements().revokeCriterion(byTheBooksAdv, "legitimate");

			PacketUtil.network.sendTo(new PacketSkillData(Enums.Skills.EXPEDITION.id, stats.levels.get(Enums.Skills.EXPEDITION), stats.xp.get(Enums.Skills.EXPEDITION), stats.optionals.get(Enums.Skills.EXPEDITION)), (EntityPlayerMP)player);
		}
	}

	public void enableRevenge(final EntityLivingBase target) {
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

	public EntityLivingBase getRevengeTarget() {
		return revengeTarget;
	}

	void cloneFromExistingPlayerData(PlayerDataManager sourcePlayerData) {
		interventionData = sourcePlayerData.interventionData;

		for (Enums.Skills skill : Enums.Skills.values()) {
			stats.levels.put(skill, sourcePlayerData.stats.levels.get(skill));
			stats.xp.put(skill, sourcePlayerData.stats.getExp(skill));
		}

		for (Map.Entry<Enums.Skills, Integer> skillDataEntry : sourcePlayerData.stats.optionals.entrySet()) {
			stats.optionals.put(skillDataEntry.getKey(), skillDataEntry.getValue());
		}

		for (Enums.Deities deity : Enums.Deities.values()) {
			stats.tribute.put(deity, sourcePlayerData.stats.tribute.get(deity));
		}

		equipment.cooldowns = sourcePlayerData.equipment.cooldowns;
		portalCoordinatesMap = sourcePlayerData.portalCoordinatesMap;

		if (ConfigurationUtil.MainConfig.skillsEnabled) {
			EntityUtil.applyAttributeModifierSafely(player, SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.innervationHealthBuff(InnervationUtil.getHealthBuff(stats.getLevel(Enums.Skills.INNERVATION))));
			player.setHealth(player.getMaxHealth());
		}
	}

	public NBTTagCompound saveToNBT() {
		NBTTagCompound baseTag = new NBTTagCompound();

		stats.saveToNBT(baseTag);

		if (!portalCoordinatesMap.isEmpty()) {
			NBTTagCompound portalCoordinatesNBT = new NBTTagCompound();

			for (Map.Entry<Integer, PortalCoordinatesContainer> entry : portalCoordinatesMap.entrySet()) {
				NBTTagCompound portalReturnTag = new NBTTagCompound();
				PortalCoordinatesContainer container = entry.getValue();

				portalReturnTag.setInteger("FromDim", container.fromDim);
				portalReturnTag.setDouble("PosX", container.x);
				portalReturnTag.setDouble("PosY", container.y);
				portalReturnTag.setDouble("PosZ", container.z);

				portalCoordinatesNBT.setTag(entry.getKey().toString(), portalReturnTag);
			}

			baseTag.setTag("PortalMap", portalCoordinatesNBT);
		}

		return baseTag;
	}

	public void loadFromNBT(NBTTagCompound baseTag) {
		stats.loadFromNBT(baseTag);

		if (baseTag.hasKey("PortalMap")) {
			NBTTagCompound portalMapTag = baseTag.getCompoundTag("PortalMap");

			for (String s : portalMapTag.getKeySet()) {
				NBTTagCompound portalReturnTag = portalMapTag.getCompoundTag(s);
				int fromDim = portalReturnTag.getInteger("FromDim");
				double x = portalReturnTag.getDouble("PosX");
				double y = portalReturnTag.getDouble("PosY");
				double z = portalReturnTag.getDouble("PosZ");

				try {
					portalCoordinatesMap.put(Integer.valueOf(s), new PortalCoordinatesContainer(fromDim, x, y, z));
				}
				catch (NumberFormatException e) {
					AdventOfAscension.logMessage(Level.WARN, "Found invalid portal map data, has someone been tampering with files? Data: " + s);
				}
			}
		}

		checkAndUpdateLegitimacy();
	}

	public final class PlayerEquipment {
		private final PlayerDataManager playerDataManager;

		private HashMap<Enums.Counters, Integer> cooldowns = new HashMap<Enums.Counters, Integer>(1);
		private HashMap<Enums.ArmourSets, ArmourEffectWrapper> armourMap = new HashMap<Enums.ArmourSets, ArmourEffectWrapper>(4);

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

		public boolean isCooledDown(Enums.Counters counter) {
			return !cooldowns.containsKey(counter);
		}

		public int getCooldown(Enums.Counters counter) {
			return cooldowns.getOrDefault(counter, 0);
		}

		public void setCooldown(Enums.Counters counter, int cooldownTicks) {
			cooldowns.put(counter, cooldownTicks);
		}

		public Enums.ArmourSets getCurrentFullArmourSet() {
			return currentFullSet != null ? currentFullSet.setType() : Enums.ArmourSets.NONE;
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

			armourChanged = checkAndHandleArmourSwap(boots, player.inventory.armorInventory.get(0).getItem(), EntityEquipmentSlot.FEET);
			armourChanged |= checkAndHandleArmourSwap(legs, player.inventory.armorInventory.get(1).getItem(), EntityEquipmentSlot.LEGS);
			armourChanged |= checkAndHandleArmourSwap(body, player.inventory.armorInventory.get(2).getItem(), EntityEquipmentSlot.CHEST);
			armourChanged |= checkAndHandleArmourSwap(helmet, player.inventory.armorInventory.get(3).getItem(), EntityEquipmentSlot.HEAD);

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

		private boolean checkAndHandleArmourSwap(@Nullable AdventArmour currentPiece, @Nonnull Item newPiece, @Nonnull EntityEquipmentSlot slot) {
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
			if (player.isDead || player.getHealth() <= 0)
				return;

			boolean doArmourTick = true;

			if (!player.capabilities.isCreativeMode) {
				for (EnumHand hand : EnumHand.values()) {
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
					unequipAdventArmour(playerDataManager, boots, EntityEquipmentSlot.FEET);
				}

				if (legs != null && !ItemUtil.hasLevelForItem(legs, playerDataManager)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armorInventory.get(1));
					player.inventory.armorInventory.set(1, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, legs, EntityEquipmentSlot.LEGS);
				}

				if (body != null && !ItemUtil.hasLevelForItem(body, playerDataManager)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armorInventory.get(2));
					player.inventory.armorInventory.set(2, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, body, EntityEquipmentSlot.CHEST);
				}

				if (helmet != null && !ItemUtil.hasLevelForItem(helmet, playerDataManager)) {
					doArmourTick = false;

					ItemHandlerHelper.giveItemToPlayer(player, player.inventory.armorInventory.get(3));
					player.inventory.armorInventory.set(3, ItemStack.EMPTY);
					unequipAdventArmour(playerDataManager, helmet, EntityEquipmentSlot.HEAD);
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
			player.inventoryContainer.detectAndSendChanges();
		}

		private void equipAdventArmour(PlayerDataManager plData, AdventArmour item, @Nullable EntityEquipmentSlot slot) {
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

		private void unequipAdventArmour(PlayerDataManager plData, AdventArmour item, @Nullable EntityEquipmentSlot slot) {
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
			Iterator<Map.Entry<Enums.Counters, Integer>> cooldownsIterator = cooldowns.entrySet().iterator();

			while (cooldownsIterator.hasNext()) {
				Map.Entry<Enums.Counters, Integer> entry = cooldownsIterator.next();

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
			private final HashSet<EntityEquipmentSlot> currentSlots = new HashSet<EntityEquipmentSlot>(4);

			private ArmourEffectWrapper(AdventArmour armour, EntityEquipmentSlot firstSlotEquipped) {
				this.armour = armour;

				currentSlots.add(firstSlotEquipped);
			}
		}
	}

	public final class PlayerStats {
		private final PlayerDataManager playerDataManager;

		private HashMap<Enums.Skills, Float> xp = new HashMap<Enums.Skills, Float>(Enums.Skills.values().length);
		private HashMap<Enums.Skills, Integer> levels = new HashMap<Enums.Skills, Integer>(Enums.Skills.values().length);
		private HashMap<Enums.Skills, Integer> optionals = new HashMap<Enums.Skills, Integer>(1);
		private HashMap<Enums.Deities, Integer> tribute = new HashMap<Enums.Deities, Integer>(Enums.Deities.values().length);
		private HashMap<Enums.Resources, Float> resources = new HashMap<Enums.Resources, Float>(Enums.Resources.values().length);

		private PlayerStats(PlayerDataManager dataManager) {
			this.playerDataManager = dataManager;

			for (Enums.Skills skill : Enums.Skills.values()) {
				xp.put(skill, 0f);
				levels.put(skill, 1);
			}

			for (Enums.Deities deity : Enums.Deities.values()) {
				tribute.put(deity, 0);
			}

			for (Enums.Resources resource : Enums.Resources.values()) {
				resources.put(resource, 0f);
			}

			optionals.put(Enums.Skills.EXPEDITION, 1);
		}

		private void regenResources() {
			if (resources.get(Enums.Resources.ENERGY) < 200 && (nextEnergyRegenTime < GlobalEvents.tick || GlobalEvents.tick + 60 < nextEnergyRegenTime))
				regenResource(Enums.Resources.ENERGY, 0.32f);

			if (resources.get(Enums.Resources.CREATION) < 200)
				regenResource(Enums.Resources.CREATION, 0.033f);

			if (resources.get(Enums.Resources.RAGE) < 200 && (nextRageRegenTime < GlobalEvents.tick || GlobalEvents.tick + 120 < nextRageRegenTime))
				regenResource(Enums.Resources.RAGE, ButcheryUtil.getTickRegen(levels.get(Enums.Skills.BUTCHERY)));

			if (resources.get(Enums.Resources.SOUL) < 200)
				regenResource(Enums.Resources.SOUL, 0.01f);
		}

		private void doTributeBuffs() {
			if (player.world.provider.getDimension() == 0 && !player.world.isDaytime()) {
				if (tribute.get(Enums.Deities.LUXON) == 200)
					player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 5, 0, true, false));

				if (tribute.get(Enums.Deities.EREBON) == 200)
					player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, -1, 0, true, false));

				if (tribute.get(Enums.Deities.PLUTON) == 200)
					player.addPotionEffect(new PotionEffect(MobEffects.LUCK, -1, 1, true, false));

				if (tribute.get(Enums.Deities.SELYAN) == 200)
					player.addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 0, true, false));
			}
		}

		public int getLevel(Enums.Skills skill) {
			return ConfigurationUtil.MainConfig.skillsEnabled ? Math.min(100, levels.get(skill)) : 100;
		}

		public int getLevelForDisplay(Enums.Skills skill) {
			return levels.get(skill);
		}

		public float getExp(Enums.Skills skill) {
			return xp.get(skill);
		}

		public float getResourceValue(Enums.Resources resource) {
			return ConfigurationUtil.MainConfig.resourcesEnabled ? resources.get(resource) : 200;
		}

		public int getTribute(Enums.Deities deity) {
			return ConfigurationUtil.MainConfig.resourcesEnabled ? tribute.get(deity) : 200;
		}

		@Nullable
		public Integer getSkillData(Enums.Skills skill) {
			return optionals.get(skill);
		}

		public void addXp(Enums.Skills skill, float xp, boolean isUnnatural, boolean ignoreXpBuffs) {
			if (!ConfigurationUtil.MainConfig.skillsEnabled)
				return;

			int lvl = levels.get(skill);

			if (lvl >= 1000)
				return;

			if (!isUnnatural)
				xp = buffs.applyXpBuffs(skill, xp);

			float remaining = Math.min(544132359, xp);
			int newLevels = 0;
			boolean noXpDrop = !ConfigurationUtil.MainConfig.showVanityLevels && lvl >= 100;
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

			if (newLevels > 0) {
				levelUp(skill, lvl, lvl + newLevels, !isUnnatural);

				if (!isUnnatural && optionals.get(Enums.Skills.EXPEDITION) < 4)
					AoALeaderboard.updatePlayerStat(player, skill, levels.get(skill));
			}

			if (isUnnatural) {
				applyLegitimacyPenalties();
			}
			else if (player instanceof EntityPlayerMP) {
				AdvancementTriggerRegister.xpGainTrigger.trigger((EntityPlayerMP)player, skill, (int)xp);
			}

			this.xp.put(skill, this.xp.get(skill) + remaining);

			if (player instanceof EntityPlayerMP) {
				checkAndUpdateLegitimacy();
				PacketUtil.network.sendTo(new PacketSkillData(skill.id, levels.get(skill), this.xp.get(skill), optionals.get(skill)), (EntityPlayerMP)player);

				if (!noXpDrop)
					PacketUtil.network.sendTo(new PacketXpGain(skill.id, xp, newLevels > 0), (EntityPlayerMP)player);
			}
		}

		public void subtractXp(Enums.Skills skill, float xp) {
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

			if (player instanceof EntityPlayerMP)
				PacketUtil.network.sendTo(new PacketSkillData(skill.id, levels.get(skill), this.xp.get(skill), optionals.get(skill)), (EntityPlayerMP)player);
		}

		public void levelUp(Enums.Skills skill, int oldLevel, int newLevel, boolean isNaturalLevel) {
			if (newLevel < 100) {
				player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.PLAYER_LEVEL_UP, SoundCategory.PLAYERS, 1.0f, 1.0f);
			}
			else if (newLevel == 100 || newLevel == 1000) {
				player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.PLAYER_LEVEL_100, SoundCategory.PLAYERS, 1.0f, 1.0f);
			}

			levels.put(skill, newLevel);
			xp.put(skill, 0.0f);

			AoAEvents.playerLevelChange(this.playerDataManager, oldLevel, newLevel, skill, isNaturalLevel);

			if (player instanceof EntityPlayerMP) {
				PacketUtil.network.sendTo(new PacketSkillData(skill.id, newLevel, xp.get(skill), optionals.get(skill)), (EntityPlayerMP)player);
				AdvancementTriggerRegister.levelUpTrigger.trigger((EntityPlayerMP)player, skill, newLevel);
			}
		}

		public void setLevel(Enums.Skills skill, int newLevel) {
			int oldLevel = levels.get(skill);

			levels.put(skill, MathHelper.clamp(newLevel, 1, 1000));
			xp.put(skill, 0f);

			AoAEvents.playerLevelChange(this.playerDataManager, oldLevel, newLevel, skill, false);
			applyLegitimacyPenalties();

			if (player instanceof EntityPlayerMP)
				PacketUtil.network.sendTo(new PacketSkillData(skill.id, newLevel, 0, optionals.get(skill)), (EntityPlayerMP)player);
		}

		public boolean consumeResource(Enums.Resources resource, float value, boolean force) {
			if (!ConfigurationUtil.MainConfig.resourcesEnabled)
				return true;

			if (resource != Enums.Resources.RAGE && player.capabilities.isCreativeMode)
				return true;

			float current = resources.get(resource);
			value = buffs.applyResourceFortitudeBuffs(resource, value);

			if (current < value && !force) {
				if (player instanceof EntityPlayerMP)
					PlayerUtil.notifyPlayerOfInsufficientResources((EntityPlayerMP)player, resource, value);

				return false;
			}

			resources.put(resource, Math.max(current - value, 0));

			if (resource == Enums.Resources.ENERGY)
				nextEnergyRegenTime = GlobalEvents.tick + 50;

			if (resource == Enums.Resources.RAGE)
				nextRageRegenTime = GlobalEvents.tick + 100;

			resourcesUpdated = true;

			return !force || current > value;
		}

		public void regenResource(Enums.Resources resource, float amount) {
			float current = resources.get(resource);
			float max = 200;
			amount = buffs.applyResourceRegenBuffs(resource, amount);

			resources.put(resource, Math.min(current + amount, max));

			if (current != max)
				resourcesUpdated = true;
		}

		public void addTribute(Enums.Deities deity, int amount) {
			if (ItemUtil.isHoldingItem(player, WeaponRegister.HOLY_SWORD))
				amount *= 2;

			tribute.put(deity, MathHelper.clamp(tribute.get(deity) + amount, 0, 200));

			if (player instanceof EntityPlayerMP)
				PacketUtil.network.sendTo(new PacketTributeData(tribute.get(Enums.Deities.EREBON), tribute.get(Enums.Deities.LUXON), tribute.get(Enums.Deities.PLUTON), tribute.get(Enums.Deities.SELYAN)), (EntityPlayerMP)player);
		}

		public void resetAllTribute() {
			for (Enums.Deities deity : Enums.Deities.values()) {
				tribute.put(deity, 0);
			}

			if (player instanceof EntityPlayerMP)
				PacketUtil.network.sendTo(new PacketTributeData(tribute.get(Enums.Deities.EREBON), tribute.get(Enums.Deities.LUXON), tribute.get(Enums.Deities.PLUTON), tribute.get(Enums.Deities.SELYAN)), (EntityPlayerMP)player);
		}

		public void incrementExpeditionBoost() {
			int optExpedition = optionals.get(Enums.Skills.EXPEDITION);

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

			optionals.put(Enums.Skills.EXPEDITION, optExpedition);

			if (player instanceof EntityPlayerMP)
				PacketUtil.network.sendTo(new PacketSkillData(Enums.Skills.EXPEDITION.id, levels.get(Enums.Skills.EXPEDITION), xp.get(Enums.Skills.EXPEDITION), optExpedition), (EntityPlayerMP)player);
		}

		private void saveToNBT(NBTTagCompound baseTag) {
			for (Enums.Skills skill : Enums.Skills.values()) {
				NBTTagCompound skillNBT = new NBTTagCompound();

				skillNBT.setInteger("Level", Math.max(1, levels.get(skill)));
				skillNBT.setFloat("Exp", Math.max(0, xp.get(skill)));

				if (optionals.containsKey(skill))
					skillNBT.setInteger("Opt", optionals.get(skill));

				baseTag.setTag(skill.toString(), skillNBT);
			}

			for (Enums.Deities deity : Enums.Deities.values()) {
				baseTag.setInteger(deity.toString(), tribute.get(deity));
			}

			if (interventionData != null) {
				NBTTagCompound interventionTag = new NBTTagCompound();
				int i = 0;

				for (ItemStack stack : interventionData) {
					interventionTag.setTag(String.valueOf(i), stack.serializeNBT());
					i++;
				}

				baseTag.setTag("InterventionData", interventionTag);
			}
		}

		private void loadFromNBT(NBTTagCompound baseTag) {
			for (Enums.Skills skill : Enums.Skills.values()) {
				NBTTagCompound skillTag = baseTag.getCompoundTag(skill.toString());

				levels.put(skill, MathHelper.clamp(skillTag.getInteger("Level"), 1, 1000));
				xp.put(skill, Math.max(0, skillTag.getFloat("Exp")));

				if (skillTag.hasKey("Opt"))
					optionals.put(skill, skillTag.getInteger("Opt"));
			}

			for (Enums.Deities deity : Enums.Deities.values()) {
				tribute.put(deity, MathHelper.clamp(baseTag.getInteger(deity.toString()), 0, 200));
			}

			if (baseTag.hasKey("InterventionData")) {
				interventionData = new HashSet<ItemStack>();
				NBTTagCompound interventionTag = baseTag.getCompoundTag("InterventionData");
				int i = 0;

				while (interventionTag.hasKey(String.valueOf(i))) {
					interventionData.add(new ItemStack(interventionTag.getCompoundTag(String.valueOf(i))));

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
		private HashMap<Enums.Skills, Float> xpMods = null;

		private HashMap<Enums.Resources, Float> resourceRegenBuffs = null;
		private HashMap<Enums.Resources, Float> resourceFortitudeBuffs = null;

		private void applyDefensiveBuffs(LivingHurtEvent ev) {
			if (defensiveBuffs != null && defensiveBuffs.containsKey(ev.getSource().getDamageType()))
				ev.setAmount(ev.getAmount() * (1 - defensiveBuffs.get(ev.getSource().getDamageType())));
		}

		private void applyOffensiveBuffs(LivingHurtEvent ev) {
			if (offensiveBuffs != null && offensiveBuffs.containsKey(ev.getSource().getDamageType()))
				ev.setAmount(ev.getAmount() * (1 + offensiveBuffs.get(ev.getSource().getDamageType())));
		}

		private float applyXpBuffs(Enums.Skills skill, float baseXp) {
			baseXp *= ConfigurationUtil.MainConfig.globalXpModifier;
			baseXp *= globalXpMod;

			if (xpMods != null && xpMods.containsKey(skill))
				baseXp *= (1 + xpMods.get(skill));

			return baseXp;
		}

		private float applyResourceRegenBuffs(Enums.Resources resource, float amountToRegen) {
			if (resourceRegenBuffs != null && resourceRegenBuffs.containsKey(resource))
				amountToRegen *= (1 + resourceRegenBuffs.get(resource));

			return amountToRegen;
		}

		private float applyResourceFortitudeBuffs(Enums.Resources resource, float amountToConsume) {
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

		public void addXpModifier(Enums.Skills skill, float modifier) {
			if (xpMods == null)
				xpMods = new HashMap<Enums.Skills, Float>(2);

			xpMods.merge(skill, modifier, Float::sum);
		}

		public void removeXpModifier(Enums.Skills skill, float modifier) {
			if (xpMods != null) {
				xpMods.merge(skill, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (xpMods.get(skill) == 0)
					xpMods.remove(skill);
			}
		}

		public void addResourceRegenModifier(Enums.Resources resource, float modifier) {
			if (resourceRegenBuffs == null)
				resourceRegenBuffs = new HashMap<Enums.Resources, Float>(2);

			resourceRegenBuffs.merge(resource, modifier, Float::sum);
		}

		public void removeResourceRegenModifier(Enums.Resources resource, float modifier) {
			if (resourceRegenBuffs != null) {
				resourceRegenBuffs.merge(resource, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (resourceRegenBuffs.get(resource) == 0)
					resourceRegenBuffs.remove(resource);
			}
		}

		public void addResourceFortitudeModifier(Enums.Resources resource, float modifier) {
			if (resourceFortitudeBuffs == null)
				resourceFortitudeBuffs = new HashMap<Enums.Resources, Float>(2);

			resourceFortitudeBuffs.merge(resource, modifier, Float::sum);
		}

		public void removeResourceFortitudeModifier(Enums.Resources resource, float modifier) {
			if (resourceFortitudeBuffs != null) {
				resourceFortitudeBuffs.merge(resource, modifier, (key, value) -> value -= modifier == 0 ? null : value);

				if (resourceFortitudeBuffs.get(resource) == 0)
					resourceFortitudeBuffs.remove(resource);
			}
		}
	}
}
