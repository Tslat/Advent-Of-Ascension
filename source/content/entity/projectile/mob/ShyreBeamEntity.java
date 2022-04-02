package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class ShyreBeamEntity extends BaseMobProjectile {
	public ShyreBeamEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ShyreBeamEntity(Level world) {
		super(AoAProjectiles.SHYRE_BEAM.get(), world);
	}

	public ShyreBeamEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.SHYRE_BEAM.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
