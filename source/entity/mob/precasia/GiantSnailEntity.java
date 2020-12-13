package net.tslat.aoa3.entity.mob.precasia;

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
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class GiantSnailEntity extends AoAMeleeMob {
	public GiantSnailEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 80d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25d;
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
	public boolean addPotionEffect(EffectInstance effect) {
		return false;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source.getDamageType().equals("acid") || super.isInvulnerableTo(source);
	}

	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!world.isRemote && world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
			if (world.getBlockState(getPosition().down()).isOpaqueCube(world, getPosition().down()) && world.getBlockState(getPosition()).getMaterial().isReplaceable())
				world.setBlockState(getPosition(), AoABlocks.GIANT_SNAIL_ACID.get().getDefaultState());
		}
	}
}
