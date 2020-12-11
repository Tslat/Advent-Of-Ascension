package net.tslat.aoa3.entity.minion;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class MechaSkelloxEntity extends AoAMinion {
	public MechaSkelloxEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world);

		this.setAIMoveSpeed(3.2f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.09375f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(3, new LookRandomlyGoal(this));
		goalSelector.addGoal(4, new FollowOwnerGoal(this, 1, 30, 2, true));
		goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8));

		if (isHostile()) {
			if (getBaseMeleeDamage() >= 0) {
				goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.5f, true));
				goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3f));
			}

			targetSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, 0, false, false, EntityUtil.Predicates.HOSTILE_MOB));
			targetSelector.addGoal(2, new HurtByTargetGoal(this));
			targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
			targetSelector.addGoal(4, new OwnerHurtTargetGoal(this));
		}
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.72;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 160.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15.0d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MECHYON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_MECHYON_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MECHYON_DEATH.get();
	}

	@Override
	public void travel(Vec3d motionVec) {
		if (isServerWorld() || canPassengerSteer()) {
			double gravityValue;
			IAttributeInstance gravity = getAttribute(ENTITY_GRAVITY);
			boolean falling = getMotion().y <= 0;
			Vec3d currentMotion;

			if (falling && isPotionActive(Effects.SLOW_FALLING)) {
				if (!gravity.hasModifier(EntityUtil.SLOW_FALLING))
					gravity.applyModifier(EntityUtil.SLOW_FALLING);

				fallDistance = 0;
			}
			else if (gravity.hasModifier(EntityUtil.SLOW_FALLING)) {
				gravity.removeModifier(EntityUtil.SLOW_FALLING);
			}

			gravityValue = gravity.getValue();

			if (!isInWater()) {
				if (!isInLava()) {
					BlockPos feetPos = getPositionUnderneath();
					float blockSlip = world.getBlockState(feetPos).getSlipperiness(world, feetPos, this);
					float friction = onGround ? blockSlip * 0.91F : 0.91F;
					float moveFactor = onGround ? getAIMoveSpeed() * (0.21600002F / (blockSlip * blockSlip * blockSlip)) : jumpMovementFactor;

					friction *= 0.9;

					moveRelative(moveFactor, motionVec);

					currentMotion = getMotion();

					if (isOnLadder()) {
						fallDistance = 0.0F;
						currentMotion = new Vec3d(MathHelper.clamp(currentMotion.x, -0.15F, 0.15F), Math.max(currentMotion.y, -0.15F), MathHelper.clamp(currentMotion.z, -0.15F, 0.15F));
					}

					setMotion(currentMotion);
					move(MoverType.SELF, getMotion());

					currentMotion = getMotion();

					if ((collidedHorizontally || isJumping) && isOnLadder())
						currentMotion = new Vec3d(currentMotion.x, 0.2D, currentMotion.z);

					double yVelocity = currentMotion.y;

					if (isPotionActive(Effects.LEVITATION)) {
						yVelocity += (0.05D * (double)(getActivePotionEffect(Effects.LEVITATION).getAmplifier() + 1) - currentMotion.y) * 0.2D;
						fallDistance = 0.0F;
					}
					else if (world.isRemote && !world.isBlockLoaded(feetPos)) {
						yVelocity = getPosY() > 0 ? -0.1 : 0;
					}
					else if (!hasNoGravity()) {
						yVelocity -= gravityValue;
					}

					setMotion(currentMotion.x * (double)friction, yVelocity * (double)0.98F, currentMotion.z * (double)friction);
				}
				else {
					moveRelative(0.02F, motionVec);
					move(MoverType.SELF, getMotion());
					setMotion(getMotion().scale(0.5D));

					if (!hasNoGravity())
						setMotion(getMotion().add(0.0D, -gravityValue / 4.0D, 0.0D));

					currentMotion = getMotion();

					if (collidedHorizontally && isOffsetPositionInLiquid(currentMotion.x, currentMotion.y + (double)0.6F - getPosY() + getPosY(), currentMotion.z))
						setMotion(currentMotion.x, 0.3F, currentMotion.z);
				}
			}
			else {
				float drag = isSprinting() ? 0.9F : getWaterSlowDown();
				float swimSpeed = 0.02F;
				float depthStrider = (float)EnchantmentHelper.getDepthStriderModifier(this);

				if (depthStrider > 3.0F)
					depthStrider = 3.0F;

				if (!onGround)
					depthStrider *= 0.5F;

				if (depthStrider > 0.0F) {
					drag += (0.54600006F - drag) * depthStrider / 3.0F;
					swimSpeed += (getAIMoveSpeed() - swimSpeed) * depthStrider / 3.0F;
				}

				if (isPotionActive(Effects.DOLPHINS_GRACE))
					drag = 0.96F;

				swimSpeed *= (float)getAttribute(SWIM_SPEED).getValue();

				moveRelative(swimSpeed, motionVec);
				move(MoverType.SELF, getMotion());

				currentMotion = getMotion();

				if (collidedHorizontally && isOnLadder())
					currentMotion = new Vec3d(currentMotion.x, 0.2D, currentMotion.z);

				setMotion(currentMotion.mul(drag, 0.8F, drag));

				if (!hasNoGravity() && !isSprinting()) {
					currentMotion = getMotion();
					double motionY;

					if (falling && Math.abs(currentMotion.y - 0.005D) >= 0.003D && Math.abs(currentMotion.y - gravityValue / 16.0D) < 0.003D) {
						motionY = -0.003D;
					}
					else {
						motionY = currentMotion.y - gravityValue / 16.0D;
					}

					setMotion(currentMotion.x, motionY, currentMotion.z);
				}

				currentMotion = getMotion();

				if (collidedHorizontally && isOffsetPositionInLiquid(currentMotion.x, currentMotion.y + (double)0.6F - getPosY() + getPosY(), currentMotion.z))
					setMotion(currentMotion.x, 0.3F, currentMotion.z);
			}
		}

		prevLimbSwingAmount = limbSwingAmount;
		double travelledX = getPosX() - prevPosX;
		double travelledZ = getPosZ() - prevPosZ;
		float travel = MathHelper.sqrt(travelledX * travelledX + travelledZ * travelledZ) * 4.0F;

		if (travel > 1.0F)
			travel = 1.0F;

		limbSwingAmount += (travel - limbSwingAmount) * 0.4F;
		limbSwing += limbSwingAmount;
	}
}
