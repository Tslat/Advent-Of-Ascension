package net.tslat.aoa3.util.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.adventplayer.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.adventplayer.AdventPlayerCapabilityHandles;
import net.tslat.aoa3.capabilities.adventplayer.AdventPlayerCapabilityProvider;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerUtil {
    @Nonnull
    public static PlayerDataManager getAdventPlayer(@Nonnull ServerPlayerEntity player) {
        AdventPlayerCapabilityHandles cap = player.getCapability(AdventPlayerCapabilityProvider.INSTANCE, null).orElse(new AdventPlayerCapability(player));

        return cap.getPlayerData();
    }

    public static boolean doesPlayerHaveLevel(ServerPlayerEntity player, Skills skill, int level) {
        return !AoAConfig.COMMON.skillsEnabled.get() || getAdventPlayer(player).stats().getLevel(skill) >= level;
    }

    public static void addTributeToPlayer(ServerPlayerEntity player, Deities deity, int amount) {
        getAdventPlayer(player).stats().addTribute(deity, amount);
    }

    public static void giveXpToPlayer(ServerPlayerEntity player, Skills skill, float xp) {
        getAdventPlayer(player).stats().addXp(skill, xp, false, false);
    }

    public static void addResourceToPlayer(ServerPlayerEntity player, Resources resource, float amount) {
        getAdventPlayer(player).stats().regenResource(resource, amount);
    }

    public static boolean consumeResource(ServerPlayerEntity player, Resources resource, float amount, boolean forceConsume) {
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
            return currentLevel >= 1000 ? 0 : (float)(Math.pow(currentLevel - 10, 2.5) / 100) + 630000;
        }
    }

    public static boolean isWearingFullSet(ServerPlayerEntity player, AdventArmour.Type setType) {
        return getAdventPlayer(player).equipment().getCurrentFullArmourSet() == setType;
    }

    public static float getXpRemainingUntilLevel(PlayerDataManager plData, Skills skill) {
        return getXpRequiredForNextLevel(plData.stats().getLevel(skill)) - plData.stats().getExp(skill);
    }

    @Nullable
    public static Skills getLowestSkillWithLimit(PlayerDataManager plData, final int limit) {
        PlayerDataManager.PlayerStats stats = plData.stats();
        Skills lowestSkill = null;
        int lowestVal = 1001;

        for (Skills sk : Skills.values()) {
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
    public static Skills getHighestSkill(PlayerDataManager plData) {
        PlayerDataManager.PlayerStats stats = plData.stats();
        Skills highestSkill = null;
        int highestVal = 0;

        for (Skills sk : Skills.values()) {
            int lvl = stats.getLevel(sk);

            if (lvl > highestVal) {
                highestSkill = sk;
                highestVal = lvl;
            }
        }

        return highestSkill;
    }

    public static void notifyPlayer(ServerPlayerEntity player, String langKey, Object... args) {
        getAdventPlayer(player).sendThrottledChatMessage(langKey, args);
    }

    public static void clonePlayerData(ServerPlayerEntity sourcePlayer, ServerPlayerEntity targetPlayer) {
        getAdventPlayer(targetPlayer).cloneFromExistingPlayerData(getAdventPlayer(sourcePlayer));
    }

    public static void notifyPlayerOfInsufficientLevel(ServerPlayerEntity player, Skills skill, int level) {
        AoAPackets.messagePlayer(player, new ToastPopupPacket(skill, level));
    }

    public static void notifyPlayerOfInsufficientResources(ServerPlayerEntity player, Resources resource, float amount) {
        AoAPackets.messagePlayer(player, new ToastPopupPacket(resource, amount));
    }

    public static void notifyPlayerOfInsufficientTribute(ServerPlayerEntity player, Deities deity, int tribute) {
        AoAPackets.messagePlayer(player, new ToastPopupPacket(deity, tribute));
    }

    public static void messageAllPlayersInRange(ITextComponent msg, World world, BlockPos center, int radius) {
        for (PlayerEntity pl : WorldUtil.getAllPlayersInRegion(world, new AxisAlignedBB(center).inflate(radius))) {
            pl.sendMessage(msg, Util.NIL_UUID);
        }
    }

    public static boolean shouldPlayerBeAffected(PlayerEntity pl) {
        return pl.isAlive() && !pl.isSpectator() && !pl.isCreative();
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

    public static BlockPos getBlockAimingAt(PlayerEntity pl, double distance) {
        Vector3d startVec = new Vector3d(pl.getX(), pl.getY() + (double)pl.getEyeHeight(), pl.getZ());
        float cosYaw = MathHelper.cos(-pl.yRot * 0.017453292F - (float)Math.PI);
        float sinYaw = MathHelper.sin(-pl.yRot * 0.017453292F - (float)Math.PI);
        float cosPitch = -MathHelper.cos(-pl.xRot * 0.017453292F);
        float sinPitch = MathHelper.sin(-pl.xRot * 0.017453292F);
        float angleX = sinYaw * cosPitch;
        float angleZ = cosYaw * cosPitch;
        Vector3d endVec = startVec.add((double)angleX * distance, (double)sinPitch * distance, (double)angleZ * distance);
        BlockRayTraceResult ray = pl.level.clip(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));

        if (ray.getType() != RayTraceResult.Type.BLOCK)
            return null;

        return  ray.getBlockPos();
    }

    public static void playSoundForPlayer(ServerPlayerEntity player, SoundEvent sound, SoundCategory category, double posX, double posY, double posZ, float volume, float pitch) {
        player.connection.send(new SPlaySoundEffectPacket(sound, category, posX, posY, posZ, volume, pitch));
    }

    @Nullable
    public static PlayerEntity getPlayerOrOwnerIfApplicable(@Nullable Entity entity) {
        if (entity == null)
            return null;

        if (entity instanceof PlayerEntity)
            return (PlayerEntity)entity;

        if (entity instanceof TameableEntity) {
            LivingEntity owner = ((TameableEntity)entity).getOwner();

            if (owner instanceof PlayerEntity)
                return (PlayerEntity)owner;
        }

        return null;
    }

    public static EquipmentSlotType handToEquipmentSlotType(Hand hand) {
        return hand == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND;
    }
}
