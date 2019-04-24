package net.nevermine.skill.innervation;

import java.util.Random;

public class innervationHelper {
	private static Random rand = new Random();

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

	public static float getHeartstoneHealAmount(final int lvl) {
		if (lvl < 15)
			return 0.0f;

		if (lvl < 30)
			return 2.0f;

		if (lvl < 45)
			return 4.0f;

		if (lvl < 60)
			return 6.0f;

		if (lvl < 75)
			return 8.0f;

		if (lvl < 90)
			return 10.0f;

		if (lvl < 99)
			return 14.0f;

		return 20.0f;
	}

	public static boolean tryDodge(final int lvl) {
		if (lvl < 15)
			return false;

		if (lvl < 30 && rand.nextInt(30) == 0)
			return true;

		if (lvl < 50 && rand.nextInt(20) == 0)
			return true;

		if (lvl < 70 && rand.nextInt(10) == 0)
			return true;

		if (lvl < 90 && rand.nextInt(7) == 0)
			return true;

		return rand.nextInt(4) == 0;

	}

	public static float getDodgeHealAmount(final int lvl) {
		if (lvl < 15)
			return 0.0f;

		if (lvl < 30)
			return 2.0f;

		if (lvl < 70)
			return 3.0f;

		return 4.0f;
	}
}
