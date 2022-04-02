package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class WhiteBallEntity extends BaseMobProjectile {
	public WhiteBallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public WhiteBallEntity(Level world) {
		super(AoAProjectiles.WHITE_BALL.get(), world);
	}

	public WhiteBallEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.WHITE_BALL.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
