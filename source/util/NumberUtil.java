package net.tslat.aoa3.util;

import java.text.DecimalFormat;

public final class NumberUtil {
	public static String floorAndAppendSuffix(float value, boolean strictDigitCount) {
		String suffix = "";

		if (value >= 10000) {
			if (value < 1000000) {
				suffix = "k";
				value = value / 1000f;
			}
			else if (value < 1000000000) {
				suffix = "m";
				value = value / 1000000f;
			}
			else {
				suffix = "b";
				value = value / 1000000000f;
			}
		}

		if (strictDigitCount && value >= 10)
			value = (int)Math.floor(value);

		return new DecimalFormat(strictDigitCount ? "#.#" : "#.##").format(value) + suffix;
	}

	public static double average(double... values) {
		double sum = 0;

		for (double d : values) {
			sum += d;
		}

		return sum / values.length;
	}

	public static String roundToNthDecimalPlace(float value, int decimals) {
		float val = Math.round(value * (float)Math.pow(10, decimals)) / (float)Math.pow(10, decimals);

		if (((int)val) == val)
			return String.valueOf((int)val);

		return String.valueOf(val);
	}

	public static boolean isWithinArea(int x, int y, int minX, int minY, int maxX, int maxY) {
		return isWithinArea(x, y, 0, minX, minY, 0, maxX, maxY, 0);
	}

	public static boolean isWithinArea(int x, int y, int z, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
		if (x != 0 && x < minX || x > maxX)
			return false;

		if (y != 0 && y < minY || y > maxY)
			return false;

		return (z == 0 || z >= minZ) && z <= maxZ;
	}

	public static boolean numberIsBetween(double value, double min, double max) {
		return value > min && value < max;
	}
}
