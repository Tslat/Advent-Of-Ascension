package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

public class MagicBallEntity extends BaseMobProjectile {
	public MagicBallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public MagicBallEntity(World world) {
		super(AoAEntities.Projectiles.MAGIC_BALL.get(), world);
	}

	public MagicBallEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.MAGIC_BALL.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
