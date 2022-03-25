package net.tslat.aoa3.content.entity.mob.barathos;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class CryptidEntity extends AoAMeleeMob {
	public CryptidEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
