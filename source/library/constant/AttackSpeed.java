package net.tslat.aoa3.library.constant;

public final class AttackSpeed {
	public static final float QUADRUPLE = getCustomSpeed(4f);
	public static final float TRIPLE = getCustomSpeed(3f);
	public static final float DOUBLE = getCustomSpeed(2f);
	public static final float NORMAL = getCustomSpeed(1f);
	public static final float HALF = getCustomSpeed(0.5f);
	public static final float THIRD = getCustomSpeed(0.33f);
	public static final float QUARTER = getCustomSpeed(0.25f);

	public static final float SHOVEL = -3.0f;
	public static final float PICKAXE = -2.8f;
	public static final float AXE = -3.0f;
	public static final float HOE = -3.0f;
	public static final float SWORD = -2.4f;
	public static final float GREATBLADE = -3.24f;

	/**
	 * Returns a float value to pass to an item tier that is equivalent to the attack speed of a sword multiplied by the passed parameter
	 *
	 * @param speedMod float value representing a multiple of 1.6 (default sword attack speed). E.G. Passing 2f provides an attack speed twice as fast as a standard sword
	 * @return the float value to pass to a {@code net.minecraft.world.item.Tier} to create an item's stats
	 */
	public static float getCustomSpeed(float speedMod) {
		return -1f * (4 - 1 / (0.625f * (1 / speedMod)));
	}

	/**
	 * Returns a float value to pass to an item tier that represents an absolute attacks-per-second value
	 *
	 * @param aps float value representing the number of attacks per second to build the value for
	 * @return the float value to pass to a {@code net.minecraft.world.item.Tier} to create an item's stats
	 */
	public static float forAttacksPerSecond(float aps) {
		return -4 + aps;
	}
}
