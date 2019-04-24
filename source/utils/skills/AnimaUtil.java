package net.tslat.aoa3.utils.skills;

import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;

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

	public static void doAnimaStonePickup(PlayerEvent.ItemPickupEvent ev) {
		AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.player);

		while (ev.getStack().getCount() > 0) {
			cap.addXp(Enums.Skills.ANIMA, cap.getXpReqForLevel(cap.getLevel(Enums.Skills.ANIMA) / getExpDenominator(cap.getLevel(Enums.Skills.ANIMA))), false);
			ev.getStack().shrink(1);
		}

		ev.player.world.playSound(null, ev.player.posX, ev.player.posY, ev.player.posZ, SoundsRegister.heartStonePickup, SoundCategory.PLAYERS, 1.0f, 1.0f);
		ev.getOriginalEntity().setDead();
		ev.pickedUp.setDead();
	}
}
