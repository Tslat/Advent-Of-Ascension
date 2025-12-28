package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class SelyanSticklerShotEntity extends PhysicalWeaponProjectile {
	public SelyanSticklerShotEntity(EntityType<? extends SelyanSticklerShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public SelyanSticklerShotEntity(EntityType<? extends SelyanSticklerShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public SelyanSticklerShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.SELYAN_STICKLER_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.015f;
	}
}
