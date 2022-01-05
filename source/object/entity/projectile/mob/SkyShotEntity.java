package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.base.AoAFlyingRangedMob;

public class SkyShotEntity extends BaseMobProjectile {
	public SkyShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SkyShotEntity(World world) {
		super(AoAEntities.Projectiles.SKY_SHOT.get(), world);
	}

	public SkyShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.SKY_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
