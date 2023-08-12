package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class HorndronEntity extends AoAAnimal<HorndronEntity> {
	public HorndronEntity(EntityType<? extends HorndronEntity> entityType, Level world) {
		super(entityType, world);

		setParts(new AoAEntityPart<>(this, 1, 1f, 0, 0.755f, getBbWidth() / 2f + 0.5f).setDamageMultiplier(1.25f),
				new AoAEntityPart<>(this, 0.75f, 1.1875f, 0.3725f, 0.75f, -getBbWidth() / 2f - 0.3725f).setDamageMultiplier(0.9f),
				new AoAEntityPart<>(this, 0.75f, 1.1875f, -0.3725f, 0.75f, -getBbWidth() / 2f - 0.3725f).setDamageMultiplier(0.9f)
		);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.34375f;
	}

	@Override
	public int getMaxHeadYRot() {
		return 32;
	}

	@Override
	public int getMaxHeadXRot() {
		return 15;
	}

	@Override
	public int getHeadRotSpeed() {
		return 5;
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.POLAR_BEAR_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_HORNDRON_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_HORNDRON_HURT.get();
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this));
	}
}
