package net.tslat.aoa3.content.entity.mob.precasia;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.block.functional.altar.ArmyBlock;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class SkeleElderEntity extends AoAMeleeMob<SkeleElderEntity> {
	private BlockPos armyBlockPos;
	private int wave;

	public SkeleElderEntity(Level world, BlockPos armyBlockPos, int wave) {
		super(AoAMobs.SKELE_ELDER.get(), world);

		this.armyBlockPos = armyBlockPos;
		this.wave = wave;

		restrictTo(armyBlockPos, 18);
	}

	public SkeleElderEntity(EntityType<? extends SkeleElderEntity> entityType, Level world) {
		super(entityType, world);

		this.armyBlockPos = null;
		this.wave = -1;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return armyBlockPos == null || wave < 0;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 2.34375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SKELETON_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SKELETON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.SKELETON_HURT;
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("SkeletalArmyWave"))
			wave = compound.getInt("SkeletalArmyWave");

		if (compound.contains("ArmyBlockPos"))
			armyBlockPos = BlockPos.of(compound.getLong("ArmyBlockPos"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		if (wave > 0)
			compound.putInt("SkeletalArmyWave", wave);

		if (armyBlockPos != null)
			compound.putLong("ArmyBlockPos", armyBlockPos.asLong());
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);

		if (wave >= 0 && armyBlockPos != null && distanceToSqr(armyBlockPos.getX(), armyBlockPos.getY(), armyBlockPos.getZ()) < 50 * 50) {
			Block bl = level.getBlockState(armyBlockPos).getBlock();

			if (bl == AoABlocks.ARMY_BLOCK.get())
				ArmyBlock.spawnWave(level, armyBlockPos, wave + 1);
		}
	}
}
