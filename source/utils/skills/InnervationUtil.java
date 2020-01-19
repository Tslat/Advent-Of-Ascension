package net.tslat.aoa3.utils.skills;

public class InnervationUtil {
	public static int getExpDenominator(final int lvl) {
		if (lvl < 5)
			return 4;

		if (lvl < 15)
			return 7;

		if (lvl < 30)
			return 11;

		if (lvl < 45)
			return 25;

		if (lvl < 60)
			return 40;

		if (lvl < 75)
			return 60;

		if (lvl < 90)
			return 80;

		if (lvl < 95)
			return 100;

		return 150;
	}

	public static double getHealthBuff(final int lvl) {
		return Math.floor(Math.min(100, lvl) / 5d);
	}

	public static float getHeartstoneHealAmount(final int lvl) {
		if (lvl < 8)
			return 0.0f;

		if (lvl < 15)
			return 1.0f;

		if (lvl < 37)
			return 2.0f;

		if (lvl < 70)
			return 3.0f;

		return 4.0f;
	}
}
