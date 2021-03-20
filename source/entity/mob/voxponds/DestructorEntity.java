package net.tslat.aoa3.entity.mob.voxponds;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.BloodballEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class DestructorEntity extends AoARangedMob {
	public DestructorEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		setInvulnerable(true);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0d, 20, 40, 32));
		goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8f));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 7.4375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_DESTRUCTOR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_DESTRUCTOR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DESTRUCTOR_HURT.get();
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level, projectile, 3);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 3);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_DESTRUCTOR_AMBIENT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new BloodballEntity(this, BaseMobProjectile.Type.OTHER);
	}
}
