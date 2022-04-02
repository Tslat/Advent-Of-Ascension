package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class CreepBombEntity extends BaseMobProjectile {
	public CreepBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public CreepBombEntity(Level world) {
		super(AoAProjectiles.CREEP_BOMB.get(), world);
	}

	public CreepBombEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.CREEP_BOMB.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
