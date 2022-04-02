package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class WinderShotEntity extends BaseEnergyShot {
	public WinderShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public WinderShotEntity(Level world) {
		super(AoAProjectiles.WINDER_SHOT.get(), world);
	}

	public WinderShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.WINDER_SHOT.get(), shooter, weapon, maxAge);
	}

	public WinderShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.WINDER_SHOT.get(), world, x, y, z);
	}
}
