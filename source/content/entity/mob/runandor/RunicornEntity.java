package net.tslat.aoa3.content.entity.mob.runandor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class RunicornEntity extends AoAMeleeMob {

	public RunicornEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		setSpeed(1.6f);
	}

	public RunicornEntity(RunicornRiderEntity rider, float health) {
		this(AoAMobs.RUNICORN.get(), rider.level);

		moveTo(rider.getX(), rider.getY(), rider.getZ(), rider.getYRot(), rider.getXRot());
		setHealth(health);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_RAINICORN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_RAINICORN_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_RAINICORN_HURT.get();
	}
}
