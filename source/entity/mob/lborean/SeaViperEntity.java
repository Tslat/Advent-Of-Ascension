package net.tslat.aoa3.entity.mob.lborean;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAWaterMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class SeaViperEntity extends AoAWaterMeleeMob {
	public SeaViperEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.40625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_SEA_VIPER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SEA_VIPER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SEA_VIPER_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.POISON, 200));
	}

	@Override
	protected int getAttackSwingDuration() {
		return 20;
	}

	@Override
	protected int getPreAttackTime() {
		return 15;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericSwimController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
