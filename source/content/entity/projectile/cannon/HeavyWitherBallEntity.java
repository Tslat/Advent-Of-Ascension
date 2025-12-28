package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class HeavyWitherBallEntity extends PhysicalWeaponProjectile {
	public HeavyWitherBallEntity(EntityType<? extends HeavyWitherBallEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HeavyWitherBallEntity(EntityType<? extends HeavyWitherBallEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HeavyWitherBallEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HEAVY_WITHER_BALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}
}
