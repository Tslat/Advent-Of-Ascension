package net.tslat.aoa3.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.ExtractionUtil;
import net.tslat.aoa3.utils.skills.ForagingUtil;
import net.tslat.aoa3.utils.skills.HaulingUtil;
import net.tslat.aoa3.utils.skills.LoggingUtil;

import javax.annotation.Nullable;

public final class AoASkillAPI {
	/**
	 * Adds a weighted loot entry to the Extraction loot pool with an optional level requirement.
	 * Stack leaderboardCapacity will default to a leaderboardCapacity of 1.
	 *
	 * @param level the Extraction level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 */
	public static void addExtractionDrop(int level, int weight, @Nullable ItemStack loot) {
		addExtractionDrop(level, weight, loot, 1, 1);
	}

	/**
	 * Adds a weighted loot entry to the Extraction loot pool with an optional level requirement and a given minimum and maximum stack leaderboardCapacity.
	 *
	 * @param level the Extraction level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 * @param minStackSize minimum stack leaderboardCapacity for the loot stack.<br>
	 *           Min value: 1
	 * @param maxStackSize maximum stack leaderboardCapacity for the loot stack. Size of the stack will be randomly chosen between minStackSize and this, inclusive.<br>
	 *           Min value must be higher than minStackSize<br>
	 *           Max value: 64
	 */
	public static void addExtractionDrop(int level, int weight, @Nullable ItemStack loot, int minStackSize, int maxStackSize) {
		loot = loot == null ? ItemStack.EMPTY : loot;
		minStackSize = MathHelper.clamp(minStackSize, 1, loot.getMaxStackSize());
		maxStackSize = MathHelper.clamp(maxStackSize, minStackSize, loot.getMaxStackSize());
		level = Math.max(1, level);
		weight = Math.max(1, weight);

		ExtractionUtil.lootTable.addEntry(level, weight, new ExtractionUtil.ExtractionDrop(loot, StringUtil.getLocaleStringWithArguments("message.feedback.extraction.generic", loot.getDisplayName()), minStackSize, maxStackSize));
	}


	/**
	 * Adds a weighted loot entry to the Foraging loot pool with an optional level requirement.
	 * Stack leaderboardCapacity will default to a leaderboardCapacity of 1.
	 *
	 * @param level the Foraging level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 * @param xp the amount of Foraging xp to give as part of this loot bundle.<br>
	 *           Min value: 0<br>
	 *           Max value: 20000
	 */
	public static void addForagingDrop(int level, int weight, @Nullable ItemStack loot, float xp) {
		addForagingDrop(level, weight, loot, xp, 1, 1);
	}

	/**
	 * Adds a weighted loot entry to the Foraging loot pool with an optional level requirement and a given minimum and maximum stack leaderboardCapacity.
	 *
	 * @param level the Foraging level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 * @param xp the amount of Foraging xp to give as part of this loot bundle.<br>
	 *           Min value: 0<br>
	 *           Max value: 20000
	 * @param minStackSize minimum stack leaderboardCapacity for the loot stack.<br>
	 *           Min value: 1
	 * @param maxStackSize maximum stack leaderboardCapacity for the loot stack. Size of the stack will be randomly chosen between minStackSize and this, inclusive.<br>
	 *           Min value must be higher than minStackSize<br>
	 *           Max value: 64
	 */
	public static void addForagingDrop(int level, int weight, @Nullable ItemStack loot, float xp, int minStackSize, int maxStackSize) {
		loot = loot == null ? ItemStack.EMPTY : loot;
		minStackSize = MathHelper.clamp(minStackSize, 1, 64);
		maxStackSize = MathHelper.clamp(maxStackSize, minStackSize, 64);
		xp = MathHelper.clamp(xp, 0,20000);
		level = Math.max(1, level);
		weight = Math.max(1, weight);

		ForagingUtil.lootTable.addEntry(level, weight, new ForagingUtil.ForagingDrop(loot, xp, minStackSize, maxStackSize));
	}


