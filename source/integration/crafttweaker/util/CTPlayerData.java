package net.tslat.aoa3.integration.crafttweaker.util;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.impl.item.MCItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import org.apache.logging.log4j.Level;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.UUID;

@ZenCodeType.Name("mods.aoa3.PlayerData")
public class CTPlayerData {
	private PlayerDataManager plData;
	private final UUID backupPlayerUUID;

	public CTPlayerData(PlayerDataManager plData) {
		this.plData = plData;
		this.backupPlayerUUID = plData.player().getUUID();
	}

	@Nullable
	public static CTPlayerData getForPlayer(PlayerEntity player) {
		if (player.level.isClientSide())
			return null;

		return new CTPlayerData(PlayerUtil.getAdventPlayer((ServerPlayerEntity)player));
	}

	@ZenCodeType.Method
	public int getLevel(String skillName, @ZenCodeType.OptionalBoolean boolean ignoreLevelCap) {
		if (!validatePlayerData())
			return 0;

		try {
			Skills skill = Skills.valueOf(skillName);

			return ignoreLevelCap ? plData.stats().getLevelForDisplay(skill) : plData.stats().getLevel(skill);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid skill name: " + skillName + " for CraftTweaker method.", ex);

			return -1;
		}
	}

	@ZenCodeType.Method
	public float getXp(String skillName) {
		if (!validatePlayerData())
			return 0;

		try {
			Skills skill = Skills.valueOf(skillName);

			return plData.stats().getExp(skill);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid skill name: " + skillName + " for CraftTweaker method.", ex);

			return -1;
		}
	}

	@ZenCodeType.Method
	public void grantXp(String skillName, float xpAmount) {
		if (!validatePlayerData())
			return;

		try {
			Skills skill = Skills.valueOf(skillName);

			plData.stats().addXp(skill, xpAmount, false, false);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid skill name: " + skillName + " for CraftTweaker method.", ex);
		}
	}

	@ZenCodeType.Method
	public float getResourceValue(String resourceName) {
		if (!validatePlayerData())
			return -1;

		try {
			Resources resource = Resources.valueOf(resourceName.toUpperCase(Locale.ROOT).replace(" ", "_"));

			return plData.stats().getResourceValue(resource);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid resource name: " + resourceName + " for CraftTweaker method.", ex);

			return -1;
		}
	}

	@ZenCodeType.Method
	public void grantResource(String resourceName, float amount) {
		if (!validatePlayerData())
			return;

		try {
			Resources resource = Resources.valueOf(resourceName.toUpperCase(Locale.ROOT).replace(" ", "_"));

			plData.stats().regenResource(resource, amount);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid resource name: " + resourceName + " for CraftTweaker method.", ex);
		}
	}

	@ZenCodeType.Method
	public int getTribute(String deityName) {
		if (!validatePlayerData())
			return -1;

		try {
			Deities deity = Deities.valueOf(deityName.toUpperCase(Locale.ROOT).replace(" ", "_"));

			return plData.stats().getTribute(deity);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid deity name: " + deityName + " for CraftTweaker method.", ex);

			return -1;
		}
	}

	@ZenCodeType.Method
	public boolean consumeResource(String resourceName, float amount, boolean forceConsume) {
		if (!validatePlayerData())
			return false;
		try {
			Resources resource = Resources.valueOf(resourceName.toUpperCase(Locale.ROOT).replace(" ", "_"));

			return plData.stats().consumeResource(resource, amount, forceConsume);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid resource name: " + resourceName + " for CraftTweaker method.", ex);

			return false;
		}
	}

	@ZenCodeType.Method
	public void grantTribute(String deityName, int amount) {
		if (!validatePlayerData())
			return;
		try {
			Deities deity = Deities.valueOf(deityName.toUpperCase(Locale.ROOT).replace(" ", "_"));

			plData.stats().addTribute(deity, amount);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid deity name: " + deityName + " for CraftTweaker method.", ex);
		}
	}

	@ZenCodeType.Method
	public void resetTributes() {
		plData.stats().resetAllTribute();
	}

	@ZenCodeType.Method
	public float getGlobalXpModifier() {
		if (!validatePlayerData())
			return -1;

		return plData.buffs().getGlobalXpModifier();
	}

	@ZenCodeType.Method
	public void addGlobalXpModifier(float amount) {
		if (!validatePlayerData())
			return;

		plData.buffs().addGlobalXpModifier(amount);
	}

	@ZenCodeType.Method
	public void removeGlobalXpModifier(float amount) {
		if (!validatePlayerData())
			return;

		plData.buffs().removeGlobalXpModifier(amount);
	}

	@ZenCodeType.Method
	public void addSkillXpModifier(String skillName, float amount) {
		if (!validatePlayerData())
			return;
		try {
			Skills skill = Skills.valueOf(skillName.toUpperCase(Locale.ROOT).replace(" ", "_"));

			plData.buffs().addXpModifier(skill, amount);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid skill name: " + skillName + " for CraftTweaker method.", ex);
		}
	}

	@ZenCodeType.Method
	public void removeSkillXpModifier(String skillName, float amount) {
		if (!validatePlayerData())
			return;

		try {
			Skills skill = Skills.valueOf(skillName.toUpperCase(Locale.ROOT).replace(" ", "_"));

			plData.buffs().removeXpModifier(skill, amount);
		}
		catch (IllegalArgumentException ex) {
			Logging.logMessage(Level.ERROR, "Invalid skill name: " + skillName + " for CraftTweaker method.", ex);
		}
	}

	@ZenCodeType.Getter
	public IItemStack getArmourSetType() {
		if (!validatePlayerData())
			return MCItemStack.EMPTY.get();

		if (plData.equipment().getCurrentFullArmourSet() == null)
			return MCItemStack.EMPTY.get();

		return new MCItemStack(new ItemStack(plData.player().getItemBySlot(EquipmentSlotType.FEET).getItem()));
	}

	private boolean validatePlayerData() {
		if (plData == null || plData.player() == null || !plData.player().isAlive()) {
			if (backupPlayerUUID == null)
				return false;

			ServerPlayerEntity pl = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(backupPlayerUUID);

			if (pl == null)
				return false;

			plData = PlayerUtil.getAdventPlayer(pl);
		}

		return true;
	}
}
