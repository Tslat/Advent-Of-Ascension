package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PlayerUtil;

public class InnervationUtil {
	public static int getExpDenominator(final int lvl) {
		if (lvl < 5)
			return 4;

		if (lvl < 15)
			return 7;

		if (lvl < 30)
			return 11;

		if (lvl < 45)
			return 25;

		if (lvl < 60)
			return 40;

		if (lvl < 75)
			return 60;

		if (lvl < 90)
			return 80;

		if (lvl < 95)
			return 100;

		return 150;
	}

	public static float getHeartstoneHealAmount(final int lvl) {
		if (lvl < 15)
			return 0.0f;

		if (lvl < 30)
			return 2.0f;

		if (lvl < 45)
			return 4.0f;

		if (lvl < 60)
			return 6.0f;

		if (lvl < 75)
			return 8.0f;

		if (lvl < 90)
			return 10.0f;

		if (lvl < 99)
			return 14.0f;

		return 20.0f;
	}

	public static float getDodgeHealAmount(final int lvl) {
		if (lvl < 15)
			return 0.0f;

		if (lvl < 30)
			return 2.0f;

		if (lvl < 70)
			return 3.0f;

		return 4.0f;
	}

	public static boolean testDodge(final int lvl) {
		if (lvl < 5)
			return false;

		return AdventOfAscension.rand.nextInt((int)(15 / (lvl / 100d))) == 0;
	}

	public static void tryDodge(LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntityLiving());

			if (testDodge(cap.getLevel(Enums.Skills.INNERVATION))) {
				if (AdventOfAscension.rand.nextBoolean()) {
					ev.setCanceled(true);
				}
				else {
					EntityUtil.healEntity((EntityPlayer)ev.getEntity(), getDodgeHealAmount(cap.getLevel(Enums.Skills.INNERVATION)));
				}
			}
		}
	}

	public static void doHeartStonePickup(PlayerEvent.ItemPickupEvent ev) {
		AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.player);

		while (ev.getStack().getCount() > 0) {
			int lvl = cap.getLevel(Enums.Skills.INNERVATION);

			cap.addXp(Enums.Skills.INNERVATION, cap.getXpReqForLevel(lvl) / getExpDenominator(lvl), false);
			ev.getStack().shrink(1);
		}

		EntityUtil.healEntity(ev.player, getHeartstoneHealAmount(cap.getLevel(Enums.Skills.INNERVATION)));
		ev.player.world.playSound(null, ev.player.posX, ev.player.posY, ev.player.posZ, SoundsRegister.heartStonePickup, SoundCategory.PLAYERS, 1.0f, 1.0f);
		ev.getOriginalEntity().setDead();
		ev.pickedUp.setDead();
	}
}
