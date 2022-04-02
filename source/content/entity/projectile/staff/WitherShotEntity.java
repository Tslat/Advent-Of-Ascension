package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class WitherShotEntity extends BaseEnergyShot {
	public WitherShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public WitherShotEntity(Level world) {
		super(AoAProjectiles.WITHER_SHOT.get(), world);
	}

	public WitherShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.WITHER_SHOT.get(), shooter, weapon, maxAge);
	}

	public WitherShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.WITHER_SHOT.get(), world, x, y, z);
	}
}
