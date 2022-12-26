package net.tslat.aoa3.util;

import com.google.common.base.Suppliers;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

public final class HolidayUtil {
	private static final Supplier<Holiday> CURRENT_HOLIDAY = Suppliers.memoize(() -> {
		LocalDate date = LocalDate.now();

		if (date.isEqual(LocalDate.of(date.getYear(), Month.APRIL, 1)))
			return Holiday.APRIL_FOOLS;

		if (date.isEqual(LocalDate.of(date.getYear(), Month.DECEMBER, 25)))
			return Holiday.CHRISTMAS;

		if (date.isEqual(LocalDate.of(date.getYear(), Month.OCTOBER, 31)))
			return Holiday.HALLOWEEN;

		return Holiday.NONE;
	});

	public static Holiday getCurrentHoliday() {
		return CURRENT_HOLIDAY.get();
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

	public enum Holiday {
		NONE,
		CHRISTMAS,
		HALLOWEEN,
		APRIL_FOOLS
	}
}
