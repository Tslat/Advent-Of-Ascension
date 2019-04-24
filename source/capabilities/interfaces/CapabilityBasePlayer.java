package net.tslat.aoa3.capabilities.interfaces;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.library.PortalCoordinatesContainer;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.library.Enums;

public interface CapabilityBasePlayer {
	void addPlayer(EntityPlayer pl);

	NBTTagCompound saveNBTData();

	void loadNBTData(NBTTagCompound nbtTag);

	void sendPlayerMessage(ITextComponent message);

	void tickPlayer();

	Item getHelmet();

	Item getChestplate();

	Item getLeggings();

	Item getBoots();

	float getResourceValue(Enums.Resources resource);

	boolean consumeResource(Enums.Resources resource, float amount, boolean force);

	void resourceRegen(Enums.Resources resource, float amount);

	int getExpeditionBoost();

	void incrementExpeditionBoost();

	int getLevel(Enums.Skills skill);

	int getDisplayLevel(Enums.Skills skill);

	float getExp(Enums.Skills skill);

	float getXpReqForLevel(int lvl);

	void addXp(Enums.Skills skill, float xp, boolean isUnnatural);

	void subtractXp(Enums.Skills skill, float xp);

	int getLevelProgressPercentage(Enums.Skills skill);

	float getXpRemaining(Enums.Skills skill);

	Enums.Skills getLowestSkillWithLimit(final int limit);

	void levelUp(Enums.Skills skill, int oldLevel, int newLevel);

	void setLevel(Enums.Skills skill, int newLevel);

	int getTribute(Enums.Deities deity);

	void resetAllTribute();

	void addTribute(Enums.Deities deity, int amount);

	void enableRevenge(EntityLivingBase target);

	boolean isRevengeActive();

	void disableRevenge();

	EntityLivingBase getRevengeTarget();

	void setPortalReturnLocation(Integer toDimId, PortalCoordinatesContainer coords);

	void removePortalReturnLocation(Integer toDimId);

	PortalCoordinatesContainer getPortalReturnLocation(Integer toDimId);

	EntityPlayer getPlayer();

	AdventArmour getArmourSet();

	Enums.ArmourSets getArmourSetType();

	boolean hasLevel(Enums.Skills skill, int level);

	public boolean isCooledDown(Enums.Counters counter);

	public void setCooldown(Enums.Counters counter, int cooldown);
}
