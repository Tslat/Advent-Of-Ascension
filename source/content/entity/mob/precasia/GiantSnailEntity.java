package net.tslat.aoa3.content.entity.mob.precasia;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class GiantSnailEntity extends AoAMeleeMob<GiantSnailEntity> {
	public GiantSnailEntity(EntityType<? extends GiantSnailEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GIANT_SNAIL_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GIANT_SNAIL_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GIANT_SNAIL_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_SNAIL_STEP.get();
	}

	@Override
	public boolean addEffect(MobEffectInstance effect, @Nullable Entity source) {
		return false;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source.getMsgId().equals("acid") || super.isInvulnerableTo(source);
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!level.isClientSide && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
			if (level.getBlockState(blockPosition().below()).isSolidRender(level, blockPosition().below()) && level.getBlockState(blockPosition()).getMaterial().isReplaceable())
				level.setBlockAndUpdate(blockPosition(), AoABlocks.GIANT_SNAIL_ACID.get().defaultBlockState());
		}
	}
}
