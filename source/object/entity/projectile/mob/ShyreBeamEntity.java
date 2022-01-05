package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.base.AoARangedMob;

public class ShyreBeamEntity extends BaseMobProjectile {
	public ShyreBeamEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public ShyreBeamEntity(World world) {
		super(AoAEntities.Projectiles.SHYRE_BEAM.get(), world);
	}

	public ShyreBeamEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.SHYRE_BEAM.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
