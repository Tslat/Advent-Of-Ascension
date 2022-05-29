package net.tslat.aoa3.content.entity.mob.lunalus;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntityRetrievalUtil;

import javax.annotation.Nullable;

public class RefluctEntity extends AoAMeleeMob {
	public RefluctEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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

		for (Entity e : EntityRetrievalUtil.getEntities(this.level, getBoundingBox().inflate(3), new EntityPredicate<>().isSubtypeOf(BaseEnergyShot.class))) {
			e.setDeltaMovement(e.getDeltaMovement().multiply(-1, -1, -1));
		}
	}
}
