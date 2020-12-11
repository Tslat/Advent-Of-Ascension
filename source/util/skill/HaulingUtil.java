package net.tslat.aoa3.util.skill;

public class HaulingUtil {
	public static int getTreasureBoxRolls(final int lvl) {
		if (lvl < 11)
			return 1;

		if (lvl < 26)
			return 2;

		if (lvl < 41)
			return 3;

		if (lvl < 56)
			return 4;

		if (lvl < 71)
			return 5;

		if (lvl < 86)
			return 6;

		if (lvl < 96)
			return 7;

		return 8;
	}

	public static int getRunesLootCount(final int lvl) {
		if (lvl <= 15)
			return 1;

		if (lvl <= 25)
			return 3;

		if (lvl <= 35)
			return 5;

		if (lvl <= 55)
			return 8;

		if (lvl <= 65)
			return 11;

		if (lvl <= 75)
			return 15;

		if (lvl <= 85)
			return 17;

		if (lvl <= 95)
			return 19;

		return 21;
	}
}
