package net.tslat.aoa3.entity.mob.lborean;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAWaterMeleeMob;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class AnglerEntity extends AoAWaterMeleeMob {
	public AnglerEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.8f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ANGLER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ANGLER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ANGLER_HURT.get();
	}

	// TODO Combine open mouth & bite attack, do timings

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericSwimController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
