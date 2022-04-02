package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;

public class SkyShotEntity extends BaseMobProjectile {
	public SkyShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public SkyShotEntity(Level world) {
		super(AoAProjectiles.SKY_SHOT.get(), world);
	}

	public SkyShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAProjectiles.SKY_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
