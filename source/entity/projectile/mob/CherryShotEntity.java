package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile.Type;

public class CherryShotEntity extends BaseMobProjectile {
	public CherryShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public CherryShotEntity(World world) {
		super(AoAEntities.Projectiles.CHERRY_SHOT.get(), world);
	}

	public CherryShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CHERRY_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.015f;
	}
}
