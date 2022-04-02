package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class DestroyerShotEntity extends BaseEnergyShot {
	public DestroyerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public DestroyerShotEntity(Level world) {
		super(AoAProjectiles.DESTROYER_SHOT.get(), world);
	}

	public DestroyerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.DESTROYER_SHOT.get(), shooter, weapon, maxAge);
	}

	public DestroyerShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.DESTROYER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.15f;
	}
}
