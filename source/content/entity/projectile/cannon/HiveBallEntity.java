package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class HiveBallEntity extends PhysicalWeaponProjectile {
	public HiveBallEntity(EntityType<? extends HiveBallEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HiveBallEntity(EntityType<? extends HiveBallEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HiveBallEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HIVE_BALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}
}
