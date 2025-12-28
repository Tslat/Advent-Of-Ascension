package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.util.EntityRetrievalUtil;

public class SunShotEntity extends NonPhysicalWeaponProjectile {
	public SunShotEntity(EntityType<? extends SunShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public SunShotEntity(EntityType<? extends SunShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public SunShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.SUN_SHOT.get(), level, context);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.3d, 0.3d, 0.3d));

		if (level() instanceof ServerLevel level) {
			for (LivingEntity entity : EntityRetrievalUtil.getEntities(this, 10, LivingEntity.class, target -> !target.fireImmune() && !target.isOnFire() && EntityUtil.areProbablyEnemies(target, getShooter()))) {
				entity.igniteForSeconds(2);
			}

			if (this.tickCount >= 260) {
				AoAExplosionBuilder.at(this, AoAExplosions.SUN_STAFF, StandardExplosion::new).explode();
				discard();
			}
		}
	}

	@Override
	protected void onHit(HitResult result) {
		setDeltaMovement(new Vec3(0, !level().getBlockState(blockPosition().below()).isAir() ? 1 : getDeltaMovement().y(), 0));
	}
}