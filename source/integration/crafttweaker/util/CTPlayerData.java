package net.tslat.aoa3.integration.crafttweaker.util;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.impl.item.MCItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import org.apache.logging.log4j.Level;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nullable;
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
	public int getLevel(String skillId, @ZenCodeType.OptionalBoolean boolean ignoreLevelCap) {
		if (!validatePlayerData())
			return 0;

		AoASkill skill = AoASkills.getSkill(new ResourceLocation(skillId));

		if (skill == null) {
			Logging.logMessage(Level.WARN, "Invalid skill name: " + skillId + " for CraftTweaker method.");

			return -1;
		}

		return plData.getSkill(skill).getLevel(ignoreLevelCap);
	}

	@ZenCodeType.Method
	public float getXp(String skillId) {
		if (!validatePlayerData())
			return 0;

		AoASkill skill = AoASkills.getSkill(new ResourceLocation(skillId));

		if (skill == null) {
			Logging.logMessage(Level.WARN, "Invalid skill name: " + skillId + " for CraftTweaker method.");

			return -1;
		}

		return plData.getSkill(skill).getXp();
	}

	@ZenCodeType.Method
	public void grantXp(String skillId, float xpAmount) {
		if (!validatePlayerData())
			return;

		AoASkill skill = AoASkills.getSkill(new ResourceLocation(skillId));

		if (skill == null) {
			Logging.logMessage(Level.WARN, "Invalid skill name: " + skillId + " for CraftTweaker method.");

			return;
		}

		plData.getSkill(skill).adjustXp(xpAmount, false, false);
	}

	@ZenCodeType.Method
	public float getResourceValue(String resourceId) {
		if (!validatePlayerData())
			return -1;

		AoAResource resource = AoAResources.getResource(new ResourceLocation(resourceId));

		if (resource == null) {
			Logging.logMessage(Level.ERROR, "Invalid resource name: " + resourceId + " for CraftTweaker method.");

			return -1;
		}

		return plData.getResource(resource).getCurrentValue();
	}

	@ZenCodeType.Method
	public void grantResource(String resourceId, float amount) {
		if (!validatePlayerData())
			return;

		AoAResource resource = AoAResources.getResource(new ResourceLocation(resourceId));

		if (resource == null) {
			Logging.logMessage(Level.ERROR, "Invalid resource name: " + resourceId + " for CraftTweaker method.");

			return;
		}

		plData.getResource(resource).addValue(amount);
	}

	@ZenCodeType.Method
	public boolean consumeResource(String resourceId, float amount, boolean forceConsume) {
		if (!validatePlayerData())
			return false;

		AoAResource resource = AoAResources.getResource(new ResourceLocation(resourceId));

		if (resource == null) {
			Logging.logMessage(Level.ERROR, "Invalid resource name: " + resourceId + " for CraftTweaker method.");

			return false;
		}

		return plData.getResource(resource).consume(amount, forceConsume);
	}

	@ZenCodeType.Method
	public void addSkillXpModifier(String skillId, float amount) {
		if (!validatePlayerData())
			return;

		AoASkill skill = AoASkills.getSkill(new ResourceLocation(skillId));

		if (skill == null) {
			Logging.logMessage(Level.ERROR, "Invalid skill ID: " + skillId + " for CraftTweaker method.");

			return;
		}

		plData.getSkill(skill).applyXpModifier(amount);
	}

	@ZenCodeType.Method
	public void removeSkillXpModifier(String skillId, float amount) {
		if (!validatePlayerData())
			return;

		AoASkill skill = AoASkills.getSkill(new ResourceLocation(skillId));

		if (skill == null) {
			Logging.logMessage(Level.ERROR, "Invalid skill ID: " + skillId + " for CraftTweaker method.");

			return;
		}

		plData.getSkill(skill).removeXpModifier(amount);
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
