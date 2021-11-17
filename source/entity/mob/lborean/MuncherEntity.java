package net.tslat.aoa3.entity.mob.lborean;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAWaterMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class MuncherEntity extends AoAWaterMeleeMob {
	public MuncherEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		if (EntityUtil.isNaturalSpawnReason(reason)) {
			BlockPos.Mutable spawnPos = new BlockPos.Mutable().set(blockPosition());

			while (!world.getBlockState(spawnPos).getMaterial().blocksMotion() && spawnPos.getY() > 0) {
				spawnPos.move(Direction.DOWN);
			}

			setPos(spawnPos.getX(), spawnPos.getY() + 1, spawnPos.getZ());
		}

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25f, false));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.96875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MUNCHER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MUNCHER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_MUNCHER_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!onGround)
			setDeltaMovement(getDeltaMovement().add(0, -0.005, 0));
	}

	@Override
	protected int getAttackSwingDuration() {
		return 20;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
