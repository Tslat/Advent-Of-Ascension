package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

public class WhiteBallEntity extends BaseMobProjectile {
	public WhiteBallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public WhiteBallEntity(World world) {
		super(AoAEntities.Projectiles.WHITE_BALL.get(), world);
	}

	public WhiteBallEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.WHITE_BALL.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
