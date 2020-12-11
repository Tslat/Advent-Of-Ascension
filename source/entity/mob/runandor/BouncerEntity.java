package net.tslat.aoa3.entity.mob.runandor;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class BouncerEntity extends AoAMeleeMob {
	private int jumpCooldown;

	public BouncerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		rand.setSeed(getUniqueID().getMostSignificantBits());
		jumpCooldown = RandomUtil.randomNumberBetween(40, 120);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 110;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_BOUNCER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_BOUNCER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_BOUNCER_HURT.get();
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	protected float getJumpUpwardsMotion() {
		return jumpCooldown == 0 ? 1.2f : super.getJumpUpwardsMotion();
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!isAlive())
			return;

		if (jumpCooldown > 0) {
			jumpCooldown--;
		}
		else {
			jump();
			jumpCooldown = 70;
		}
	}
}
