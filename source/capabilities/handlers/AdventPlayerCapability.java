package net.tslat.aoa3.capabilities.handlers;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBasePlayer;
import net.tslat.aoa3.common.packet.*;
import net.tslat.aoa3.common.registration.AdvancementTriggerRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.Enums.Counters;
import net.tslat.aoa3.library.Enums.Deities;
import net.tslat.aoa3.library.Enums.Resources;
import net.tslat.aoa3.library.Enums.Skills;
import net.tslat.aoa3.library.PortalCoordinatesContainer;
import net.tslat.aoa3.library.leaderboard.AoALeaderboard;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.skills.AuguryUtil;
import net.tslat.aoa3.utils.skills.ButcheryUtil;

import java.util.*;

public class AdventPlayerCapability implements CapabilityBasePlayer {
	private EntityPlayer player;

	private HashMap<Skills, Float> skillsXp = new HashMap<Skills, Float>();
	private HashMap<Skills, Integer> skillsLvl = new HashMap<Skills, Integer>();
	private HashMap<Deities, Integer> tribute = new HashMap<Deities, Integer>();
	private HashMap<Resources, Float> resources = new HashMap<Resources, Float>();
	private HashMap<Counters, Integer> counters = new HashMap<Counters, Integer>();

	public HashSet<ItemStack> interventionStacks = new HashSet<ItemStack>();

	private int nextEnergyRegenTime = 0;
	private int nextRageRegenTime = 0;

	public int expeditionBoost = 1;

	private int revengeCounter = 0;
	private EntityLivingBase revengeTarget = null;

	private HashMap<Integer, PortalCoordinatesContainer> portalCoordinates = new HashMap<Integer, PortalCoordinatesContainer>();

	private AdventArmour fullSet;
	private Item helmet;
	private Item body;
	private Item legs;
	private Item boots;

	private int nextMessageTime = 0;
	private ITextComponent lastMessage = new TextComponentString("init");

	public AdventPlayerCapability() {
		for (Skills sk : Skills.values()) {
			skillsXp.put(sk, 0.0f);
			skillsLvl.put(sk, 1);
		}

		for (Deities de : Deities.values()) {
			tribute.put(de, 0);
		}

		for (Resources re : Resources.values()) {
			resources.put(re, 0.0f);
		}
	}

	@Override
	public void addPlayer(EntityPlayer pl) {
		player = pl;
	}

	@Override
	public NBTTagCompound saveNBTData() {
		NBTTagCompound baseTag = new NBTTagCompound();

		if (skillsLvl == null)
			return baseTag;

		for (Enums.Skills sk : Enums.Skills.values()) {
			NBTTagCompound skillTag = new NBTTagCompound();
			int lvl = getDisplayLevel(sk);

			if (lvl <= 0)
				continue;

			skillTag.setInteger("Level", lvl);
			skillTag.setFloat("Exp", getExp(sk));

			if (sk == Enums.Skills.EXPEDITION)
				skillTag.setInteger("Opt", expeditionBoost);

			baseTag.setTag(sk.toString(), skillTag);
		}

		for (Enums.Deities deity : Enums.Deities.values()) {
			baseTag.setInteger(deity.toString(), getTribute(deity));
		}

		NBTTagCompound portalMapTag = new NBTTagCompound();

		for (Map.Entry<Integer, PortalCoordinatesContainer> entry : portalCoordinates.entrySet()) {
			NBTTagCompound portalReturnTag = new NBTTagCompound();
			PortalCoordinatesContainer container = entry.getValue();

			portalReturnTag.setInteger("FromDim", container.fromDim);
			portalReturnTag.setDouble("PosX", container.x);
			portalReturnTag.setDouble("PosY", container.y);
			portalReturnTag.setDouble("PosZ", container.z);

			portalMapTag.setTag(entry.getKey().toString(), portalReturnTag);
			baseTag.setTag("PortalMap", portalMapTag);
		}

		return baseTag;
	}

