package net.tslat.aoa3.content.entity.mob.shyrelands;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class ArcFlowerEntity extends AoAMeleeMob {
    public ArcFlowerEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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

        Player nearestPlayer = level.getNearestPlayer(this, 64);

        if (nearestPlayer == null)
            return;

        if (nearestPlayer.hasLineOfSight(this))
            setDeltaMovement(0, getDeltaMovement().y(), 0);
    }

    @Override
    public void push(double x, double y, double z) {}

    @Override
    public void push(Entity entityIn) {}

    @Override
    public boolean shouldDropExperience() {
        return false;
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);

        if (!level.isClientSide) {
            ArcwormEntity arcworm = new ArcwormEntity(AoAMobs.ARCWORM.get(), level);

            arcworm.moveTo(getX(), getY(), getZ(), getYRot(), getXRot());
            level.addFreshEntity(arcworm);
            discard();
        }
    }
}
