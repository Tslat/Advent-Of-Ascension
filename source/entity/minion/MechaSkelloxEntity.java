package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class MechaSkelloxEntity extends AoAMinion {
	public MechaSkelloxEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world);

		this.setSpeed(3.2f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.09375f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(3, new LookRandomlyGoal(this));
		goalSelector.addGoal(4, new FollowOwnerGoal(this, 1, 30, 2, true));
		goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8));

		if (isHostile()) {
			if (getAttributeValue(Attributes.ATTACK_DAMAGE) >= 0) {
				goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.5f, true));
				goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3f));
			}

			targetSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, 0, false, false, EntityUtil.Predicates.HOSTILE_MOB));
			targetSelector.addGoal(2, new HurtByTargetGoal(this));
			targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
			targetSelector.addGoal(4, new OwnerHurtTargetGoal(this));
		}
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MECHYON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_MECHYON_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MECHYON_DEATH.get();
	}
}