	@Override
	public void loadNBTData(NBTTagCompound baseTag) {
		for (Enums.Skills sk : Enums.Skills.values()) {
			NBTTagCompound skillTag = baseTag.getCompoundTag(sk.toString());

			skillsLvl.put(sk, MathHelper.clamp(skillTag.getInteger("Level"), 1, 1000));
			skillsXp.put(sk, Math.max(0, skillTag.getFloat("Exp")));

			if (sk == Skills.EXPEDITION)
				expeditionBoost = skillTag.getInteger("Opt");
		}

		for (Enums.Deities deity : Enums.Deities.values()) {
			tribute.put(deity, MathHelper.clamp(baseTag.getInteger(deity.toString()), 0, 200));
		}

		if (baseTag.hasKey("PortalMap")) {
			NBTTagCompound portalMapTag = baseTag.getCompoundTag("PortalMap");

			for (String s : portalMapTag.getKeySet()) {
				NBTTagCompound portalReturnTag = portalMapTag.getCompoundTag(s);
				int fromDim = portalReturnTag.getInteger("FromDim");
				double x = portalReturnTag.getDouble("PosX");
				double y = portalReturnTag.getDouble("PosY");
				double z = portalReturnTag.getDouble("PosZ");

				portalCoordinates.put(Integer.valueOf(s), new PortalCoordinatesContainer(fromDim, x, y, z));
			}
		}

		checkAndUpdateLegitimacy();
	}

	@Override
	public void sendPlayerMessage(ITextComponent message) {
		if (message.equals(lastMessage) && (nextMessageTime > GlobalEvents.tick || GlobalEvents.tick + 300 < nextMessageTime))
			return;

		nextMessageTime = GlobalEvents.tick + 200;
		lastMessage = message;
		player.sendMessage(message);
	}

