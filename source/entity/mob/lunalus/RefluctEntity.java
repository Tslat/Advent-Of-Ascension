package net.tslat.aoa3.entity.mob.lunalus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;

import javax.annotation.Nullable;

public class RefluctEntity extends AoAMeleeMob {
	public RefluctEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.84375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_REFLUCT_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_REFLUCT_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_REFLUCT_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		for (Entity e : level.getEntities(this, getBoundingBox().inflate(3), entity -> entity instanceof BaseEnergyShot)) {
			e.setDeltaMovement(e.getDeltaMovement().multiply(-1, -1, -1));
		}
	}
}
