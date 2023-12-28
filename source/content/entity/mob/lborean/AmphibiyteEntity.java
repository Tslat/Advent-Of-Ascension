package net.tslat.aoa3.content.entity.mob.lborean;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAWaterMeleeMobOld;
import org.jetbrains.annotations.Nullable;


public class AmphibiyteEntity extends AoAWaterMeleeMobOld {
	public AmphibiyteEntity(EntityType<? extends WaterAnimal> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.85f;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.98f;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_AMPHIBIYTE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_AMPHIBIYTE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_AMPHIBIYTE_HURT.get();
	}
}
