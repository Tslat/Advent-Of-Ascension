package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class HeavyRedCannonballEntity extends PhysicalWeaponProjectile {
	public HeavyRedCannonballEntity(EntityType<? extends HeavyRedCannonballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HeavyRedCannonballEntity(EntityType<? extends HeavyRedCannonballEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HeavyRedCannonballEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HEAVY_RED_CANNONBALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}
}
