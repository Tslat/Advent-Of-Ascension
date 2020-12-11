package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class AoARangedMob extends MonsterEntity implements IRangedAttackMob, AoARangedAttacker {
	protected boolean isSlipperyMovement = false;

	protected AoARangedMob(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
		
		experienceValue = (int)(5 + (getBaseMaxHealth() + getBaseArmour() * 1.75f + getBaseProjectileDamage() * 2) / 10f);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 20, 40, 32));
		goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24);
		getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth() * (AoAConfig.COMMON.hardcoreMode.get() ? 2f : 1f));
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getBaseArmour() * (AoAConfig.COMMON.hardcoreMode.get() ? 1.25f : 1f));
	}

	@Override
	protected abstract float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn);

	protected abstract double getBaseKnockbackResistance();

	protected abstract double getBaseMaxHealth();

	public abstract double getBaseProjectileDamage();

	protected abstract double getBaseMovementSpeed();

	protected double getBaseArmour() {
		return 0;
	}

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
	protected abstract SoundEvent getShootSound();

	@Nullable
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return SoundEvents.ENTITY_PIG_STEP;
	}

	protected boolean isOverworldMob() {
		return false;
	}

	protected boolean isDaylightMob() {
		return false;
	}

	protected int getMinSpawnHeight() {
		return 0;
	}

	protected int getMaxSpawnHeight() {
		return 255;
	}

	@Nullable
	protected OverworldEvents.Event getEventRequirement() {
		return null;
	}

	protected abstract BaseMobProjectile getNewProjectileInstance();

	protected void onAttack(Entity target) {}

	protected void onHit(DamageSource source, float amount) {}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			onAttack(target);

			return true;
		}

		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (super.attackEntityFrom(source, amount)) {
			onHit(source, amount);

			return true;
		}

		return false;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		boolean success;
		float mod = AoAConfig.COMMON.hardcoreMode.get() ? 1.5f : 1f;

		switch (projectile.getProjectileType()) {
			case MAGIC:
				success = DamageUtil.dealMagicDamage(projectile, this, target, (float)getBaseProjectileDamage() * mod, false);
				break;
			case GUN:
				success = DamageUtil.dealGunDamage(target, this, projectile, (float)getBaseProjectileDamage() * mod);
				break;
			case PHYSICAL:
				success = DamageUtil.dealRangedDamage(target, this, projectile, (float)getBaseProjectileDamage() * mod);
				break;
			case OTHER:
			default:
				success = target.attackEntityFrom(DamageSource.causeIndirectDamage(projectile, this), (float)getBaseProjectileDamage() * mod);
				break;
		}

		if (success)
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		BaseMobProjectile projectile = getNewProjectileInstance();

		double distanceFactorX = target.getPosX() - getPosX();
		double distanceFactorY = target.getBoundingBox().minY + (double)(target.getHeight() / 3.0f) - projectile.getPosY();
		double distanceFactorZ = target.getPosZ() - getPosZ();
		double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

		if (getShootSound() != null)
			world.playSound(null, getPosX(), getPosY(), getPosZ(), getShootSound(), SoundCategory.HOSTILE, 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - world.getDifficulty().getId()));
		world.addEntity(projectile);
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

					if (isSlipperyMovement)
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

	public static boolean meetsSpawnConditions(EntityType<? extends MonsterEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	public boolean canSpawn(IWorld world, SpawnReason reason) {
		return checkWorldRequirements(reason) && isValidLightLevel(reason) && canSpawnAt(reason, world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnAt(SpawnReason reason, BlockState blockState) {
		if (EntityUtil.isNaturalSpawnReason(reason) && (getPosY() > getMaxSpawnHeight() || getPosY() < getMinSpawnHeight()))
			return false;

		return blockState.canEntitySpawn(world, getPosition(), getType());
	}

	private boolean isValidLightLevel(SpawnReason reason) {
		if (isDaylightMob() && !world.isDaytime())
			return false;

		if (isDaylightMob() || !isOverworldMob())
			return WorldUtil.getLightLevel(world, getPosition(), true, false) <= RandomUtil.randomNumberUpTo(8);

		return isValidLightLevel(world, getPosition(), rand);
	}

	private boolean checkWorldRequirements(SpawnReason reason) {
		if (isOverworldMob() && world.getDimension().getType() != DimensionType.OVERWORLD)
			return false;

		OverworldEvents.Event eventReq = getEventRequirement();

		return eventReq == null || OverworldEvents.isEventActive(eventReq);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		SoundEvent stepSound = getStepSound(pos, blockIn);

		if (stepSound != null)
			playSound(stepSound, 0.15F, 1.0F);
	}
}
