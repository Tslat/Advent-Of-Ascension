/*
package net.tslat.aoa3.content.entity.mob.misc;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.boss.HiveKingEntity;

import javax.annotation.Nullable;

public class HiveWorkerEntity extends AoAMeleeMob {
	public HiveWorkerEntity(HiveKingEntity hiveKing) {
		this(AoAMobs.HIVE_WORKER.get(), hiveKing.level);

		moveTo(hiveKing.getX(), hiveKing.getY(), hiveKing.getZ(), random.nextFloat() * 360, 1);
	}

	public HiveWorkerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.1625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PARASECT_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PARASECT_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_PARASECT_HURT.get();
	}
}
*/
