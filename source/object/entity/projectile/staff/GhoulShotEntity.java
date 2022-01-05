package net.tslat.aoa3.object.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class GhoulShotEntity extends BaseEnergyShot {
	public GhoulShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public GhoulShotEntity(World world) {
		super(AoAEntities.Projectiles.GHOUL_SHOT.get(), world);
	}

	public GhoulShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.GHOUL_SHOT.get(), shooter, weapon, maxAge);
	}

	public GhoulShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.GHOUL_SHOT.get(), world, x, y, z);
	}
}
