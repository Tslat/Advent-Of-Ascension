package net.tslat.aoa3.content.entity.mob.crystevia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.ConstructShotEntity;

import javax.annotation.Nullable;

public class ConstructOfRangeEntity extends AoARangedMob<ConstructOfRangeEntity> {
	public ConstructOfRangeEntity(EntityType<? extends ConstructOfRangeEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.85f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public void tick() {
		super.tick();

		if (isAlive() && getHealth() < getMaxHealth())
			heal(0.1f);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new ConstructShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}
}
