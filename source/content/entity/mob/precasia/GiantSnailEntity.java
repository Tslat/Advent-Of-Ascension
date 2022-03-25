package net.tslat.aoa3.content.entity.mob.precasia;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class GiantSnailEntity extends AoAMeleeMob {
	public GiantSnailEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
	public boolean addEffect(EffectInstance effect) {
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
