package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;

public class SpectralShotEntity extends BaseMobProjectile {
	public SpectralShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public SpectralShotEntity(Level world) {
		super(AoAProjectiles.SPECTRAL_SHOT.get(), world);
	}

	public SpectralShotEntity(AoARangedAttacker shooter, Type projectileType) {
		super(AoAProjectiles.SPECTRAL_SHOT.get(), ((Entity)shooter).level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
