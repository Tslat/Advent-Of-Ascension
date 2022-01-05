package net.tslat.aoa3.object.entity.mob.lunalus;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class ZargEntity extends AoAMeleeMob {
	public ZargEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
