package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.ai.mob.FlyingLookRandomlyGoal;
import net.tslat.aoa3.entity.ai.mob.FlyingMeleeAttackGoal;
import net.tslat.aoa3.entity.ai.mob.RandomFlyingGoal;
import net.tslat.aoa3.entity.ai.movehelper.RoamingFlightMoveHelper;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class AoAFlyingMeleeMob extends FlyingEntity implements IMob {
	protected boolean isSlipperyMovement = false;

	protected AoAFlyingMeleeMob(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);

		moveController = new RoamingFlightMoveHelper(this);
		experienceValue = (int)(5 + (getBaseMaxHealth() + getBaseArmour() * 1.75f + getBaseMeleeDamage() * 2) / 10f);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new RandomFlyingGoal(this, true));
		goalSelector.addGoal(2, new FlyingLookRandomlyGoal(this));
		goalSelector.addGoal(3, new FlyingMeleeAttackGoal(this, 0.6f, false));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, 10, true, true, entity -> entity instanceof AoAMinion && ((AoAMinion)entity).isTamed()));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, true, pl -> pl instanceof PlayerEntity && PlayerUtil.shouldPlayerBeAffected((PlayerEntity)pl)));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getBaseMeleeDamage() * (AoAConfig.COMMON.hardcoreMode.get() ? 1.5f : 1f));
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24);
		getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth() * (AoAConfig.COMMON.hardcoreMode.get() ? 2f : 1f));
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getBaseArmour() * (AoAConfig.COMMON.hardcoreMode.get() ? 1.25f : 1f));
	}

	@Override
	protected PathNavigator createNavigator(World world) {
		return new FlyingPathNavigator(this, world);
	}

	@Override
	protected abstract float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn);

	protected abstract double getBaseKnockbackResistance();

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMeleeDamage();

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
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
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

		if (world.getLightFor(LightType.SKY, getPosition()) > rand.nextInt(32))
			return false;

		int light = world.isThundering() ? world.getNeighborAwareLightSubtracted(getPosition(), 10) : (int)world.getBrightness(getPosition()) * 15;

		return light <= rand.nextInt(8);
	}

	private boolean checkWorldRequirements(SpawnReason reason) {
		if (isOverworldMob() && world.getDimension().getType() != DimensionType.OVERWORLD)
			return false;

		OverworldEvents.Event eventReq = getEventRequirement();

		return eventReq == null || OverworldEvents.isEventActive(eventReq);
	}

	@Override
	protected boolean isDespawnPeaceful() {
		return true;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {}
}
