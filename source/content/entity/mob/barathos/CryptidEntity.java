package net.tslat.aoa3.content.entity.mob.barathos;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class CryptidEntity extends AoAMeleeMob<CryptidEntity> {
	public CryptidEntity(EntityType<? extends CryptidEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.75f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CRYPTID_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CRYPTID_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CRYPTID_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (level.getBlockState(blockPosition().below()).getBlock() == AoABlocks.HELLSTONE.get() && level.getBlockState(blockPosition()).getMaterial().isReplaceable())
			level.setBlockAndUpdate(blockPosition(), Blocks.FIRE.defaultBlockState());
	}
}
