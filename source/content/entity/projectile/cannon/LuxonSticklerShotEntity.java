package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class LuxonSticklerShotEntity extends PhysicalWeaponProjectile {
	public LuxonSticklerShotEntity(EntityType<? extends LuxonSticklerShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public LuxonSticklerShotEntity(EntityType<? extends LuxonSticklerShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public LuxonSticklerShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.LUXON_STICKLER_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.015f;
	}
}
