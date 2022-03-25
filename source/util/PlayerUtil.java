package net.tslat.aoa3.util;

import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.capability.adventplayer.AdventPlayerCapability;
import net.tslat.aoa3.content.capability.adventplayer.AdventPlayerCapabilityHandles;
import net.tslat.aoa3.content.capability.adventplayer.AdventPlayerCapabilityProvider;
import net.tslat.aoa3.content.item.armour.AdventArmour;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class PlayerUtil {
    private static final Int2FloatOpenHashMap XP_MAP = new Int2FloatOpenHashMap(1000);
    private static final Int2FloatOpenHashMap TIME_BASED_XP_MAP = new Int2FloatOpenHashMap(1000);

    public static void init() {
        for (int i = 1; i <= 99; i++) {
            XP_MAP.put(i, (float)Math.pow(1.1, i) * 50f);
            TIME_BASED_XP_MAP.put(i, XP_MAP.get(i) / (float)(20 + Math.pow(Math.pow(i, 3) / 18000d, 1.75d)));
        }

        for (int i = 100; i <= 999; i++) {
            XP_MAP.put(i, (float)Math.pow(i - 10, 2.5) / 100f + 630000);
            TIME_BASED_XP_MAP.put(i, XP_MAP.get(i) / (1050 + i));
        }
    }

    @Nonnull
    public static ServerPlayerDataManager getAdventPlayer(@Nonnull ServerPlayerEntity player) {
        AdventPlayerCapabilityHandles cap = player.getCapability(AdventPlayerCapabilityProvider.INSTANCE, null).orElseGet(() -> new AdventPlayerCapability((ServerPlayerEntity)player));

        return cap.getPlayerData();
    }

    @Nonnull
    public static PlayerDataManager getAdventPlayer(@Nonnull PlayerEntity player) {
        PlayerDataManager plData;

        if (!player.level.isClientSide()) {
            plData = getAdventPlayer((ServerPlayerEntity)player);
        }
        else {
            plData = ClientOperations.CLIENT_PLAYER_DATA;
        }

        if (plData.player() == null)
            plData.updatePlayerInstance(player);

        return plData;
    }

    @Nonnull
    public static AoASkill.Instance getSkill(PlayerEntity player, AoASkill skill) {
        return getAdventPlayer(player).getSkill(skill);
    }

    public static int getLevel(PlayerEntity player, AoASkill skill) {
        AoASkill.Instance instance = getSkill(player, skill);

        return instance == AoASkills.DEFAULT ? 0 : instance.getLevel(true);
    }

    public static boolean doesPlayerHaveLevel(PlayerEntity player, AoASkill skill, int level) {
        return getLevel(player, skill) >= level;
    }

    public static void giveXpToPlayer(ServerPlayerEntity player, AoASkill skill, float xp, boolean ignoreXpBuffs) {
        AoASkill.Instance instance = getSkill(player, skill);

        if (instance != AoASkills.DEFAULT)
            instance.adjustXp(xp, false, ignoreXpBuffs);
    }

    @Nonnull
    public static AoAResource.Instance getResource(PlayerEntity player, AoAResource resource) {
        return getAdventPlayer(player).getResource(resource);
    }

    public static float getResourceValue(PlayerEntity player, AoAResource resource) {
        AoAResource.Instance instance = getResource(player, resource);

        return instance == AoAResources.DEFAULT ? 0 : instance.getCurrentValue();
    }

    public static void addResourceToPlayer(ServerPlayerEntity player, AoAResource resource, float amount) {
        AoAResource.Instance instance = getResource(player, resource);

        if (instance != AoAResources.DEFAULT)
            instance.addValue(amount);
    }

    public static boolean consumeResource(ServerPlayerEntity player, AoAResource resource, float amount, boolean forceConsume) {
        AoAResource.Instance instance = getResource(player, resource);

        if (instance == AoAResources.DEFAULT)
            return false;

        return instance.consume(amount, forceConsume);
    }

    public static int getLevelProgressPercentage(int lvl, float xp) {
        return lvl >= 1000 ? 100 : (int)((xp / getXpRequiredForNextLevel(lvl)) * 100);
    }

    public static float getXpRequiredForNextLevel(int currentLevel) {
        return XP_MAP.get(currentLevel);
    }

    public static boolean isWearingFullSet(ServerPlayerEntity player, AdventArmour.Type setType) {
        return getAdventPlayer(player).equipment().getCurrentFullArmourSet() == setType;
    }

    public static float getXpRemainingUntilLevel(PlayerDataManager plData, AoASkill skill) {
        AoASkill.Instance instance = plData.getSkill(skill);

       if (instance == AoASkills.DEFAULT)
           return -1;

       return getXpRequiredForNextLevel(instance.getLevel(true)) - instance.getXp();
    }

    public static float getTimeBasedXpForLevel(int currentLevel, float timeIntervalTicks) {
        return TIME_BASED_XP_MAP.get(currentLevel) * (timeIntervalTicks / 100f);
    }

    public static float getXpForFractionOfLevel(int currentLevel, float fraction) {
        return getXpRequiredForNextLevel(currentLevel) * fraction;
    }

    @Nullable
    public static AoASkill getLowestSkillWithLimit(PlayerDataManager plData, final int limit) {
        AoASkill.Instance lowestSkill = null;
        int lowestVal = 1001;

        for (AoASkill.Instance skill : plData.getSkills()) {
            int temp = skill.getLevel(true);

            if (temp >= limit && temp < lowestVal) {
                lowestVal = temp;
                lowestSkill = skill;
            }
        }

        if (lowestVal >= 1000) {
            return null;
        }
        else {
            return lowestSkill.type();
        }
    }

    @Nullable
    public static AoASkill getHighestSkill(PlayerDataManager plData) {
        AoASkill.Instance highestSkill = null;
        int highestVal = 0;

        for (AoASkill.Instance skill : plData.getSkills()) {
            int lvl = skill.getLevel(true);

            if (lvl > highestVal) {
                highestSkill = skill;
                highestVal = lvl;
            }
        }

        return highestSkill == null ? null : highestSkill.type();
    }

    public static void notifyPlayer(PlayerEntity player, ITextComponent msg) {
        player.sendMessage(msg, Util.NIL_UUID);
    }

    public static void notifyPlayerOfInsufficientLevel(ServerPlayerEntity player, AoASkill skill, int level) {
        AoAPackets.messagePlayer(player, new ToastPopupPacket(skill, level));
    }

    public static void notifyPlayerOfInsufficientResources(ServerPlayerEntity player, AoAResource resource, float amount) {
        AoAPackets.messagePlayer(player, new ToastPopupPacket(resource, amount));
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
        float cosYaw = MathHelper.cos((float)(-pl.yRot * Math.toRadians(1) - Math.PI));
        float sinYaw = MathHelper.sin((float)(-pl.yRot * Math.toRadians(1) - Math.PI));
        float cosPitch = -MathHelper.cos((float)(-pl.xRot * Math.toRadians(1)));
        float sinPitch = MathHelper.sin((float)(-pl.xRot * Math.toRadians(1)));
        float angleX = sinYaw * cosPitch;
        float angleZ = cosYaw * cosPitch;
        Vector3d endVec = startVec.add((double)angleX * distance, (double)sinPitch * distance, (double)angleZ * distance);
        BlockRayTraceResult ray = pl.level.clip(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));

        if (ray.getType() != RayTraceResult.Type.BLOCK)
            return null;

        return ray.getBlockPos();
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

        if (entity instanceof ProjectileEntity) {
           Entity owner = ((ProjectileEntity)entity).getOwner();

           if (owner instanceof PlayerEntity)
               return (PlayerEntity)owner;
        }

        return null;
    }

    public static EquipmentSlotType handToEquipmentSlotType(Hand hand) {
        return hand == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND;
    }

    public static GameType getGameMode(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity) {
            return ((ServerPlayerEntity)player).gameMode.getGameModeForPlayer();
        }
        else {
            return ClientOperations.getGameMode();
        }
    }
}
