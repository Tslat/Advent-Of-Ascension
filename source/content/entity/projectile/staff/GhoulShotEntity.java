package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class GhoulShotEntity extends BaseEnergyShot {
	public GhoulShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public GhoulShotEntity(Level world) {
		super(AoAProjectiles.GHOUL_SHOT.get(), world);
	}

	public GhoulShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.GHOUL_SHOT.get(), shooter, weapon, maxAge);
	}

	public GhoulShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.GHOUL_SHOT.get(), world, x, y, z);
	}
}
