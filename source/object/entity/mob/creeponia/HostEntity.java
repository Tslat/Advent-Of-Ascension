package net.tslat.aoa3.object.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class HostEntity extends AoAMeleeMob {
	public HostEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(3, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.85f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_HOST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_HOST_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_HOST_AMBIENT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		Vector3d motion = getDeltaMovement();
		double motionY = motion.y();

		if (hasImpulse && motionY < 0)
			motionY *= 0.8;

		if ((getTarget() == null ? RandomUtil.oneInNChance(100) : RandomUtil.oneInNChance(10)))
			motionY += 0.3;

		setDeltaMovement(motion.x(), motionY, motion.z());

		if (!level.isClientSide && getTarget() != null && RandomUtil.oneInNChance(80)) {
			CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, level);

			creeper.moveTo(getX(), getY(), getZ(), random.nextFloat() * 360f, 0.0f);
			level.addFreshEntity(creeper);
			playSound(AoASounds.ENTITY_HOST_SUMMON.get(), 1.0f, 1.0f);
		}
	}

}
