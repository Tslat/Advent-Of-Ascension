package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class GreenGuardianShotEntity extends BaseMobProjectile {
	public GreenGuardianShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public GreenGuardianShotEntity(Level world) {
		super(AoAProjectiles.GREEN_GUARDIAN_SHOT.get(), world);
	}

	public GreenGuardianShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.GREEN_GUARDIAN_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