	@Override
	public void tickPlayer() {
		if (player == null || player.isSpectator() || player.world.isRemote)
			return;

		ItemStack stackBoots = player.inventory.armorInventory.get(0);
		ItemStack stackLegs = player.inventory.armorInventory.get(1);
		ItemStack stackBody = player.inventory.armorInventory.get(2);
		ItemStack stackHelmet = player.inventory.armorInventory.get(3);
		AdventArmour oldSet = fullSet;

		boolean armourChanged = false;

		if (!Objects.equals(stackBoots.getItem(), this.boots)) {
			armourChanged = true;
			this.boots = stackBoots.getItem();
		}

		if (!player.capabilities.isCreativeMode) {
			ItemStack mainHandItem = player.getHeldItem(EnumHand.MAIN_HAND);
			ItemStack offHandItem = player.getHeldItem(EnumHand.OFF_HAND);

			if (mainHandItem.getItem() instanceof SkillItem) {
				SkillItem item = (SkillItem)mainHandItem.getItem();

				if (skillsLvl.get(item.getSkill()) < item.getLevelReq()) {
					ItemHandlerHelper.giveItemToPlayer(player, mainHandItem);
					player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
					player.inventoryContainer.detectAndSendChanges();
				}
			}

			if (offHandItem.getItem() instanceof SkillItem) {
				SkillItem item = (SkillItem)offHandItem.getItem();

				if (skillsLvl.get(item.getSkill()) < item.getLevelReq()) {
					ItemHandlerHelper.giveItemToPlayer(player, offHandItem);
					player.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
					player.inventoryContainer.detectAndSendChanges();
				}
			}
		}

		if (!player.capabilities.isCreativeMode && stackBoots.getItem() instanceof SkillItem) {
			SkillItem boot = (SkillItem)stackBoots.getItem();

			if (skillsLvl.get(boot.getSkill()) < boot.getLevelReq()) {
				this.boots = null;
				armourChanged = true;
				ItemHandlerHelper.giveItemToPlayer(player, stackBoots);
				player.inventory.armorInventory.set(0, ItemStack.EMPTY);
			}
		}

		if (!Objects.equals(stackLegs.getItem(), this.legs)) {
			armourChanged = true;
			this.legs = stackLegs.getItem();
		}

		if (!player.capabilities.isCreativeMode && stackLegs.getItem() instanceof SkillItem) {
			SkillItem leg = (SkillItem)stackLegs.getItem();

			if (skillsLvl.get(leg.getSkill()) < leg.getLevelReq()) {
				this.legs = null;
				armourChanged = true;
				ItemHandlerHelper.giveItemToPlayer(player, stackLegs);
				player.inventory.armorInventory.set(1, ItemStack.EMPTY);
			}
		}

		if (!Objects.equals(stackBody.getItem(), this.body)) {
			armourChanged = true;
			this.body = stackBody.getItem();
		}

		if (!player.capabilities.isCreativeMode && stackBody.getItem() instanceof SkillItem) {
			SkillItem bod = (SkillItem)stackBody.getItem();

			if (skillsLvl.get(bod.getSkill()) < bod.getLevelReq()) {
				this.body = null;
				armourChanged = true;
				ItemHandlerHelper.giveItemToPlayer(player, stackBody);
				player.inventory.armorInventory.set(2, ItemStack.EMPTY);
			}
		}

		if (!Objects.equals(stackHelmet.getItem(), this.helmet)) {
			armourChanged = true;
			this.helmet = stackHelmet.getItem();
		}

		if (!player.capabilities.isCreativeMode && stackHelmet.getItem() instanceof SkillItem) {
			SkillItem helm = (SkillItem)stackHelmet.getItem();

			if (skillsLvl.get(helm.getSkill()) < helm.getLevelReq()) {
				this.helmet = null;
				armourChanged = true;
				ItemHandlerHelper.giveItemToPlayer(player, stackHelmet);
				player.inventory.armorInventory.set(3, ItemStack.EMPTY);
			}
		}

		if (armourChanged) {
			if (boots instanceof AdventArmour && legs instanceof AdventArmour && body instanceof AdventArmour && helmet instanceof AdventArmour) {
				if (((AdventArmour)boots).setType() == ((AdventArmour)legs).setType() && ((AdventArmour)legs).setType() == ((AdventArmour)body).setType() && ((AdventArmour)body).isSetHelmet((AdventArmour)helmet)) {
					fullSet = (AdventArmour)boots;

					if (fullSet != oldSet) {
						if (oldSet != null)
							oldSet.setUnequipEffect(this);

						fullSet.setEquipEffect(this);
					}
				}
				else {
					fullSet = null;

					if (oldSet != null)
						oldSet.setUnequipEffect(this);
				}
			}
			else {
				fullSet = null;

				if (oldSet != null)
					oldSet.setUnequipEffect(this);
			}

			player.inventoryContainer.detectAndSendChanges();
		}

		if (fullSet != null && player.getHealth() > 0)
			fullSet.setTickEffect(this);

		if (this.helmet instanceof AdventArmour && ((AdventArmour)this.helmet).setType() == Enums.ArmourSets.ALL)
			((AdventArmour)this.helmet).setTickEffect(this);

		if (body == Items.ELYTRA && player.isElytraFlying())
			stackBody.damageItem(1, player);

		boolean resourcesChanged = false;

		if (resources.get(Resources.ENERGY) < 200 && (nextEnergyRegenTime < GlobalEvents.tick || GlobalEvents.tick + 60 < nextEnergyRegenTime)) {
			resourceRegen(Resources.ENERGY, 0.32f);
			resourcesChanged = true;
		}

		float maxCreation = AuguryUtil.getMaxCreation(skillsLvl.get(Skills.AUGURY));

		if (resources.get(Resources.CREATION) < maxCreation) {
			resourceRegen(Resources.CREATION, 0.033f);
			resourcesChanged = true;
		}

		if (resources.get(Resources.RAGE) < 200 && (nextRageRegenTime < GlobalEvents.tick || GlobalEvents.tick + 120 < nextRageRegenTime)) {
			resourceRegen(Resources.RAGE, ButcheryUtil.getTickRegen(skillsLvl.get(Skills.BUTCHERY)));
			resourcesChanged = true;
		}

		float maxSoul = AuguryUtil.getMaxSoul(skillsLvl.get(Skills.AUGURY));

		if (resources.get(Resources.SOUL) < maxSoul) {
			resourceRegen(Resources.SOUL, 0.01f);
			resourcesChanged = true;
		}

		if (resourcesChanged && player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketResourceData(resources.get(Resources.CREATION), resources.get(Resources.ENERGY), resources.get(Resources.RAGE), resources.get(Resources.SOUL)), (EntityPlayerMP)player);

		Iterator<Map.Entry<Counters, Integer>> it = counters.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Counters, Integer> entry = it.next();

			if (entry.getValue() <= 1) {
				it.remove();
			}
			else {
				entry.setValue(entry.getValue() - 1);
			}
		}

