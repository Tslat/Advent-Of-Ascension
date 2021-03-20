package net.tslat.aoa3.entity.mob.abyss;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class DistorterEntity extends AoAMeleeMob {
	private int effectTick = 60;

	public DistorterEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 15f));
		goalSelector.addGoal(2, new LookRandomlyGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.675f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_DISTORTER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_DISTORTER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DISTORTER_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		effectTick--;

		if (tickCount % 5 == 0) {
			Effect currentEffect = effectTick <= 30 ? Effects.MOVEMENT_SLOWDOWN : Effects.MOVEMENT_SPEED;

			for (PlayerEntity pl : level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(2), pl -> pl != null && !pl.isSpectator() && !pl.isCreative() && canSee(pl))) {
				EntityUtil.applyPotions(pl, new PotionUtil.EffectBuilder(currentEffect, 5).level(6).hideParticles()); // TODO custom effect?
			}
		}

		if (effectTick <= 0)
			effectTick = 60;
	}
}
