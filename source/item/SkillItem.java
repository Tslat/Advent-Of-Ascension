package net.tslat.aoa3.item;

import net.tslat.aoa3.util.constant.Skills;

/**
 * Implement this interface on your item, and the mod will automatically manage item-removal and unequipping when required.
 */
public interface SkillItem {
	/**
	 * The skill required for the item.
	 *
	 * @return An {@code Skills} skill
	 */
	public Skills getSkill();

	/**
	 * The level required in the given skill to hold/wear the item.
	 *
	 * @return An int value representing the level required.
	 */
	public int getLevelReq();
}
