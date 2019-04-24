package net.nevermine.skill.butchery;

import net.minecraft.entity.player.EntityPlayer;
import net.nevermine.container.PlayerContainer;

import static net.nevermine.container.PlayerContainer.Skills.Butchery;

public class butcheryHelper {
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

	public static float getDamageMultiplier(EntityPlayer pl) {
		final int lvl = PlayerContainer.getProperties(pl).getLevel(Butchery);

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

	public static float getTickRegen(EntityPlayer pl) {
		final int lvl = PlayerContainer.getProperties(pl).getLevel(Butchery);

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
}
