package net.tslat.aoa3.entity.mob.overworld;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class PincherEntity extends AoAMeleeMob {
	public PincherEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1, 10));
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
		return 0;
	}

	@Override
	protected double getBaseArmour() {
		return 4d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.29d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PINCHER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PINCHER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_PINCHER_HURT.get();
	}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public int getMaxSpawnHeight() {
		return 55;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	public void livingTick() {
		super.livingTick();

		Vec3d motion = getMotion();

		if (isInWater() && getAttackTarget() != null && getAttackTarget().getPosY() > getPosY())
			setMotion(motion.getX(), 0.25, motion.getZ());

		motion = getMotion();

		if (isInWater()) {
			if (motion.getX() > -1.100000023841858 && motion.getX() < 1.100000023841858)
				setMotion(motion.mul(1.100000023841858, 1, 1));

			motion = getMotion();

			if (motion.getZ() > -1.100000023841858 && motion.getZ() < 1.100000023841858)
				setMotion(motion.mul(1, 1, 1.100000023841858));
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (!world.isRemote && isInWater() && target.isInWater())
			WorldUtil.createExplosion(this, world, 1.5f);
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.ARTHROPOD;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
