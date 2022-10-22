package net.tslat.aoa3.content.entity.mob.precasia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class OpteryxEntity extends AoAMeleeMob<OpteryxEntity> {
	private int jumpCooldown = 70;

	public OpteryxEntity(EntityType<? extends OpteryxEntity> entityType, Level world) {
		super(entityType, world);

		setSpeed(1.8f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.34375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (jumpCooldown <= 0) {
			jumpCooldown = rand().nextInt(100) + 30;
			Vec3 motion = getDeltaMovement();

			setDeltaMovement(motion.x() * 1.2f, 0.8, motion.z() * 1.2);
		}
	}
}
