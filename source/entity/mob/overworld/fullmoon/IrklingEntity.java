package net.tslat.aoa3.entity.mob.overworld.fullmoon;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.event.dimension.OverworldEvents;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IrklingEntity extends AoAMeleeMob {
	private int jumpTimer;

	public IrklingEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		rand.setSeed(entityUniqueID.getMostSignificantBits());
		jumpTimer = rand.nextInt(80) + 40;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.32f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.31;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_IRKLING_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_IRKLING_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_IRKLING_HURT.get();
	}

	@Override
	protected float getJumpUpwardsMotion() {
		return jumpTimer == 0 ? 0.8f : super.getJumpUpwardsMotion();
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!isAlive())
			return;

		fallDistance = -0.5f;

		if (jumpTimer > 0)
			--jumpTimer;

		if (jumpTimer == 0) {
			jump();
			jumpTimer = 80;

			if (getAttackTarget() != null)
				setMotion((getAttackTarget().getPosX() - getPosX()) * 0.0949999988079071, getMotion().getY(), (getAttackTarget().getPosZ() - getPosZ()) * 0.0949999988079071);
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected OverworldEvents.Event getEventRequirement() {
		return OverworldEvents.Event.FULL_MOON;
	}
}
