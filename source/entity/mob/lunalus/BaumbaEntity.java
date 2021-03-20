package net.tslat.aoa3.entity.mob.lunalus;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.BloodballEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class BaumbaEntity extends AoARangedMob {
	public BaumbaEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.78125f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GENERIC_PLANT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GENERIC_PLANT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_BAUMBA_SHOOT.get();
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level, projectile, 2f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 2f);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isAlive() && tickCount % 50 == 0) {
			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_BAUMBA_JUMP.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);

			Vector3d motion = getDeltaMovement();

			setDeltaMovement(motion.x(), 0.5d, motion.z());

			motion = getDeltaMovement();

			if (getTarget() != null)
				setDeltaMovement((getTarget().getX() - getX()) * 0.0649, motion.y(), (getTarget().getZ() - getZ()) * 0.0649);
		}
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new BloodballEntity(this, BaseMobProjectile.Type.MAGIC);
	}
}
