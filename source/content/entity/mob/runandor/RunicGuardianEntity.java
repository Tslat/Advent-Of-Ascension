package net.tslat.aoa3.content.entity.mob.runandor;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.HeavyRunicGuardianShotEntity;
import net.tslat.aoa3.content.entity.projectile.mob.LightRunicGuardianShotEntity;
import net.tslat.aoa3.content.entity.projectile.mob.RunicGuardianShotEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RunicGuardianEntity extends AoARangedMob<RunicGuardianEntity> {
	public RunicGuardianEntity(EntityType<? extends RunicGuardianEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.75f;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {}

	@Override
	public void performRangedAttack(@Nonnull LivingEntity target, float bowDamageFactor) {
		BaseMobProjectile projectile1 = new LightRunicGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);
		BaseMobProjectile projectile2 = new RunicGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);
		BaseMobProjectile projectile3 = new HeavyRunicGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);

		double distanceFactorX = target.getX() - this.getX();
		double distanceFactorY = target.getBoundingBox().minY + (double)(target.getBbHeight() / 3.0f) - projectile1.getY();
		double distanceFactorZ = target.getZ() - this.getZ();
		double hyp = Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

		if (getShootSound() != null)
			playSound(getShootSound(), 1.0f, 1.0f);

		projectile1.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
		projectile2.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
		projectile3.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
		level().addFreshEntity(projectile1);
		level().addFreshEntity(projectile2);
		level().addFreshEntity(projectile3);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_WIZARD_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return null;
	}
}
