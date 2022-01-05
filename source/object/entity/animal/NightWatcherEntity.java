package net.tslat.aoa3.object.entity.animal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAAnimal;

import javax.annotation.Nullable;

public class NightWatcherEntity extends AoAAnimal {
	public NightWatcherEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 2.5625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_NIGHT_WATCHER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NIGHT_WATCHER_HURT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_NIGHT_WATCHER_HURT.get();
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader world) {
		return world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) ? 10 : 15 - world.getBrightness(pos) - 0.5F;
	}

	@Override
	protected boolean isBreedable() {
		return false;
	}
}
