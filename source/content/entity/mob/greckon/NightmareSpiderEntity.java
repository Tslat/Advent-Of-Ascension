package net.tslat.aoa3.content.entity.mob.greckon;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

import javax.annotation.Nullable;

public class NightmareSpiderEntity extends AoAMeleeMob {
	private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.<Byte>defineId(NightmareSpiderEntity.class, EntityDataSerializers.BYTE);

	public NightmareSpiderEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	/*@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4f));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 1, true));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
	}*/

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(CLIMBING, (byte)0);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new WallClimberNavigation(this, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.59275f;
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
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	@Override
	public void makeStuckInBlock(BlockState state, Vec3 motionMultiplierIn) {
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
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.CONFUSION, 10), new EffectBuilder(MobEffects.BLINDNESS, 80));
	}
}
