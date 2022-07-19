package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedFindNearbyTargetGoal;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedHurtByTargetGoal;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.RandomUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class KingChargerEntity extends AoAMeleeMob {
	private int nextChargerSpawn = 0;

	public KingChargerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new ExtendedMeleeAttackGoal<>(this).speedModifier(1.125f).attackInterval(ConstantInt.of(getCurrentSwingDuration())).actionTelegraphTicks(getPreAttackTime()).onStart(goal -> setSprinting(true)).onStop(goal -> setSprinting(false)));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 16f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new ExtendedHurtByTargetGoal<>(this).alertNearbyEntities(40, entity -> entity instanceof ChargerEntity));
		targetSelector.addGoal(2, new ExtendedFindNearbyTargetGoal<>(this, true, EntityPredicate.SURVIVAL_PLAYER).searchRadius(10));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.84375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CHARGER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (nextChargerSpawn < tickCount) {
			LivingEntity target = getTarget();

			if (target != null) {
				ChargerEntity charger = EntitySpawningUtil.spawnEntity((ServerLevel)level, AoAMobs.CHARGER.get(), RandomUtil.getRandomPositionWithinRange(target.blockPosition(), 40, 10, 40, 30, 0, 30, true, level, 2, state -> true), MobSpawnType.MOB_SUMMONED);

				if (charger != null)
					charger.setTarget(target);
			}

			nextChargerSpawn = tickCount + RandomUtil.randomNumberBetween(80, 140);
		}
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkRunIdleController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
