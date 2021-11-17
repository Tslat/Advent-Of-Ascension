package net.tslat.aoa3.entity.mob.shyrelands;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class ArcFlowerEntity extends AoAMeleeMob {
    public ArcFlowerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
        goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.05f;
    }

    @Nullable
    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return null;
    }

	@Override
    protected void jumpFromGround() {}

    @Override
    public void aiStep() {
        super.aiStep();

        PlayerEntity nearestPlayer = level.getNearestPlayer(this, 64);

        if (nearestPlayer == null)
            return;

        if (nearestPlayer.canSee(this))
            setDeltaMovement(0, getDeltaMovement().y(), 0);
    }

    @Override
    public void push(double x, double y, double z) {}

    @Override
    public void push(Entity entityIn) {}

    @Override
    protected boolean shouldDropExperience() {
        return false;
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);

        if (!level.isClientSide) {
            ArcwormEntity arcworm = new ArcwormEntity(AoAEntities.Mobs.ARCWORM.get(), level);

            arcworm.moveTo(getX(), getY(), getZ(), yRot, xRot);
            level.addFreshEntity(arcworm);
            remove();
        }
    }
}
