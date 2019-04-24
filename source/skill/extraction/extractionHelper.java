package net.nevermine.skill.extraction;

import java.util.Random;

public class extractionHelper {
	public static int getExpDenominator(final int level) {
		if (level < 3)
			return 5;

		if (level < 9)
			return 10;

		if (level < 17)
			return 20;

		if (level < 26)
			return 35;

		if (level < 41)
			return 60;

		if (level < 61)
			return 90;

		if (level < 76)
			return 150;

		if (level < 99)
			return 300;

		return 500;
	}

	public static int getLootPick(final int lv) {
		return new Random().nextInt((21 - ((100 - lv) / 5)) + 1);
	}

	public static boolean isExtractionFail(final int lv) {
		return new Random().nextInt(100) < Math.pow(((102 - lv) / 6), 1.34);
	}
}
