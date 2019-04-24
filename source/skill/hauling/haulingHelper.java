package net.nevermine.skill.hauling;

public class haulingHelper {
	public static int getExpDenominator(final int lvl) {
		if (lvl <= 4)
			return 2;

		if (lvl <= 14)
			return 5;

		if (lvl <= 29)
			return 9;

		if (lvl <= 44)
			return 15;

		if (lvl <= 59)
			return 25;

		if (lvl <= 74)
			return 40;

		if (lvl <= 89)
			return 60;

		if (lvl <= 94)
			return 80;

		return 100;
	}

	public static int getRunesLootCount(final int lvl) {
		if (lvl <= 15)
			return 5;

		if (lvl <= 25)
			return 8;

		if (lvl <= 35)
			return 13;

		if (lvl <= 55)
			return 21;

		if (lvl <= 65)
			return 32;

		if (lvl <= 75)
			return 45;

		if (lvl <= 85)
			return 54;

		if (lvl <= 95)
			return 66;

		return 80;
	}

	public static int getSkillCrystalPossibilities(final int lvl) {
		if (lvl < 25)
			return 1;

		if (lvl < 50)
			return 2;

		if (lvl < 75)
			return 3;

		return 4;
	}

	public static int getFishCasePossibilities(final int lvl) {
		if (lvl < 6)
			return 2;

		if (lvl < 11)
			return 3;

		if (lvl < 16)
			return 4;

		if (lvl < 21)
			return 5;

		if (lvl < 26)
			return 6;

		if (lvl < 31)
			return 7;

		if (lvl < 36)
			return 8;

		if (lvl < 46)
			return 9;

		if (lvl < 54)
			return 10;

		if (lvl < 66)
			return 11;

		if (lvl < 74)
			return 12;

		if (lvl < 82)
			return 13;

		if (lvl < 87)
			return 14;

		if (lvl < 94)
			return 15;

		return 16;
	}

	public static int getTreasureBoxRolls(final int lvl) {
		if (lvl < 11)
			return 2;

		if (lvl < 26)
			return 3;

		if (lvl < 41)
			return 5;

		if (lvl < 56)
			return 7;

		if (lvl < 71)
			return 9;

		if (lvl < 86)
			return 12;

		if (lvl < 96)
			return 14;

		if (lvl < 98)
			return 17;

		return 18;
	}
}
