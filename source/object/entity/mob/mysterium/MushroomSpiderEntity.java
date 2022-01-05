package net.tslat.aoa3.object.entity.mob.mysterium;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class MushroomSpiderEntity extends AoAMeleeMob {
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>defineId(MushroomSpiderEntity.class, DataSerializers.BYTE);

	public MushroomSpiderEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4f));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 1, true));
		goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CLIMBING, (byte)0);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.59275f;
	}

	@Override
	protected PathNavigator createNavigation(World world) {
		return new ClimberPathNavigator(this, world);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_NIGHTMARE_SPIDER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NIGHTMARE_SPIDER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_NIGHTMARE_SPIDER_HURT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return SoundEvents.SPIDER_STEP;
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.ARTHROPOD;
	}

	@Override
	public void makeStuckInBlock(BlockState state, Vector3d motionMultiplierIn) {
		if (state.getBlock() != Blocks.COBWEB)
			super.makeStuckInBlock(state, motionMultiplierIn);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide)
			setBesideClimbableBlock(this.horizontalCollision);
	}

	@Override
	public boolean onClimbable() {
		return isBesideClimbableBlock();
	}

	public boolean isBesideClimbableBlock() {
		return (this.entityData.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte climbingBit = this.entityData.get(CLIMBING);

		if (climbing) {
			climbingBit = (byte)(climbingBit | 1);
		}
		else {
			climbingBit = (byte)(climbingBit & -2);
		}

		this.entityData.set(CLIMBING, climbingBit);
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.POISON, 80).level(2), new PotionUtil.EffectBuilder(Effects.BLINDNESS, 80));
	}
}
