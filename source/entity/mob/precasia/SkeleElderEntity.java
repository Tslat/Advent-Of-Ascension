package net.tslat.aoa3.entity.mob.precasia;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.altar.ArmyBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class SkeleElderEntity extends AoAMeleeMob {
	private BlockPos armyBlockPos;
	private int wave;

	public SkeleElderEntity(World world, BlockPos armyBlockPos, int wave) {
		super(AoAEntities.Mobs.SKELE_ELDER.get(), world);

		this.armyBlockPos = armyBlockPos;
		this.wave = wave;

		restrictTo(armyBlockPos, 18);
	}

	public SkeleElderEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		this.armyBlockPos = null;
		this.wave = -1;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return armyBlockPos == null || wave < 0;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("SkeletalArmyWave"))
			wave = compound.getInt("SkeletalArmyWave");

		if (compound.contains("ArmyBlockPos"))
			armyBlockPos = BlockPos.of(compound.getLong("ArmyBlockPos"));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);

		if (wave > 0)
			compound.putInt("SkeletalArmyWave", wave);

		if (armyBlockPos != null)
			compound.putLong("ArmyBlockPos", armyBlockPos.asLong());
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (wave >= 0 && armyBlockPos != null && distanceToSqr(armyBlockPos.getX(), armyBlockPos.getY(), armyBlockPos.getZ()) < 50 * 50) {
			Block bl = level.getBlockState(armyBlockPos).getBlock();

			if (bl == AoABlocks.ARMY_BLOCK.get())
				ArmyBlock.spawnWave(level, armyBlockPos, wave + 1);
		}
	}
}
