package net.tslat.aoa3.utils.skills;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;

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
			return 1.3f;

		if (lvl <= 29)
			return 1.4f;

		if (lvl <= 49)
			return 1.5f;

		if (lvl <= 69)
			return 1.6f;

		if (lvl <= 89)
			return 1.7f;

		return 1.8f;
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

	public static void tryCritical(LivingHurtEvent event, AdventPlayerCapability cap) {
		if (AdventOfAscension.rand.nextInt(100) + 1 <= cap.getResourceValue(Enums.Resources.RAGE)) {
			float multiplier = 1.0f;
			multiplier *= getCriticalMultiplier(cap.getLevel(Enums.Skills.BUTCHERY));

			if (cap.getResourceValue(Enums.Resources.RAGE) >= 180)
				multiplier *= 1.3f;

			event.setAmount(event.getAmount() * multiplier);

			if (cap.getArmourSetType() == Enums.ArmourSets.BUTCHERY) {
				if (!cap.getPlayer().capabilities.isCreativeMode)
					EntityUtil.dealSelfHarmDamage(cap.getPlayer(), 2.0f);

				if (AdventOfAscension.rand.nextInt(5) < 2)
					return;
			}

			cap.consumeResource(Enums.Resources.RAGE, 200.0f, true);
		}
	}
}
