package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraftforge.common.ForgeMod;
import net.tslat.aoa3.common.registration.AoAEntityData;
import net.tslat.aoa3.entity.minion.AoAMinion;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AoAMeleeMob extends MonsterEntity {
	private static final AttributeModifier SLOW_FALLING = new AttributeModifier(UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA"), "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION);
	protected boolean isSlipperyMovement = false;

	protected AoAMeleeMob(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		xpReward = (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ATTACK_DAMAGE) * 2) / 10f);

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	@Override
	protected abstract float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn);

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return null;
	}

	@Nullable
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		if (!blockState.getMaterial().isLiquid()) {
			BlockState state = this.level.getBlockState(pos.above());
			SoundType blockSound = state.getBlock() == Blocks.SNOW ? state.getSoundType(level, pos, this) : blockState.getSoundType(level, pos, this);
			return blockSound.getStepSound();
		}

		return null;
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader world) {
		return AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(getType()) ? 1 : super.getWalkTargetValue(pos, world);
	}

	protected void onAttack(Entity target) {}

	protected void onHit(DamageSource source, float amount) {}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		SoundEvent stepSound = getStepSound(pos, blockIn);

		if (stepSound != null)
			playSound(stepSound, 0.15F, 1.0F);
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		if (super.doHurtTarget(target)) {
			onAttack(target);

			return true;
		}

		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			onHit(source, amount);

			return true;
		}

		return false;
	}

	@Override
	public void travel(Vector3d travelVector) {
		if (isEffectiveAi() || isControlledByLocalInstance()) {
			ModifiableAttributeInstance gravity = getAttribute(ForgeMod.ENTITY_GRAVITY.get());
			boolean isFalling = getDeltaMovement().y <= 0.0D;

			if (isFalling && hasEffect(Effects.SLOW_FALLING)) {
				if (!gravity.hasModifier(SLOW_FALLING)) gravity.addTransientModifier(SLOW_FALLING);
					fallDistance = 0.0F;
			}
			else if (gravity.hasModifier(SLOW_FALLING)) {
				gravity.removeModifier(SLOW_FALLING);
			}

			double gravityValue = gravity.getValue();

			FluidState fluidState = level.getFluidState(blockPosition());

			if (isInWater() && isAffectedByFluids() && !canStandOnFluid(fluidState.getType())) {
				double initialYPos = getY();
				float drag = isSprinting() ? 0.9F : getWaterSlowDown();
				float swimMotionFactor = 0.02F;
				float depthStrider = (float)EnchantmentHelper.getDepthStrider(this);

				if (depthStrider > 3.0F)
					depthStrider = 3.0F;

				if (!onGround)
					depthStrider *= 0.5F;

				if (depthStrider > 0.0F) {
					drag += (0.54600006F - drag) * depthStrider / 3.0F;
					swimMotionFactor += (getSpeed() - swimMotionFactor) * depthStrider / 3.0F;
				}

				if (hasEffect(Effects.DOLPHINS_GRACE))
					drag = 0.96F;

				swimMotionFactor *= (float)getAttribute(ForgeMod.SWIM_SPEED.get()).getValue();

				moveRelative(swimMotionFactor, travelVector);
				move(MoverType.SELF, getDeltaMovement());

				Vector3d vector3d6 = getDeltaMovement();

				if (horizontalCollision && onClimbable())
					vector3d6 = new Vector3d(vector3d6.x, 0.2D, vector3d6.z);

				setDeltaMovement(vector3d6.multiply(drag, 0.8F, drag));

				Vector3d constrainedMotion = getFluidFallingAdjustedMovement(gravityValue, isFalling, getDeltaMovement());

				setDeltaMovement(constrainedMotion);

				if (horizontalCollision && isFree(constrainedMotion.x, constrainedMotion.y + (double)0.6F - getY() + initialYPos, constrainedMotion.z))
					setDeltaMovement(constrainedMotion.x, 0.3F, constrainedMotion.z);
			}
			else if (isInLava() && isAffectedByFluids() && !canStandOnFluid(fluidState.getType())) {
				double originalYPos = getY();
				moveRelative(0.02F, travelVector);
				move(MoverType.SELF, getDeltaMovement());

				if (getFluidHeight(FluidTags.LAVA) <= getFluidJumpThreshold()) {
					setDeltaMovement(getDeltaMovement().multiply(0.5D, 0.8F, 0.5D));

					Vector3d constrainedMotion = getFluidFallingAdjustedMovement(gravityValue, isFalling, getDeltaMovement());

					setDeltaMovement(constrainedMotion);
				}
				else {
					setDeltaMovement(getDeltaMovement().scale(0.5D));
				}

				if (!isNoGravity())
					setDeltaMovement(getDeltaMovement().add(0.0D, -gravityValue / 4.0D, 0.0D));

				Vector3d newMotion = getDeltaMovement();

				if (horizontalCollision && isFree(newMotion.x, newMotion.y + (double)0.6F - getY() + originalYPos, newMotion.z))
					setDeltaMovement(newMotion.x, 0.3F, newMotion.z);
			}
			else if (isFallFlying()) {
				Vector3d curMotion = getDeltaMovement();

				if (curMotion.y > -0.5D)
					fallDistance = 1.0F;

				Vector3d lookVec = getLookAngle();
				float normalizedPitch = xRot * ((float)Math.PI / 180F);
				double lookHypot = Math.sqrt(lookVec.x * lookVec.x + lookVec.z * lookVec.z);
				double lateralMotion = Math.sqrt(getHorizontalDistanceSqr(curMotion));
				double lookVecLength = lookVec.length();
				float pitchRotation = MathHelper.cos(normalizedPitch);
				pitchRotation = (float)((double)pitchRotation * (double)pitchRotation * Math.min(1.0D, lookVecLength / 0.4D));
				curMotion = getDeltaMovement().add(0.0D, gravityValue * (-1.0D + (double)pitchRotation * 0.75D), 0.0D);

				if (curMotion.y < 0.0D && lookHypot > 0.0D) {
					double verticalVelocityAdjust = curMotion.y * -0.1D * (double)pitchRotation;
					curMotion = curMotion.add(lookVec.x * verticalVelocityAdjust / lookHypot, verticalVelocityAdjust, lookVec.z * verticalVelocityAdjust / lookHypot);
				}

				if (normalizedPitch < 0.0F && lookHypot > 0.0D) {
					double lateralVelocityAdjust = lateralMotion * (double)(-MathHelper.sin(normalizedPitch)) * 0.04D;
					curMotion = curMotion.add(-lookVec.x * lateralVelocityAdjust / lookHypot, lateralVelocityAdjust * 3.2D, -lookVec.z * lateralVelocityAdjust / lookHypot);
				}

				if (lookHypot > 0.0D)
					curMotion = curMotion.add((lookVec.x / lookHypot * lateralMotion - curMotion.x) * 0.1D, 0.0D, (lookVec.z / lookHypot * lateralMotion - curMotion.z) * 0.1D);

				setDeltaMovement(curMotion.multiply(0.99F, 0.98F, 0.99F));
				move(MoverType.SELF, getDeltaMovement());

				if (horizontalCollision && !level.isClientSide) {
					double newLateralMotion = Math.sqrt(getHorizontalDistanceSqr(getDeltaMovement()));
					double lateralMotionDiff = lateralMotion - newLateralMotion;
					float lateralCollisionSpeed = (float)(lateralMotionDiff * 10.0D - 3.0D);

					if (lateralCollisionSpeed > 0.0F) {
						playSound(getFallDamageSound((int)lateralCollisionSpeed), 1.0F, 1.0F);
						hurt(DamageSource.FLY_INTO_WALL, lateralCollisionSpeed);
					}
				}

				if (onGround && !level.isClientSide)
					setSharedFlag(7, false);
			}
			else {
				BlockPos standingPosition = getBlockPosBelowThatAffectsMyMovement();
				float blockSlipperiness = level.getBlockState(getBlockPosBelowThatAffectsMyMovement()).getSlipperiness(level, getBlockPosBelowThatAffectsMyMovement(), this);
				float friction = onGround ? blockSlipperiness * 0.91F : 0.91F;
				Vector3d newMotion = handleRelativeFrictionAndCalculateMovement(travelVector, blockSlipperiness);
				double verticalMotion = newMotion.y;

				if (isSlipperyMovement)
					friction *= 0.9f;

				if (hasEffect(Effects.LEVITATION)) {
					verticalMotion += (0.05D * (double)(getEffect(Effects.LEVITATION).getAmplifier() + 1) - newMotion.y) * 0.2D;
					fallDistance = 0.0F;
				}
				else if (level.isClientSide && !level.hasChunkAt(standingPosition)) {
					if (getY() > 0.0D) {
						verticalMotion = -0.1D;
					}
					else {
						verticalMotion = 0.0D;
					}
				}
				else if (!isNoGravity()) {
					verticalMotion -= gravityValue;
				}

				setDeltaMovement(newMotion.x * (double)friction, verticalMotion * (double)0.98F, newMotion.z * (double)friction);
			}
		}

		calculateEntityAnimation(this, this instanceof IFlyingAnimal);
	}
}
