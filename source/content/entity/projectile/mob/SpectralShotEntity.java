package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;

public class SpectralShotEntity extends BaseMobProjectile {
	public SpectralShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SpectralShotEntity(World world) {
		super(AoAEntities.Projectiles.SPECTRAL_SHOT.get(), world);
	}

	public SpectralShotEntity(AoARangedAttacker shooter, Type projectileType) {
		super(AoAEntities.Projectiles.SPECTRAL_SHOT.get(), ((Entity)shooter).level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
