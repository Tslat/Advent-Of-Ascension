package net.tslat.aoa3.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.library.Enums;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;

public class PlayerUtil {
    @Nonnull
    public static AdventPlayerCapability getAdventPlayer(EntityPlayer player) {
        AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

        if (cap == null) {
            cap = new AdventPlayerCapability();

            cap.addPlayer(player);
            AdventOfAscension.getLogger().log(Level.ERROR, "Player: " + player.getName() + " doesn't have an attached AoA capability. Something is very wrong here.");
        }

        return cap;
    }

    public static boolean doesPlayerHaveLevel(EntityPlayer player, Enums.Skills skill, int level) {
        return getAdventPlayer(player).getLevel(skill) >= level;
    }

    public static void addTributeToPlayer(EntityPlayer player, Enums.Deities deity, int amount) {
        getAdventPlayer(player).addTribute(deity, amount);
    }

    public static void giveXpToPlayer(EntityPlayer player, Enums.Skills skill, float xp) {
        getAdventPlayer(player).addXp(skill, xp, false);
    }

    public static void addResourceToPlayer(EntityPlayer player, Enums.Resources resource, float amount) {
        getAdventPlayer(player).resourceRegen(resource, amount);
    }

    public static boolean consumeResource(EntityPlayer player, Enums.Resources resource, float amount, boolean forceConsume) {
        return getAdventPlayer(player).consumeResource(resource, amount, forceConsume);
    }

    public static int getLevelProgressPercentage(int lvl, float xp) {
        return lvl >= 1000 ? 100 : (int)((xp / getXpRequiredForNextLevel(lvl)) * 100);
    }

    public static float getXpRequiredForNextLevel(int currentLevel) {
        if (currentLevel <= 100) {
            return (float)(Math.pow(1.1, currentLevel) * 50.0);
        }
        else {
            return currentLevel >= 1000 ? 0 : (float)(Math.pow(Math.min(currentLevel, 999) - 10, 2.5) / 100) + 500000;
        }
    }
}
