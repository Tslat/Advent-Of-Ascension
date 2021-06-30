package net.tslat.aoa3.entity.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.InnervationUtil;

public class HeartStoneEntity extends Entity {
    public HeartStoneEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);

        setSilent(true);
    }

    @Override
    protected float getEyeHeight(Pose pose, EntitySize size) {
        return 0.25f;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundNBT compound) {}

    @Override
    protected void addAdditionalSaveData(CompoundNBT compound) {}

    @Override
    public void tick() {
        super.tick();

        if (tickCount >= 200) {
            remove();

            return;
        }

        boolean isFalling = getDeltaMovement().y <= 0.0D;
        double gravity = 0.08d;

        if (!isInWater()) {
            BlockPos groundPos = getBlockPosBelowThatAffectsMyMovement();
            float slipperiness = level.getBlockState(groundPos).getSlipperiness(level, groundPos, this);
            float friction = onGround ? slipperiness * 0.91F : 0.91F;

            move(MoverType.SELF, getDeltaMovement());

            Vector3d motion = getDeltaMovement();
            double motionY = motion.y;

            if (level.isClientSide && !level.hasChunkAt(groundPos)) {
                if (getY() > 0.0D) {
                    motionY = -0.1D;
                }
                else {
                    motionY = 0.0D;
                }
            }
            else if (!isNoGravity()) {
                motionY -= gravity;
            }

            setDeltaMovement(motion.x * friction, motionY * 0.98F, motion.z * friction);
        }
        else {
            double initialPosY = getY();
            float waterDrag = 0.8f;

            move(MoverType.SELF, getDeltaMovement());

            Vector3d motion = getDeltaMovement();

            setDeltaMovement(motion.multiply(waterDrag, 0.8F, waterDrag));

            if (!isNoGravity()) {
                motion = getDeltaMovement();
                double motionY;

                if (isFalling && Math.abs(motion.y - 0.005D) >= 0.003D && Math.abs(motion.y - gravity / 16.0D) < 0.003D) {
                    motionY = -0.003D;
                }
                else {
                    motionY = motion.y - gravity / 16.0D;
                }

                setDeltaMovement(motion.x, motionY, motion.z);
            }

            motion = getDeltaMovement();

            if (horizontalCollision && isFree(motion.x, motion.y + 0.6D - getY() + initialPosY, motion.z))
                setDeltaMovement(motion.x, 0.3F, motion.z);
        }
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity && isAlive()) {
            PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
            int lvl = plData.stats().getLevelForDisplay(Skills.INNERVATION);

            EntityUtil.healEntity(player, InnervationUtil.getHeartstoneHealAmount(lvl));
            plData.stats().addXp(Skills.INNERVATION, PlayerUtil.getXpRequiredForNextLevel(lvl) / InnervationUtil.getExpDenominator(lvl), false, false);
            level.playSound(null, getX(), getY(), getZ(), AoASounds.HEART_STONE_COLLECT.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
            remove();
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPickable() {
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