	/**
	 * Adds a weighted loot entry to the Hauling loot pool with an optional level requirement.
	 * Stack leaderboardCapacity will default to a leaderboardCapacity of 1.
	 *
	 * @param level the Hauling level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 * @param xp the amount of Hauling xp to give as part of this loot bundle.<br>
	 *           Min value: 0<br>
	 *           Max value: 20000
	 */
	public static void addHaulingDrop(int level, int weight, @Nullable ItemStack loot, float xp) {
		addHaulingDrop(level, weight, loot, xp, 1, 1);
	}

	/**
	 * Adds a weighted loot entry to the Hauling loot pool with an optional level requirement and a given minimum and maximum stack leaderboardCapacity.
	 *
	 * @param level the Hauling level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 * @param xp the amount of Hauling xp to give as part of this loot bundle.<br>
	 *           Min value: 0<br>
	 *           Max value: 20000
	 * @param minStackSize minimum stack leaderboardCapacity for the loot stack.<br>
	 *           Min value: 1
	 * @param maxStackSize maximum stack leaderboardCapacity for the loot stack. Size of the stack will be randomly chosen between minStackSize and this, inclusive.<br>
	 *           Min value must be higher than minStackSize<br>
	 *           Max value: 64
	 */
	public static void addHaulingDrop(int level, int weight, @Nullable ItemStack loot, float xp, int minStackSize, int maxStackSize) {
		loot = loot == null ? ItemStack.EMPTY : loot;
		minStackSize = MathHelper.clamp(minStackSize, 1, 64);
		maxStackSize = MathHelper.clamp(maxStackSize, minStackSize, 64);
		xp = MathHelper.clamp(xp, 0,20000);
		level = Math.max(1, level);
		weight = Math.max(1, weight);

		HaulingUtil.lootTable.addEntry(level, weight, new HaulingUtil.HaulingDrop(loot, xp, minStackSize, maxStackSize));
	}


	/**
	 * Adds a weighted loot entry to the Logging loot pool with an optional level requirement.
	 * Stack leaderboardCapacity will default to a leaderboardCapacity of 1.
	 *
	 * @param level the Logging level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 * @param xp the amount of Logging xp to give as part of this loot bundle.<br>
	 *           Min value: 0<br>
	 *           Max value: 20000
	 */
	public static void addLoggingDrop(int level, int weight, @Nullable ItemStack loot, float xp) {
		addLoggingDrop(level, weight, loot, xp, 1, 1);
	}

	/**
	 * Adds a weighted loot entry to the Logging loot pool with an optional level requirement and a given minimum and maximum stack leaderboardCapacity.
	 *
	 * @param level the Logging level required to obtain this drop<br>
	 *              Min value: 1
	 * @param weight weight rating defining how likely the drop is to occur relative to all others. Base weight
	 *               rating is 100 for simple drops, with higher numbers being proportionally more rare.<br>
	 *               Min value: 1
	 * @param loot the itemstack that drops as part of this loot bundles.<br>
	 *             CAN BE NULL
	 * @param xp the amount of Logging xp to give as part of this loot bundle.<br>
	 *           Min value: 0<br>
	 *           Max value: 20000
	 * @param minStackSize minimum stack leaderboardCapacity for the loot stack.<br>
	 *           Min value: 1
	 * @param maxStackSize maximum stack leaderboardCapacity for the loot stack. Size of the stack will be randomly chosen between minStackSize and this, inclusive.<br>
	 *           Min value must be higher than minStackSize<br>
	 *           Max value: 64
	 */
	public static void addLoggingDrop(int level, int weight, @Nullable ItemStack loot, float xp, int minStackSize, int maxStackSize) {
		loot = loot == null ? ItemStack.EMPTY : loot;
		minStackSize = MathHelper.clamp(minStackSize, 1, 64);
		maxStackSize = MathHelper.clamp(maxStackSize, minStackSize, 64);
		xp = MathHelper.clamp(xp, 0,20000);
		level = Math.max(1, level);
		weight = Math.max(1, weight);

		LoggingUtil.lootTable.addEntry(level, weight, new LoggingUtil.LoggingDrop(loot, xp, minStackSize, maxStackSize));
	}
}
