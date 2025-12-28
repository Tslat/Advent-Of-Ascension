package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class HeavyCannonballEntity extends PhysicalWeaponProjectile {
	public HeavyCannonballEntity(EntityType<? extends HeavyCannonballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HeavyCannonballEntity(EntityType<? extends HeavyCannonballEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HeavyCannonballEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HEAVY_CANNONBALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.05f;
	}
}