		if (revengeCounter > 0) {
			--revengeCounter;

			if (revengeCounter <= 0)
				disableRevenge();
		}

		if (!player.world.isDaytime()) {
			if (getTribute(Deities.LUXON) == 200)
				player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 5, 0, true, false));

			if (getTribute(Deities.EREBON) == 200)
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, -1, 0, true, false));

			if (getTribute(Deities.PLUTON) == 200)
				player.addPotionEffect(new PotionEffect(MobEffects.LUCK, -1, 1, true, false));

			if (getTribute(Deities.SELYAN) == 200)
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 0, true, false));
		}
	}

	@Override
	public void resourceRegen(Resources resource, float amount) {
		float current = resources.get(resource);
		float max = 0;

		switch (resource) {
			case CREATION:
				max = AuguryUtil.getMaxCreation(skillsLvl.get(Skills.AUGURY));
				break;
			case SOUL:
				max = AuguryUtil.getMaxSoul(skillsLvl.get(Skills.AUGURY));
				break;
			case RAGE:
				max = 200;
				break;
			case ENERGY:
				max = 200;
				break;
			default:
				break;
		}

		resources.put(resource, Math.min(current + amount, max));

		if (resources.get(resource) < max && player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketResourceData(resources.get(Resources.CREATION), resources.get(Resources.ENERGY), resources.get(Resources.RAGE), resources.get(Resources.SOUL)), (EntityPlayerMP)player);
	}

	@Override
	public Item getHelmet() {
		return helmet;
	}

	@Override
	public Item getChestplate() {
		return body;
	}

	@Override
	public Item getLeggings() {
		return legs;
	}

	@Override
	public Item getBoots() {
		return boots;
	}

	@Override
	public int getExpeditionBoost() {
		return expeditionBoost % 4;
	}

	@Override
	public float getResourceValue(Enums.Resources resource) {
		return resources.get(resource);
	}

	@Override
	public boolean consumeResource(Enums.Resources resource, float value, boolean force) {
		if (resource != Resources.RAGE && player.capabilities.isCreativeMode)
			return true;

		float current = resources.get(resource);

		if (current < value && !force)
			return false;

		resources.put(resource, Math.max(current - value, 0));

		if (resource == Resources.ENERGY)
			nextEnergyRegenTime = GlobalEvents.tick + 50;

		if (resource == Resources.RAGE)
			nextRageRegenTime = GlobalEvents.tick + 100;

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketResourceData(resources.get(Resources.CREATION), resources.get(Resources.ENERGY), resources.get(Resources.RAGE), resources.get(Resources.SOUL)), (EntityPlayerMP)player);

		return !force || current > value;
	}

	@Override
	public void incrementExpeditionBoost() {
		if (expeditionBoost > 3) {
			++expeditionBoost;

			if (expeditionBoost > 7)
				expeditionBoost = 4;
		}
		else {
			++expeditionBoost;

			if (expeditionBoost > 3)
				expeditionBoost = 0;
		}

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketSkillData(Skills.EXPEDITION.id, skillsLvl.get(Skills.EXPEDITION), skillsXp.get(Skills.EXPEDITION), expeditionBoost), (EntityPlayerMP)player);
	}

	@Override
	public int getLevel(Skills skill) {
		return Math.min(100, skillsLvl.get(skill));
	}

	@Override
	public int getDisplayLevel(Skills skill) {
		return skillsLvl.get(skill);
	}

	@Override
	public float getExp(Skills skill) {
		return skillsXp.get(skill);
	}

	@Override
	public float getXpReqForLevel(int lvl) {
		if (lvl <= 100) {
			return (float)(Math.pow(1.1, lvl - 1) * 50.0);
		}
		else {
			return (float)(Math.pow(lvl - 10, 2.5) / 100) + 500000;
		}
	}

	@Override
	public void addXp(Skills skill, float xp, boolean isUnnatural) {
		int lvl = skillsLvl.get(skill);
		float remaining = Math.min(544132359, xp);
		int levels = 0;
		boolean noXpDrop = !ConfigurationUtil.MainConfig.showVanityLevels && lvl >= 100;

		if (lvl >= 1000)
			return;

		if (remaining > getXpRemaining(skill)) {
			remaining -= getXpRemaining(skill);
			levels++;

			float stillRemaining;

			while ((stillRemaining = remaining - getXpReqForLevel(lvl + levels + 1)) >= 0 && lvl + levels < 1000) {
				remaining = stillRemaining;
				levels++;
			}
		}

		if (levels > 0) {
			levelUp(skill, lvl, lvl + levels);

			if (!isUnnatural && expeditionBoost < 4)
				AoALeaderboard.updatePlayerStat(player, skill, skillsLvl.get(skill));
		}

		if (isUnnatural) {
			applyLegitimacyPenalties();
		}
		else if (player instanceof EntityPlayerMP) {
			AdvancementTriggerRegister.xpGainTrigger.trigger((EntityPlayerMP)player, skill, (int)xp);
		}

		skillsXp.put(skill, skillsXp.get(skill) + remaining);

		if (player instanceof EntityPlayerMP) {
			checkAndUpdateLegitimacy();
			PacketUtil.network.sendTo(new PacketSkillData(skill.id, skillsLvl.get(skill), skillsXp.get(skill), expeditionBoost), (EntityPlayerMP)player);

			if (!noXpDrop)
				PacketUtil.network.sendTo(new PacketXpGain(skill.id, xp, levels > 0), (EntityPlayerMP)player);
		}
	}

	@Override
	public void subtractXp(Skills skill, float xp) {
		int lvl = skillsLvl.get(skill);
		float remaining = Math.min(544132359, xp);
		int levels = 0;

		if (lvl > 1 && remaining > skillsXp.get(skill)) {
			remaining -= skillsXp.get(skill);
			levels++;

			float stillRemaining;

			while ((stillRemaining = remaining - getXpReqForLevel(lvl - levels)) >= 0 && lvl - levels > 1) {
				remaining = stillRemaining;
				levels++;
			}
		}

		skillsLvl.put(skill, lvl - levels);
		skillsXp.put(skill, Math.max(0, getXpReqForLevel(lvl - levels + 1) - remaining));
		applyLegitimacyPenalties();

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketSkillData(skill.id, getDisplayLevel(skill), getLevelProgressPercentage(skill), expeditionBoost), (EntityPlayerMP)player);
	}

	@Override
	public int getLevelProgressPercentage(Skills skill) {
		return (int)Math.floor(skillsXp.get(skill) / getXpReqForLevel(skillsLvl.get(skill)) * 100.0f);
	}

	@Override
	public float getXpRemaining(Skills skill) {
		return getXpReqForLevel(skillsLvl.get(skill) + 1) - skillsXp.get(skill);
	}

	@Override
	public Skills getLowestSkillWithLimit(final int limit) {
		Skills lowestSkill = null;
		int lowestVal = 1001;

		for (Skills sk : Skills.values()) {
			int temp = skillsLvl.get(sk);

			if (temp >= limit && temp < lowestVal) {
				lowestVal = temp;
				lowestSkill = sk;
			}
		}

		if (lowestVal >= 1000) {
			return null;
		}
		else {
			return lowestSkill;
		}
	}

	@Override
	public void levelUp(Skills skill, int oldLevel, int newLevel) {
		if (newLevel < 100) {
			player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.levelUp, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
		else if (newLevel == 100) {
			player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.level100, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}

		skillsLvl.put(skill, newLevel);
		skillsXp.put(skill, 0.0f);

		if (player instanceof EntityPlayerMP) {
			PacketUtil.network.sendTo(new PacketSkillData(skill.id, newLevel, getLevelProgressPercentage(skill), expeditionBoost), (EntityPlayerMP)player);
			AdvancementTriggerRegister.levelUpTrigger.trigger((EntityPlayerMP)player, skill, newLevel);
		}
	}

	@Override
	public void setLevel(Skills skill, int newLevel) {
		skillsLvl.put(skill, MathHelper.clamp(newLevel, 1, 1000));
		skillsXp.put(skill, 0f);

		applyLegitimacyPenalties();

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketSkillData(skill.id, newLevel, 0, expeditionBoost), (EntityPlayerMP)player);
	}

	private void checkAndUpdateLegitimacy() {
		if (player instanceof EntityPlayerMP) {
			Advancement adv = ModUtil.getAdvancement("overworld/by_the_books");
			boolean legit = ((EntityPlayerMP)player).getAdvancements().getProgress(adv).isDone();
			PlayerAdvancements plAdv = ((EntityPlayerMP)player).getAdvancements();

			if (expeditionBoost > 3 && legit) {
				plAdv.revokeCriterion(adv, "legitimate");
			}
			else if (!legit) {
				Advancement rootAdv = ModUtil.getAdvancement("overworld/root");

				if (!plAdv.getProgress(rootAdv).isDone()) {
					plAdv.grantCriterion(rootAdv, "playerjoin");
					plAdv.grantCriterion(adv, "legitimate");
				}
				else {
					expeditionBoost = (expeditionBoost % 4) + 4;
				}
			}
		}
	}

	private void applyLegitimacyPenalties() {
		expeditionBoost = (expeditionBoost % 4) + 4;
		AoALeaderboard.removePlayer(player);

		if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP)player).getAdvancements().revokeCriterion(FMLCommonHandler.instance().getMinecraftServerInstance().getAdvancementManager().getAdvancement(new ResourceLocation("aoa3", "overworld/by_the_books")), "legitimate");
			PacketUtil.network.sendTo(new PacketSkillData(Skills.EXPEDITION.id, skillsLvl.get(Skills.EXPEDITION), skillsXp.get(Skills.EXPEDITION), expeditionBoost), (EntityPlayerMP)player);
		}
	}

	@Override
	public int getTribute(Deities deity) {
		return tribute.get(deity);
	}

	@Override
	public void resetAllTribute() {
		for (Deities deity : Deities.values()) {
			tribute.put(deity, 0);
		}

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketTributeData(tribute.get(Deities.EREBON), tribute.get(Deities.LUXON), tribute.get(Deities.PLUTON), tribute.get(Deities.SELYAN)), (EntityPlayerMP)player);
	}

	@Override
	public void addTribute(Enums.Deities deity, int amount) {
		tribute.put(deity, Math.min(tribute.get(deity) + amount, 200));

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketTributeData(tribute.get(Deities.EREBON), tribute.get(Deities.LUXON), tribute.get(Deities.PLUTON), tribute.get(Deities.SELYAN)), (EntityPlayerMP)player);
	}

	@Override
	public void enableRevenge(final EntityLivingBase target) {
		revengeCounter = 400;
		revengeTarget = target;

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketRevenge(true), (EntityPlayerMP)player);
	}

	@Override
	public boolean isRevengeActive() {
		return revengeCounter > 0 && revengeTarget != null;
	}

	@Override
	public void disableRevenge() {
		revengeCounter = 0;
		revengeTarget = null;

		if (player instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketRevenge(false), (EntityPlayerMP)player);
	}

	@Override
	public EntityLivingBase getRevengeTarget() {
		return revengeTarget;
	}

	@Override
	public void setPortalReturnLocation(Integer toDimId, PortalCoordinatesContainer coords) {
		portalCoordinates.put(toDimId, coords);
	}

	@Override
	public void removePortalReturnLocation(Integer toDimId) {
		portalCoordinates.remove(toDimId);
	}

	@Override
	public PortalCoordinatesContainer getPortalReturnLocation(Integer toDimId) {
		return portalCoordinates.get(toDimId);
	}

	@Override
	public EntityPlayer getPlayer() {
		return player;
	}

	@Override
	public AdventArmour getArmourSet() {
		return fullSet;
	}

	@Override
	public Enums.ArmourSets getArmourSetType() {
		return fullSet != null ? fullSet.setType() : Enums.ArmourSets.NONE;
	}

	@Override
	public boolean hasLevel(Enums.Skills skill, int level) {
		return skillsLvl.get(skill) >= level;
	}

	@Override
	public boolean isCooledDown(Enums.Counters counter) {
		return !counters.containsKey(counter);
	}

	@Override
	public void setCooldown(Enums.Counters counter, int cooldown) {
		counters.put(counter, cooldown);
	}
}
