package net.tslat.aoa3.content.entity.mob.candyland;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import org.jetbrains.annotations.Nullable;


public class CandyCornyEntity extends AoAMeleeMob<CandyCornyEntity> {
	public CandyCornyEntity(EntityType<? extends CandyCornyEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.53125f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GENERIC_CANDY_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GENERIC_CANDY_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}
}
