package net.nevermine.skill.infusion;

public class infusionHelper {
	public enum InfusionEnchant {
		Sharpness,
		Shell,
		Power,
		Sever,
		Slice,
		Overpower,
		Unbreaking,
		Knockback,
		Punch,
		Control,
		Crush,
		Efficiency,
		Windfury,
		Recharge,
		Protection,
		Archmage,
		Lure,
		SeaLuck
	}

	public static int getInfusionEnchantLevel(final InfusionEnchant ench, final int lvl) {
		switch (ench) {
			case Sharpness:
				if (lvl < 16)
					return 2;

				if (lvl < 41)
					return 3;

				if (lvl < 81)
					return 4;

				return 5;
			case Shell:
				if (lvl < 37)
					return 1;

				if (lvl < 78)
					return 2;

				return 3;
			case Power:
				if (lvl < 16)
					return 2;

				if (lvl < 41)
					return 3;

				if (lvl < 81)
					return 4;

				return 5;
			case Sever:
				if (lvl < 61)
					return 1;

				if (lvl < 86)
					return 2;

				return 3;
			case Slice:
				if (lvl < 61)
					return 1;

				if (lvl < 86)
					return 2;

				return 3;
			case Overpower:
				if (lvl < 51)
					return 1;

				return 2;
			case Unbreaking:
				if (lvl < 61)
					return 1;

				if (lvl < 81)
					return 2;

				if (lvl < 96)
					return 3;

				return 4;
			case Knockback:
				if (lvl < 21)
					return 1;

				if (lvl < 46)
					return 2;

				if (lvl < 66)
					return 3;

				return 4;
			case Punch:
				if (lvl < 21)
					return 1;

				if (lvl < 46)
					return 2;

				if (lvl < 66)
					return 3;

				return 4;
			case Control:
				if (lvl < 56)
					return 1;

				return 2;
			case Crush:
				if (lvl < 51)
					return 1;

				return 2;
			case Efficiency:
				if (lvl < 61)
					return 3;

				if (lvl < 81)
					return 4;

				if (lvl < 92)
					return 5;

				return 6;
			case Windfury:
				if (lvl < 31)
					return 1;

				if (lvl < 71)
					return 2;

				return 3;
			case Recharge:
				if (lvl < 61)
					return 1;

				return 2;
			case Protection:
				if (lvl < 47)
					return 1;

				if (lvl < 67)
					return 2;

				if (lvl < 87)
					return 3;

				return 4;
			case Archmage:
				if (lvl < 85)
					return 0;

				return 1;
			case Lure:
				if (lvl < 16)
					return 2;

				if (lvl < 41)
					return 3;

				if (lvl < 81)
					return 4;

				return 5;
			case SeaLuck:
				if (lvl < 21)
					return 2;

				if (lvl < 46)
					return 3;

				if (lvl < 66)
					return 4;

				return 5;
			default:
				break;
		}

		return 0;
	}
}
