package net.tslat.aoa3.entity.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.AnimaUtil;

public class AnimaStoneEntity extends Entity {
    public AnimaStoneEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);

        setSilent(true);
    }

    public AnimaStoneEntity(World world, BlockPos spawnPosition) {
        this(AoAEntities.Misc.ANIMA_STONE.get(), world);

        setPositionAndUpdate(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
    }

    @Override
    protected float getEyeHeight(Pose pose, EntitySize size) {
        return 0.25f;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerData() {}

    @Override
    public void tick() {
        super.tick();

        if (ticksExisted >= 200) {
            remove();

            return;
        }

        boolean isFalling = getMotion().y <= 0.0D;
        double gravity = 0.08d;

        if (!isInWater()) {
            BlockPos groundPos = getPositionUnderneath();
            float slipperiness = world.getBlockState(groundPos).getSlipperiness(world, groundPos, this);
            float friction = onGround ? slipperiness * 0.91F : 0.91F;

            move(MoverType.SELF, getMotion());

            Vec3d motion = getMotion();
            double motionY = motion.y;

            if (world.isRemote && !world.isBlockLoaded(groundPos)) {
                if (getPosY() > 0.0D) {
                    motionY = -0.1D;
                }
                else {
                    motionY = 0.0D;
                }
            }
            else if (!hasNoGravity()) {
                motionY -= gravity;
            }

            setMotion(motion.x * friction, motionY * 0.98F, motion.z * friction);
        }
        else {
            double initialPosY = getPosY();
            float waterDrag = 0.8f;

            move(MoverType.SELF, getMotion());

            Vec3d motion = getMotion();

            setMotion(motion.mul(waterDrag, 0.8F, waterDrag));

            if (!hasNoGravity()) {
                motion = getMotion();
                double motionY;

                if (isFalling && Math.abs(motion.y - 0.005D) >= 0.003D && Math.abs(motion.y - gravity / 16.0D) < 0.003D) {
                    motionY = -0.003D;
                }
                else {
                    motionY = motion.y - gravity / 16.0D;
                }

                setMotion(motion.x, motionY, motion.z);
            }

            motion = getMotion();

            if (collidedHorizontally && isOffsetPositionInLiquid(motion.x, motion.y + 0.6D - getPosY() + initialPosY, motion.z))
                setMotion(motion.x, 0.3F, motion.z);
        }
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity && isAlive()) {
            PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
            int lvl = plData.stats().getLevel(Skills.ANIMA);

            plData.stats().addXp(Skills.ANIMA, PlayerUtil.getXpRequiredForNextLevel(lvl / AnimaUtil.getExpDenominator(lvl)), false, false);
            world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.HEART_STONE_COLLECT.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
            remove();

            if (rand.nextInt(3) == 0)
                ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.FRAGMENTED_ANIMA_STONE.get(), 1));
        }
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {}

    @Override
    protected void writeAdditional(CompoundNBT compound) {}

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {}
}