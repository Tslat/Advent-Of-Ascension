package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class HagShotEntity extends BaseMobProjectile {
	public HagShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HagShotEntity(Level world) {
		super(AoAProjectiles.HAG_SHOT.get(), world);
	}

	public HagShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.HAG_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
