package net.tslat.aoa3.hooks.crafttweaker.util;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import crafttweaker.mc1120.item.MCItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
import stanhebben.zenscript.annotations.*;

import javax.annotation.Nullable;
import java.util.UUID;

@ZenClass("mods.aoa3.PlayerData")
public class CTPlayerData {
	private PlayerDataManager plData;
	private final UUID backupPlayerUUID;

	public CTPlayerData(PlayerDataManager plData) {
		this.plData = plData;
		this.backupPlayerUUID = plData.player().getUniqueID();
	}

	@Nullable
	public static CTPlayerData getForPlayer(IPlayer player) {
		if (player.getInternal() == null || ModUtil.isClient())
			return null;

		return new CTPlayerData(PlayerUtil.getAdventPlayer((EntityPlayer)player.getInternal()));
	}

	@ZenGetter
	public int getLevel(String skillName, @Optional boolean ignoreLevelCap) {
		if (!validatePlayerData())
			return 0;

		Enums.Skills skill = Enums.Skills.fromString(skillName);

		if (skill == null)
			return -1;

		return ignoreLevelCap ? plData.stats().getLevelForDisplay(skill) : plData.stats().getLevel(skill);
	}

	@ZenGetter
	public float getXp(String skillName) {
		if (!validatePlayerData())
			return 0;

		Enums.Skills skill = Enums.Skills.fromString(skillName);

		if (skill == null)
			return -1;

		return plData.stats().getExp(skill);
	}

	@ZenMethod
	public void grantXp(String skillName, float xpAmount) {
		if (!validatePlayerData())
			return;

		Enums.Skills skill = Enums.Skills.fromString(skillName);

		if (skill != null)
			plData.stats().addXp(skill, xpAmount, false, false);
	}

	@ZenGetter
	public float getResourceValue(String resourceName) {
		if (!validatePlayerData())
			return -1;

		Enums.Resources resource = Enums.Resources.fromString(resourceName);

		if (resource != null)
			return plData.stats().getResourceValue(resource);

		return -1;
	}

	@ZenMethod
	public void grantResource(String resourceName, float amount) {
		if (!validatePlayerData())
			return;

		Enums.Resources resource = Enums.Resources.fromString(resourceName);

		if (resource != null)
			plData.stats().regenResource(resource, amount);
	}

	@ZenGetter
	public int getTribute(String deityName) {
		if (!validatePlayerData())
			return -1;

		Enums.Deities deity = Enums.Deities.fromString(deityName);

		if (deity != null)
			return plData.stats().getTribute(deity);

		return -1;
	}

	@ZenMethod
	public boolean consumeResource(String resourceName, int amount, boolean forceConsume) {
		if (!validatePlayerData())
			return false;

		Enums.Resources resource = Enums.Resources.fromString(resourceName);

		if (resource != null)
			return plData.stats().consumeResource(resource, amount, forceConsume);

		return false;
	}

	@ZenMethod
	public void grantTribute(String deityName, int amount) {
		if (!validatePlayerData())
			return;

		Enums.Deities deity = Enums.Deities.fromString(deityName);

		if (deity != null)
			plData.stats().addTribute(deity, amount);
	}

	@ZenMethod
	public void resetTributes() {
		plData.stats().resetAllTribute();
	}

	@ZenGetter
	public float getGlobalXpModifier() {
		if (!validatePlayerData())
			return -1;

		return plData.buffs().getGlobalXpModifier();
	}

	@ZenMethod
	public void addGlobalXpModifier(float amount) {
		if (!validatePlayerData())
			return;

		plData.buffs().addGlobalXpModifier(amount);
	}

	@ZenMethod
	public void removeGlobalXpModifier(float amount) {
		if (!validatePlayerData())
			return;

		plData.buffs().removeGlobalXpModifier(amount);
	}

	@ZenMethod
	public void addSkillXpModifier(String skillName, float amount) {
		if (!validatePlayerData())
			return;

		Enums.Skills skill = Enums.Skills.fromString(skillName);

		if (skill != null)
			plData.buffs().addXpModifier(skill, amount);
	}

	@ZenMethod
	public void removeSkillXpModifier(String skillName, float amount) {
		if (!validatePlayerData())
			return;

		Enums.Skills skill = Enums.Skills.fromString(skillName);

		if (skill != null)
			plData.buffs().removeXpModifier(skill, amount);
	}

	@ZenGetter
	public IItemStack getArmourSetType() {
		if (!validatePlayerData())
			return MCItemStack.EMPTY;

		if (plData.equipment().getCurrentFullArmourSet() == null)
			return MCItemStack.EMPTY;

		return new MCItemStack(new ItemStack(plData.player().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem()));
	}

	private boolean validatePlayerData() {
		if (plData == null || plData.player() == null || plData.player().isDead) {
			if (backupPlayerUUID == null)
				return false;

			EntityPlayerMP pl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(backupPlayerUUID);

			if (pl == null)
				return false;

			plData = PlayerUtil.getAdventPlayer(pl);
		}

		return true;
	}
}
