package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

public class LelyetianShotEntity extends BaseMobProjectile {
	public LelyetianShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public LelyetianShotEntity(World world) {
		super(AoAEntities.Projectiles.LELYETIAN_SHOT.get(), world);
	}

	public LelyetianShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.LELYETIAN_SHOT.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
