package net.tslat.aoa3.content.entity.mob.barathos;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

import javax.annotation.Nullable;

public class EmperorBeastEntity extends AoAMeleeMob<EmperorBeastEntity> {
	public EmperorBeastEntity(EntityType<? extends EmperorBeastEntity> entityType, Level world) {
		super(entityType, world);
	}

	/*@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}*/

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
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
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.CONFUSION, 350).isAmbient());
			DamageUtil.doBodySlamKnockback((LivingEntity)target, this, 21, 1.6f, 21);
		}
	}
}
