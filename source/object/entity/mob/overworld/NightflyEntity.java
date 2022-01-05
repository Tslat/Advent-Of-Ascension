package net.tslat.aoa3.object.entity.mob.overworld;

import net.minecraft.entity.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAFlyingMeleeMob;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class NightflyEntity extends AoAFlyingMeleeMob {
	public NightflyEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_NIGHTFLY_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_NIGHTFLY_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NIGHTFLY_DEATH.get();
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.ARTHROPOD;
	}

	@Override
	public void tick() {
		super.tick();

		if (getRandom().nextBoolean()) {
			setDeltaMovement(getDeltaMovement().multiply(1, 1.25, 1));
		}
		else {
			setDeltaMovement(getDeltaMovement().multiply(1, 0.75, 1));
		}
	}

	@Override
	protected int getAttackSwingDuration() {
		return 24;
	}

	@Override
	protected int getPreAttackTime() {
		return 16;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericFlyIdleController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_FLYING_BITE));
	}
}
