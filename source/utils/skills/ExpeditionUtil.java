package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class ExpeditionUtil {
    public static void handleFallEvent(LivingFallEvent ev, PlayerDataManager plData) {
        if (ev.isCanceled())
            return;

        int lvl = plData.stats().getLevel(Enums.Skills.EXPEDITION);

        if (lvl >= 10) {
            if (lvl < 20) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.95f);
            }
            else if (lvl < 30) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.90f);
            }
            else if (lvl < 40) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.85f);
            }
            else if (lvl < 50) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.80f);
            }
            else if (lvl < 60) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.70f);
            }
            else if (lvl < 70) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.60f);
            }
            else if (lvl < 80) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.50f);
            }
            else if (lvl < 90) {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.40f);
            }
            else {
                ev.setDamageMultiplier(ev.getDamageMultiplier() * 0.30f);
            }
        }
    }

    public static void handleRunningTick(TickEvent.PlayerTickEvent ev, EntityPlayer pl) {
    	PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
		int lvl = plData.stats().getLevel(Enums.Skills.EXPEDITION);

		if (ev.player.isSprinting() && ev.player.ticksExisted % 140 == 0)
			plData.stats().addXp(Enums.Skills.EXPEDITION, PlayerUtil.getXpRequiredForNextLevel(lvl) / getXpDenominator(lvl), false, false);

		switch (plData.stats().getSkillData(Enums.Skills.EXPEDITION) % 4) {
			case 1:
				if (ev.player.isSprinting() && ev.player.ticksExisted % 600 == 0) {
					if (AdventOfAscension.rand.nextInt(110) < lvl && !ev.player.isPotionActive(MobEffects.SPEED)) {
						ev.player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 80, 4, true, false));
						plData.sendThrottledChatMessage("message.event.expedition.speed", TextFormatting.AQUA);
					}
				}
				break;
			case 2:
				if (ev.player.isSprinting() && ev.player.ticksExisted % 600 == 0) {
					if (AdventOfAscension.rand.nextInt(110) < lvl) {
						ev.player.getFoodStats().addStats(5, 0.3f);
						plData.sendThrottledChatMessage("message.event.expedition.food", TextFormatting.AQUA);
					}
				}
				break;
			case 3:
				if (ev.player.isInWater() && ev.player.ticksExisted % 500 == 0) {
					if (AdventOfAscension.rand.nextInt(110) < lvl) {
						ev.player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 400, 0, true, false));
						plData.sendThrottledChatMessage("message.event.expedition.breath", TextFormatting.AQUA);
					}
				}
				break;
			default:
				break;
		}
    }

    public static int getXpDenominator(final int lvl) {
        if (lvl <= 4)
            return 22;

        if (lvl <= 9)
            return 35;

        if (lvl <= 14)
            return 45;

        if (lvl <= 19)
            return 65;

        if (lvl <= 24)
            return 90;

        if (lvl <= 34)
            return 150;

        if (lvl <= 39)
            return 190;

        if (lvl <= 44)
            return 230;

        if (lvl <= 49)
            return 280;

        if (lvl <= 54)
            return 360;

        if (lvl <= 59)
            return 450;

        if (lvl <= 64)
            return 540;

        if (lvl <= 69)
            return 650;

        if (lvl <= 74)
            return 750;

        if (lvl <= 79)
            return 850;

        if (lvl <= 84)
            return 950;

        if (lvl <= 89)
            return 1000;

        if (lvl <= 94)
            return 1100;

        if (lvl <= 98)
            return 1200;

        return 1300;
    }
}
