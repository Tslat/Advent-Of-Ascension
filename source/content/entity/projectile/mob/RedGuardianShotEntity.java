package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class RedGuardianShotEntity extends BaseMobProjectile {
	public RedGuardianShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public RedGuardianShotEntity(World world) {
		super(AoAEntities.Projectiles.RED_GUARDIAN_SHOT.get(), world);
	}

	public RedGuardianShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.RED_GUARDIAN_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
