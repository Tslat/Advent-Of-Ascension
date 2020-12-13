package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.VoxxulonEntity;

public class VoxxulonMeteorEntity extends BaseMobProjectile {
	public VoxxulonMeteorEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public VoxxulonMeteorEntity(World world) {
		super(AoAEntities.Projectiles.VOXXULON_METEOR.get(), world);
	}

	public VoxxulonMeteorEntity(VoxxulonEntity shooter, Entity target, Type projectileType) {
		super(AoAEntities.Projectiles.VOXXULON_METEOR.get(), shooter.world, shooter, target, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0;
	}
}
