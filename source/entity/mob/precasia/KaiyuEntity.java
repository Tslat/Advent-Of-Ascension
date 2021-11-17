package net.tslat.aoa3.entity.mob.precasia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.SpiritualShotEntity;

import javax.annotation.Nullable;

public class KaiyuEntity extends AoARangedMob {
	public KaiyuEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 10, 25, 32));
		goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.96875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_KAIYU_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_KAIYU_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_KAIYU_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_KAIYU_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new SpiritualShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}
}
