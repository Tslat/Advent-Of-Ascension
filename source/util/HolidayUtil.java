package net.tslat.aoa3.util;

import java.time.LocalDate;
import java.time.Month;

public abstract class HolidayUtil {
	public static Holiday getCurrentHoliday() {
		LocalDate date = LocalDate.now();

		if (date.compareTo(LocalDate.of(date.getYear(), Month.APRIL, 1)) == 0)
			return Holiday.APRIL_FOOLS;

		if (date.compareTo(LocalDate.of(date.getYear(), Month.DECEMBER, 25)) == 0)
			return Holiday.CHRISTMAS;

		if (date.compareTo(LocalDate.of(date.getYear(), Month.OCTOBER, 31)) == 0)
			return Holiday.HALLOWEEN;

		return Holiday.NONE;
	}

	public enum Holiday {
		NONE,
		CHRISTMAS,
		HALLOWEEN,
		APRIL_FOOLS
	}
}
