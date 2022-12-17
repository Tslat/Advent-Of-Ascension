package net.tslat.aoa3.content.entity.mob.candyland;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class LollypopperEntity extends AoAMeleeMob<LollypopperEntity> {
	public LollypopperEntity(EntityType<? extends LollypopperEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.875f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_LOLLYPOPPER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GENERIC_CANDY_HURT.get();
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide)
			WorldUtil.createExplosion(this, level, 3);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}
}
