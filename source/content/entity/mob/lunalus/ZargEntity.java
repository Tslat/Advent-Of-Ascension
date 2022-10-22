package net.tslat.aoa3.content.entity.mob.lunalus;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class ZargEntity extends AoAMeleeMob<ZargEntity> {
	public ZargEntity(EntityType<? extends ZargEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ZARG_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ZARG_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ZARG_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!level.isClientSide && getTarget() != null && RandomUtil.oneInNChance(350)) {
			FakeZorpEntity fakeZorp = new FakeZorpEntity(getTarget());

			level.addFreshEntity(fakeZorp);
		}
	}
}
