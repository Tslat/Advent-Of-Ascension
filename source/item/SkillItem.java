package net.tslat.aoa3.item;

import net.tslat.aoa3.library.Enums;

/**
 * Implement this interface on your item, and the mod will automatically manage item-removal and unequipping when required.
 */
public interface SkillItem {
	/**
	 * The skill required for the item.
	 *
	 * @return An {@code net.tslat.aoa3.utils.Enums.Skills} skill
	 */
	public Enums.Skills getSkill();

	/**
	 * The level required in the given skill to hold/wear the item.
	 *
	 * @return An int value representing the level required.
	 */
	public int getLevelReq();
}
