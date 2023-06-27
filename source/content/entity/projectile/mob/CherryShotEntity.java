package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class CherryShotEntity extends BaseMobProjectile {
	public CherryShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CherryShotEntity(Level world) {
		super(AoAProjectiles.CHERRY_SHOT.get(), world);
	}

	public CherryShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.CHERRY_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.015f;
	}
}
