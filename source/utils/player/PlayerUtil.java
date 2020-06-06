package net.tslat.aoa3.utils.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.packet.PacketToastPopup;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PacketUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerUtil {
    @Nonnull
    public static PlayerDataManager getAdventPlayer(@Nonnull EntityPlayer player) {
        AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

        if (cap == null) {
            cap = new AdventPlayerCapability();

            cap.init(player);
            AdventOfAscension.logMessage(Level.ERROR, "Player: " + player.getName() + " doesn't have an attached AoA capability. Something is very wrong here.");
        }

        if (cap.getPlayerData() == null)
            cap.init(player);

        return cap.getPlayerData();
    }

    public static boolean doesPlayerHaveLevel(EntityPlayer player, Enums.Skills skill, int level) {
        return !ConfigurationUtil.MainConfig.skillsEnabled || getAdventPlayer(player).stats().getLevel(skill) >= level;
    }

    public static void addTributeToPlayer(EntityPlayer player, Enums.Deities deity, int amount) {
        getAdventPlayer(player).stats().addTribute(deity, amount);
    }

    public static void giveXpToPlayer(EntityPlayer player, Enums.Skills skill, float xp) {
        getAdventPlayer(player).stats().addXp(skill, xp, false, false);
    }

    public static void addResourceToPlayer(EntityPlayer player, Enums.Resources resource, float amount) {
        getAdventPlayer(player).stats().regenResource(resource, amount);
    }

    public static boolean consumeResource(EntityPlayer player, Enums.Resources resource, float amount, boolean forceConsume) {
        return getAdventPlayer(player).stats().consumeResource(resource, amount, forceConsume);
    }

    public static int getLevelProgressPercentage(int lvl, float xp) {
        return lvl >= 1000 ? 100 : (int)((xp / getXpRequiredForNextLevel(lvl)) * 100);
    }

    public static float getXpRequiredForNextLevel(int currentLevel) {
        if (currentLevel <= 100) {
            return (float)(Math.pow(1.1, currentLevel) * 50.0);
        }
        else {
            return currentLevel >= 1000 ? 0 : (float)(Math.pow(Math.min(currentLevel, 999) - 10, 2.5) / 100) + 630000;
        }
    }

    public static boolean isWearingFullSet(EntityPlayer player, Enums.ArmourSets setType) {
        return getAdventPlayer(player).equipment().getCurrentFullArmourSet() == setType;
    }

    public static float getXpRemainingUntilLevel(PlayerDataManager plData, Enums.Skills skill) {
        return getXpRequiredForNextLevel(plData.stats().getLevel(skill)) - plData.stats().getExp(skill);
    }

    @Nullable
    public static Enums.Skills getLowestSkillWithLimit(PlayerDataManager plData, final int limit) {
        PlayerDataManager.PlayerStats stats = plData.stats();
        Enums.Skills lowestSkill = null;
        int lowestVal = 1001;

        for (Enums.Skills sk : Enums.Skills.values()) {
            int temp = stats.getLevelForDisplay(sk);

            if (temp >= limit && temp < lowestVal) {
                lowestVal = temp;
                lowestSkill = sk;
            }
        }

        if (lowestVal >= 1000) {
            return null;
        }
        else {
            return lowestSkill;
        }
    }

    @Nonnull
    public static Enums.Skills getHighestSkill(PlayerDataManager plData) {
        PlayerDataManager.PlayerStats stats = plData.stats();
        Enums.Skills highestSkill = null;
        int highestVal = 0;

        for (Enums.Skills sk : Enums.Skills.values()) {
            int lvl = stats.getLevel(sk);

            if (lvl > highestVal) {
                highestSkill = sk;
                highestVal = lvl;
            }
        }

        return highestSkill;
    }

    public static void notifyPlayer(EntityPlayerMP player, String langKey, Object... args) {
        getAdventPlayer(player).sendThrottledChatMessage(langKey, args);
    }

    public static void clonePlayerData(EntityPlayer sourcePlayer, EntityPlayer targetPlayer) {
        getAdventPlayer(targetPlayer).cloneFromExistingPlayerData(getAdventPlayer(sourcePlayer));
    }

    public static void notifyPlayerOfInsufficientLevel(EntityPlayerMP player, Enums.Skills skill, int level) {
        PacketUtil.network.sendTo(new PacketToastPopup(skill, level), player);
    }

    public static void notifyPlayerOfInsufficientResources(EntityPlayerMP player, Enums.Resources resource, float amount) {
        PacketUtil.network.sendTo(new PacketToastPopup(resource, amount), player);
    }

    public static void notifyPlayerOfInsufficientTribute(EntityPlayerMP player, Enums.Deities deity, int tribute) {
        PacketUtil.network.sendTo(new PacketToastPopup(deity, tribute), player);
    }

    public static boolean shouldPlayerBeAffected(EntityPlayer pl) {
        return !pl.isDead && !pl.isSpectator() && !pl.isCreative();
    }

    public static void playSoundForPlayer(EntityPlayerMP player, SoundEvent sound, SoundCategory category, double posX, double posY, double posZ, float volume, float pitch) {
        player.connection.sendPacket(new SPacketSoundEffect(sound, category, posX, posY, posZ, volume, pitch));
    }

    public static int getPlayerLevelFromExp(int xp) {
        if (xp > 1507) {
            return (int)Math.floor((162.5 + Math.sqrt(-162.5 * -162.5 - 4 * 4.5 * (2220 - xp))) / (2 * 4.5));
        }
        else if (xp > 352) {
            return (int)Math.floor((40.5 + Math.sqrt(-40.5 * -40.5 - 4 * 2.5 * (360 - xp))) / (2 * 2.5));
        }
        else {
            return (int)Math.floor((-6 + Math.sqrt(6 * 6 - 4 * -xp)) / 2);
        }
    }

    @Nullable
    public static EntityPlayer getPlayerOrOwnerIfApplicable(@Nullable Entity entity) {
        if (entity == null)
            return null;

        if (entity instanceof EntityPlayer)
            return (EntityPlayer)entity;

        if (entity instanceof EntityTameable) {
            EntityLivingBase owner = ((EntityTameable)entity).getOwner();

            if (owner instanceof EntityPlayer)
                return (EntityPlayer)owner;
        }

        return null;
    }
}
