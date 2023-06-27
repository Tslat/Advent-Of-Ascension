package net.tslat.aoa3.util;

import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
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
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class PlayerUtil {
    private static final Int2FloatOpenHashMap XP_MAP = new Int2FloatOpenHashMap(1000);
    private static final Int2FloatOpenHashMap TIME_BASED_XP_MAP = new Int2FloatOpenHashMap(1000);

    static {
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
    public static ServerPlayerDataManager getAdventPlayer(@Nonnull ServerPlayer player) {
        AdventPlayerCapabilityHandles cap = player.getCapability(AdventPlayerCapabilityProvider.CAPABILITY, null).orElseGet(() -> new AdventPlayerCapability(player));

        return cap.getPlayerData();
    }

    @Nonnull
    public static PlayerDataManager getAdventPlayer(@Nonnull Player player) {
        PlayerDataManager plData;

        if (!player.level().isClientSide()) {
            plData = getAdventPlayer((ServerPlayer)player);
        }
        else {
            plData = ClientOperations.CLIENT_PLAYER_DATA;
        }

        if (plData.player() == null)
            plData.updatePlayerInstance(player);

        return plData;
    }

    @Nonnull
    public static AoASkill.Instance getSkill(Player player, AoASkill skill) {
        return getAdventPlayer(player).getSkill(skill);
    }

    public static int getLevel(Player player, AoASkill skill) {
        AoASkill.Instance instance = getSkill(player, skill);

        return instance == AoASkills.DEFAULT ? 0 : instance.getLevel(true);
    }

    public static boolean doesPlayerHaveLevel(Player player, AoASkill skill, int level) {
        return getLevel(player, skill) >= level;
    }

    public static void giveXpToPlayer(ServerPlayer player, AoASkill skill, float xp, boolean ignoreXpBuffs) {
        AoASkill.Instance instance = getSkill(player, skill);

        if (instance != AoASkills.DEFAULT)
            instance.adjustXp(xp, false, ignoreXpBuffs);
    }

    @Nonnull
    public static AoAResource.Instance getResource(Player player, AoAResource resource) {
        return getAdventPlayer(player).getResource(resource);
    }

    public static float getResourceValue(Player player, AoAResource resource) {
        AoAResource.Instance instance = getResource(player, resource);

        return instance == AoAResources.DEFAULT ? 0 : instance.getCurrentValue();
    }

    public static void addResourceToPlayer(ServerPlayer player, AoAResource resource, float amount) {
        AoAResource.Instance instance = getResource(player, resource);

        if (instance != AoAResources.DEFAULT)
            instance.addValue(amount);
    }

    public static boolean consumeResource(ServerPlayer player, AoAResource resource, float amount, boolean forceConsume) {
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

    public static boolean isWearingFullSet(ServerPlayer player, AdventArmour.Type setType) {
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

    public static void notifyPlayer(Player player, Component msg) {
        player.sendSystemMessage(msg);
    }

    public static void notifyPlayerOfInsufficientLevel(ServerPlayer player, AoASkill skill, int level) {
        AoAPackets.messagePlayer(player, new ToastPopupPacket(skill, level));
    }

    public static void notifyPlayerOfInsufficientResources(ServerPlayer player, AoAResource resource, float amount) {
        AoAPackets.messagePlayer(player, new ToastPopupPacket(resource, amount));
    }

    public static void messageAllPlayersInRange(Component msg, Level world, BlockPos center, int radius) {
        for (Player pl : EntityRetrievalUtil.getPlayers(world, new AABB(center).inflate(radius))) {
            pl.sendSystemMessage(msg);
        }
    }

    public static boolean shouldPlayerBeAffected(Player pl) {
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

    public static BlockPos getBlockAimingAt(Player pl, double distance) {
        Vec3 startVec = new Vec3(pl.getX(), pl.getY() + (double)pl.getEyeHeight(), pl.getZ());
        float cosYaw = Mth.cos((float)(-pl.getYRot() * Math.toRadians(1) - Math.PI));
        float sinYaw = Mth.sin((float)(-pl.getYRot() * Math.toRadians(1) - Math.PI));
        float cosPitch = -Mth.cos((float)(-pl.getXRot() * Math.toRadians(1)));
        float sinPitch = Mth.sin((float)(-pl.getXRot() * Math.toRadians(1)));
        float angleX = sinYaw * cosPitch;
        float angleZ = cosYaw * cosPitch;
        Vec3 endVec = startVec.add((double)angleX * distance, (double)sinPitch * distance, (double)angleZ * distance);
        BlockHitResult ray = pl.level().clip(new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, null));

        if (ray.getType() != HitResult.Type.BLOCK)
            return null;

        return ray.getBlockPos();
    }

    public static void playSoundForPlayer(ServerPlayer player, SoundEvent sound, SoundSource category, double posX, double posY, double posZ, float volume, float pitch, long seed) {
        player.connection.send(new ClientboundSoundPacket(Holder.direct(sound), category, posX, posY, posZ, volume, pitch, seed));
    }

    @Nullable
    public static Player getPlayerOrOwnerIfApplicable(@Nullable Entity entity) {
        if (entity == null)
            return null;

        if (entity instanceof Player player)
            return player;

        if (entity instanceof OwnableEntity tameable && tameable.getOwner() instanceof Player player)
            return player;

        if (entity instanceof Projectile projectile && projectile.getOwner() instanceof Player player)
            return player;

        return null;
    }

    public static EquipmentSlot handToEquipmentSlotType(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
    }

    public static GameType getGameMode(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            return serverPlayer.gameMode.getGameModeForPlayer();
        }
        else {
            return ClientOperations.getGameMode();
        }
    }

    public static void resetToDefaultStatus(ServerPlayer player) {
        FoodData foodData = player.getFoodData();

        player.setHealth(player.getMaxHealth());
        player.removeAllEffects();
        player.clearFire();
        player.setTicksFrozen(0);
        player.setSharedFlagOnFire(false);
        player.getCombatTracker().recheckStatus();
        player.setDeltaMovement(Vec3.ZERO);
        foodData.setFoodLevel(20);
        foodData.setSaturation(5);
        foodData.setExhaustion(0);
        PlayerUtil.getAdventPlayer(player).clearCheckpoint();
    }
}
