package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAFlyingMeleeMob;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nullable;

public class NightflyEntity extends AoAFlyingMeleeMob {
	public NightflyEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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
	public MobType getMobType() {
		return MobType.ARTHROPOD;
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
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericFlyIdleController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_FLYING_ATTACK));
	}
}
