package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

public class CherryShotEntity extends BaseMobProjectile {
	public CherryShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public CherryShotEntity(World world) {
		super(AoAEntities.Projectiles.CHERRY_SHOT.get(), world);
	}

	public CherryShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CHERRY_SHOT.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.015f;
	}
}
