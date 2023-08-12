package net.tslat.aoa3.content.entity.animal;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAAnimalOld;

import javax.annotation.Nullable;

public class TrotterEntity extends AoAAnimalOld {
	public TrotterEntity(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);

		setSpeed(1.16f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_TROTTER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_TROTTER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_TROTTER_HURT.get();
	}

	@Override
	public void tick() {
		super.tick();

		if (tickCount % 140 == 0)
			setDeltaMovement(getDeltaMovement().multiply(1, 1.2, 1));
	}
}
