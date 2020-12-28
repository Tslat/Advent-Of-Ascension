package net.tslat.aoa3.entity.mob.overworld;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minion.AoAMinion;

import javax.annotation.Nullable;

public class AmphibiyteEntity extends AoAMeleeMob {
	public AmphibiyteEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		if (getNavigator() instanceof GroundPathNavigator)
			getNavigator().setCanSwim(true);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(7, new RandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.85f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 37d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 3.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.28d;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.98f;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_AMPHIBIYTE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_AMPHIBIYTE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_AMPHIBIYTE_HURT.get();
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (isInWater()) {
			if (navigator instanceof GroundPathNavigator)
				navigator = new SwimmerPathNavigator(this, world);

			if (getAttackTarget() != null && getPosY() < getAttackTarget().getPosY())
				setMotion(getMotion().add(0, 0.25f, 0));

			fallDistance = -0.5f;
		}
		else if (navigator instanceof SwimmerPathNavigator) {
			navigator = new GroundPathNavigator(this, world);
		}
	}
}
