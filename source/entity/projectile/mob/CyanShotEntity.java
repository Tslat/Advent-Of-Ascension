package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

public class CyanShotEntity extends BaseMobProjectile {
	public CyanShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public CyanShotEntity(World world) {
		super(AoAEntities.Projectiles.CYAN_SHOT.get(), world);
	}

	public CyanShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CYAN_SHOT.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
