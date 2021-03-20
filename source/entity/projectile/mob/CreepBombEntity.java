package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;

import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile.Type;

public class CreepBombEntity extends BaseMobProjectile {
	public CreepBombEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public CreepBombEntity(World world) {
		super(AoAEntities.Projectiles.CREEP_BOMB.get(), world);
	}

	public CreepBombEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CREEP_BOMB.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
