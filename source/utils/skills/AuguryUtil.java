package net.tslat.aoa3.utils.skills;

public class AuguryUtil {
	public static float getMaxCreation(int lvl) {
		return 200;
		/*
		if (lvl < 30)
			return 200;

		if (lvl < 60)
			return 240f;

		if (lvl < 85)
			return 280f;

		return 320f;*/
	}

	public static float getMaxSoul(int lvl) {
		return 200f;
		/*
		if (lvl < 35)
			return 200f;

		if (lvl < 70)
			return 260f;

		if (lvl < 90)
			return 320f;

		return 400f;*/
	}
}
