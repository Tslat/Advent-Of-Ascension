package net.tslat.aoa3.utils.skills;

public class AnimaUtil {
	public static int getExpDenominator(final int lvl) {
		if (lvl <= 4)
			return 4;

		if (lvl <= 14)
			return 8;

		if (lvl <= 29)
			return 30;

		if (lvl <= 44)
			return 60;

		if (lvl <= 59)
			return 85;

		if (lvl <= 74)
			return 112;

		if (lvl <= 89)
			return 180;

		if (lvl <= 94)
			return 220;

		return 280;
	}
}
