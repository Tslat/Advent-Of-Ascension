package net.tslat.aoa3.content.entity.mob.lunalus;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.BloodballEntity;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class BaumbaEntity extends AoARangedMob<BaumbaEntity> {
	public BaumbaEntity(EntityType<? extends BaumbaEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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
	public void onProjectileAttack(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level(), projectile, 2f);
	}

	@Override
	public void doRangedAttackBlock(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level(), projectile, 2f);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isAlive() && tickCount % 50 == 0) {
			level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_BAUMBA_JUMP.get(), SoundSource.HOSTILE, 1.0f, 1.0f);

			Vec3 motion = getDeltaMovement();

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
