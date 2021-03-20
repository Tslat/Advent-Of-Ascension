package net.tslat.aoa3.entity.mob.misc;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.HiveKingEntity;

import javax.annotation.Nullable;

public class HiveWorkerEntity extends AoAMeleeMob {
	public HiveWorkerEntity(HiveKingEntity hiveKing) {
		this(AoAEntities.Mobs.HIVE_WORKER.get(), hiveKing.level);

		moveTo(hiveKing.getX(), hiveKing.getY(), hiveKing.getZ(), random.nextFloat() * 360, 1);
	}

	public HiveWorkerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
