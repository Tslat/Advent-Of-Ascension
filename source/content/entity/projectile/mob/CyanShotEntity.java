package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;

public class CyanShotEntity extends BaseMobProjectile {
	public CyanShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public CyanShotEntity(Level world) {
		super(AoAProjectiles.CYAN_SHOT.get(), world);
	}

	public CyanShotEntity(AoARangedAttacker shooter, Type projectileType) {
		super(AoAProjectiles.CYAN_SHOT.get(), ((Entity)shooter).level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
