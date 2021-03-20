package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;

import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile.Type;

public class ConstructTerrorShotEntity extends BaseMobProjectile {
	public ConstructTerrorShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public ConstructTerrorShotEntity(World world) {
		super(AoAEntities.Projectiles.TERROR_CONSTRUCT_SHOT.get(), world);
	}

	public ConstructTerrorShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.TERROR_CONSTRUCT_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
