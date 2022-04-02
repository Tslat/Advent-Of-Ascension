package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class ShyreShotEntity extends BaseEnergyShot {
	public ShyreShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ShyreShotEntity(Level world) {
		super(AoAProjectiles.SHYRE_SHOT.get(), world);
	}

	public ShyreShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.SHYRE_SHOT.get(), shooter, weapon, maxAge);
	}

	public ShyreShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.SHYRE_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.085f;
	}
}
