package net.tslat.aoa3.content.entity.mob.barathos;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class SquigglerEntity extends AoAMeleeMob<SquigglerEntity> {
	public SquigglerEntity(EntityType<? extends SquigglerEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.4375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_SQUIGGLER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SQUIGGLER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SQUIGGLER_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (getTarget() != null && getTarget().distanceTo(this) < 20 && getTarget().hasLineOfSight(this))
			getTarget().setSecondsOnFire(8);
	}
}
