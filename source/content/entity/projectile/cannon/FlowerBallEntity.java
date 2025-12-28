package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class FlowerBallEntity extends PhysicalWeaponProjectile {
	public FlowerBallEntity(EntityType<? extends FlowerBallEntity> entityType, Level level) {
		super(entityType, level);
	}

	public FlowerBallEntity(EntityType<? extends FlowerBallEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public FlowerBallEntity(Level level, WeaponFiringContext context) {
		super(AoAProjectiles.FLOWER_BALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}
}
