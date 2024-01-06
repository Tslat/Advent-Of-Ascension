package net.tslat.aoa3.util;

import java.time.LocalDate;
import java.time.Month;

public final class HolidayUtil {
	public static Holiday getCurrentHoliday() {
		final LocalDate date = LocalDate.now();

		if (date.isEqual(forDay(Month.APRIL, 1)))
			return Holiday.APRIL_FOOLS;

		if (date.isEqual(forDay(Month.DECEMBER, 25)))
			return Holiday.CHRISTMAS;

		if (date.isEqual(forDay(Month.OCTOBER, 31)))
			return Holiday.HALLOWEEN;

		return Holiday.NONE;
	}

	public static boolean isChristmas() {
		return getCurrentHoliday() == Holiday.CHRISTMAS;
	}

	public static boolean isHalloween() {
		return getCurrentHoliday() == Holiday.HALLOWEEN;
	}

	public static boolean isAprilFools() {
		return getCurrentHoliday() == Holiday.APRIL_FOOLS;
	}

	private static LocalDate forDay(Month month, int number) {
		return LocalDate.of(LocalDate.now().getYear(), month, number);
	}

	public enum Holiday {
		NONE,
		CHRISTMAS,
		HALLOWEEN,
		APRIL_FOOLS
	}
}
