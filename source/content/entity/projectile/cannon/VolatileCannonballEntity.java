package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;

public class VolatileCannonballEntity extends PhysicalWeaponProjectile {
	public VolatileCannonballEntity(EntityType<? extends VolatileCannonballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public VolatileCannonballEntity(EntityType<? extends VolatileCannonballEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public VolatileCannonballEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.VOLATILE_CANNONBALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.05f;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide && this.tickCount % 4 == 3)
			AoAExplosionBuilder.at(this, AoAExplosions.VOLATILE_CANNONBALL, StandardExplosion::new).explode();
	}
}
