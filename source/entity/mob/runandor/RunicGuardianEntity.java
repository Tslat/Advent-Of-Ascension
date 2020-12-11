package net.tslat.aoa3.entity.mob.runandor;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.HeavyRunicGuardianShotEntity;
import net.tslat.aoa3.entity.projectile.mob.LightRunicGuardianShotEntity;
import net.tslat.aoa3.entity.projectile.mob.RunicGuardianShotEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RunicGuardianEntity extends AoARangedMob {
	public RunicGuardianEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 109;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 13.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

	@Override
	public void attackEntityWithRangedAttack(@Nonnull LivingEntity target, float bowDamageFactor) {
		BaseMobProjectile projectile1 = new LightRunicGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);
		BaseMobProjectile projectile2 = new RunicGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);
		BaseMobProjectile projectile3 = new HeavyRunicGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);

		double distanceFactorX = target.getPosX() - this.getPosX();
		double distanceFactorY = target.getBoundingBox().minY + (double)(target.getHeight() / 3.0f) - projectile1.getPosY();
		double distanceFactorZ = target.getPosZ() - this.getPosZ();
		double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

		if (getShootSound() != null)
			playSound(getShootSound(), 1.0f, 1.0f);

		projectile1.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
		projectile2.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
		projectile3.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
		world.addEntity(projectile1);
		world.addEntity(projectile2);
		world.addEntity(projectile3);
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
