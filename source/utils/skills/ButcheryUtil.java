package net.tslat.aoa3.utils.skills;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;

public class ButcheryUtil {
	public static float getTickRegen(int lvl) {
		if (lvl <= 19)
			return 0.05f;

		if (lvl <= 39)
			return 0.1f;

		if (lvl <= 59)
			return 0.15f;

		if (lvl <= 79)
			return 0.2f;

		if (lvl <= 94)
			return 0.25f;

		return 0.3f;
	}

	public static float getCriticalMultiplier(int lvl) {
		if (lvl <= 14)
			return 1.1f;

		if (lvl <= 29)
			return 1.2f;

		if (lvl <= 49)
			return 1.3f;

		if (lvl <= 69)
			return 1.4f;

		if (lvl <= 89)
			return 1.5f;

		return 1.6f;
	}

	public static int getExpDenominator(final int lvl) {
		if (lvl <= 4)
			return 4;

		if (lvl <= 14)
			return 7;

		if (lvl <= 29)
			return 11;

		if (lvl <= 44)
			return 20;

		if (lvl <= 59)
			return 35;

		if (lvl <= 74)
			return 45;

		if (lvl <= 89)
			return 60;

		if (lvl <= 94)
			return 80;

		return 110;
	}

	public static void tryCritical(LivingHurtEvent event, PlayerDataManager plData) {
		if (AdventOfAscension.rand.nextInt(100) + 1 <= plData.stats().getResourceValue(Enums.Resources.RAGE)) {
			float multiplier = 1.0f;
			multiplier *= getCriticalMultiplier(plData.stats().getLevel(Enums.Skills.BUTCHERY));

			if (plData.stats().getResourceValue(Enums.Resources.RAGE) >= 180)
				multiplier *= 1.3f;

			event.setAmount(event.getAmount() * multiplier);

			if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.BUTCHERY && AdventOfAscension.rand.nextBoolean())
				return;

			plData.stats().consumeResource(Enums.Resources.RAGE, 200.0f, true);
		}
	}
}
