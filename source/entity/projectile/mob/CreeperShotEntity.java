package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.mob.creeponia.CreeperlockEntity;
import net.tslat.aoa3.entity.mob.creeponia.MagicalCreeperEntity;

public class CreeperShotEntity extends BaseMobProjectile {
	public CreeperShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public CreeperShotEntity(World world) {
		super(AoAEntities.Projectiles.CREEPER_SHOT.get(), world);
	}

	public CreeperShotEntity(CreeperlockEntity shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CREEPER_SHOT.get(), shooter.world, shooter, projectileType);
	}

	public CreeperShotEntity(MagicalCreeperEntity shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CREEPER_SHOT.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
