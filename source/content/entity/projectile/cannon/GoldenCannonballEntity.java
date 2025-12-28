package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class GoldenCannonballEntity extends PhysicalWeaponProjectile {
	public GoldenCannonballEntity(EntityType<? extends GoldenCannonballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public GoldenCannonballEntity(EntityType<? extends GoldenCannonballEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public GoldenCannonballEntity(Level level, WeaponFiringContext context) {
		super(AoAProjectiles.GOLDEN_CANNONBALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.05f;
	}
}
