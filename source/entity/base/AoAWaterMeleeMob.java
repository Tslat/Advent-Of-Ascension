package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntityData;
import net.tslat.aoa3.entity.ai.movehelper.RoamingSwimmingMovementController;
import net.tslat.aoa3.entity.minion.AoAMinion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public abstract class AoAWaterMeleeMob extends WaterMobEntity implements IMob, IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	protected AoAWaterMeleeMob(EntityType<? extends WaterMobEntity> entityType, World world) {
		super(entityType, world);

		this.moveControl = new RoamingSwimmingMovementController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25f, false));
		goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6));
		goalSelector.addGoal(6, new RandomSwimmingGoal(this, 1, 30));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected PathNavigator createNavigation(World world) {
		return new SwimmerPathNavigator(this, world);
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

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {}

	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader world) {
		return AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(getType()) ? 1 : super.getWalkTargetValue(pos, world);
	}

	protected void onAttack(Entity target) {}

	protected void onHit(DamageSource source, float amount) {}

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
	public void aiStep() {
		updateSwingTime();

		super.aiStep();
	}

	@Override
	public void travel(Vector3d motion) {
		if (isEffectiveAi() && isInWater()) {
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(0.9D));

			if (getTarget() == null)
				setDeltaMovement(getDeltaMovement().add(0.0D, -0.001D, 0.0D));
		}
		else {
			super.travel(motion);
		}
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	@Override
	public AnimationFactory getFactory() {
		return this.animationFactory;
	}
}
