package net.nevermine.skill.expedition;

public class expeditionHelper {
	public static int getXpDenominator(final int level) {
		if (level <= 4)
			return 22;

		if (level <= 9)
			return 35;

		if (level <= 14)
			return 45;

		if (level <= 19)
			return 65;

		if (level <= 24)
			return 90;

		if (level <= 29)
			return 120;

		if (level <= 34)
			return 150;

		if (level <= 39)
			return 190;

		if (level <= 44)
			return 230;

		if (level <= 49)
			return 280;

		if (level <= 54)
			return 360;

		if (level <= 59)
			return 450;

		if (level <= 64)
			return 540;

		if (level <= 69)
			return 650;

		if (level <= 74)
			return 750;

		if (level <= 79)
			return 850;

		if (level <= 84)
			return 950;

		if (level <= 89)
			return 1000;

		if (level <= 94)
			return 1100;

		if (level <= 98)
			return 1200;

		return 1300;
	}
}
