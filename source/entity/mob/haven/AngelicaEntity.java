package net.tslat.aoa3.entity.mob.haven;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.ai.mob.RandomFlyingGoal;
import net.tslat.aoa3.entity.ai.movehelper.RoamingFlightMovementController;
import net.tslat.aoa3.entity.base.AoAAnimal;

import javax.annotation.Nullable;

public class AngelicaEntity extends AoAAnimal {
	public AngelicaEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);

		moveControl = new RoamingFlightMovementController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new RandomFlyingGoal(this, true));
	}

	@Override
	protected PathNavigator createNavigation(World world) {
		return new FlyingPathNavigator(this, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.75f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ANGELICA_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ANGELICA_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ANGELICA_HURT.get();
	}

	public void travel(Vector3d travelVector) {
		if (isInWater()) {
			moveRelative(0.02F, travelVector);
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(0.8F));
		}
		else if (isInLava()) {
			moveRelative(0.02F, travelVector);
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(0.5D));
		}
		else {
			BlockPos groundPos = blockPosition().below();
			float friction = 0.91F;

			if (onGround)
				friction = level.getBlockState(groundPos).getSlipperiness(this.level, groundPos, this) * 0.91F;

			float frictionFactor = 0.16277137F / (friction * friction * friction);
			friction = 0.91F;

			if (onGround)
				friction = level.getBlockState(groundPos).getSlipperiness(level, groundPos, this) * 0.91F;

			moveRelative(onGround ? 0.1F * frictionFactor : 0.02F, travelVector);
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(friction));
		}

		calculateEntityAnimation(this, false);
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

	@Override
	public boolean onClimbable() {
		return false;
	}

	@Override
	protected boolean isBreedable() {
		return false;
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public boolean addEffect(EffectInstance effect) {
		return false;
	}

	@Override
	public boolean isIgnoringBlockTriggers() {
		return true;
	}

	@Override
	protected boolean isMovementNoisy() {
		return false;
	}
}
