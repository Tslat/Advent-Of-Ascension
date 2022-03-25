package net.tslat.aoa3.content.entity.animal;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAAnimal;

import javax.annotation.Nullable;

public class TrotterEntity extends AoAAnimal {
	public TrotterEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);

		setSpeed(1.16f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
