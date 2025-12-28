package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;

public class DestructionShotEntity extends NonPhysicalWeaponProjectile {
	public DestructionShotEntity(EntityType<? extends DestructionShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public DestructionShotEntity(EntityType<? extends DestructionShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public DestructionShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.DESTRUCTION_SHOT.get(), level, context);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().scale(0.4d));

		if (getShotContext().projectileLifespan() - this.tickCount <= 1 && !level().isClientSide) {
			AoAExplosionBuilder.at(this, AoAExplosions.DESTRUCTION_STAFF, StandardExplosion::new).explode();
			discard();
		}
	}
}
