package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.mob.creeponia.CreeperlockEntity;
import net.tslat.aoa3.content.entity.mob.creeponia.MagicalCreeperEntity;

public class CreeperShotEntity extends BaseMobProjectile {
	public CreeperShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public CreeperShotEntity(Level world) {
		super(AoAProjectiles.CREEPER_SHOT.get(), world);
	}

	public CreeperShotEntity(CreeperlockEntity shooter, Type projectileType) {
		super(AoAProjectiles.CREEPER_SHOT.get(), shooter.level, shooter, projectileType);
	}

	public CreeperShotEntity(MagicalCreeperEntity shooter, Type projectileType) {
		super(AoAProjectiles.CREEPER_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
