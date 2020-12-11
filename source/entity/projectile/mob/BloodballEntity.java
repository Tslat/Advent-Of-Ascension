package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

public class BloodballEntity extends BaseMobProjectile {
	public BloodballEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public BloodballEntity(World world) {
		super(AoAEntities.Projectiles.BLOODBALL.get(), world);
	}

	public BloodballEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.BLOODBALL.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
