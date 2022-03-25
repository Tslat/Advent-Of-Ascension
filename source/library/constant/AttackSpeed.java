package net.tslat.aoa3.library.constant;

/**
 * Attack Speed preset values.
 * Formula for custom values for external use:
 * N = Inverted Relative multiplier for attack speed (1 = standard sword speed)
 * X = Double value in code to pass as attribute value
 *
 * X = -1 * (4 - 1 / (12.5 * N / 20))
 */
public final class AttackSpeed {
	public static final float QUADRUPLE = -1f * (4 - 1 / (float)(12.5 * 0.25 / 20f));
	public static final float TRIPLE = -1f * (4 - 1 / (float)(12.5 * (1 / 3f) / 20f));
	public static final float DOUBLE = -1f * (4 - 1 / (float)(12.5 * 0.5 / 20f));
	public static final float NORMAL = -1f * (4 - 1 / (float)(12.5 * 1 / 20f));
	public static final float HALF = -1f * (4 - 1 / (float)(12.5 * 2 / 20f));
	public static final float THIRD = -1f * (4 - 1 / (float)(12.5 * 3 / 20f));
	public static final float QUARTER = -1f * (4 - 1 / (float)(12.5 * 4 / 20f));
	public static final float GREATBLADE = -3.24f;
}
