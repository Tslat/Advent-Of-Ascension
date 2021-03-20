package net.tslat.aoa3.entity.mob.barathos;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class EmperorBeastEntity extends AoAMeleeMob {
	public EmperorBeastEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(3, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 5.3125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_EMPEROR_BEAST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_EMPEROR_BEAST_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_EMPEROR_BEAST_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_EMPEROR_BEAST_STEP.get();
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.CONFUSION, 350).isAmbient());
			DamageUtil.doBodySlamKnockback((LivingEntity)target, this, 21, 1.6f, 21);
		}
	}
}
