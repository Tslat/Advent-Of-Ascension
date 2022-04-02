package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class FlowerShotEntity extends BaseEnergyShot {
	public FlowerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public FlowerShotEntity(Level world) {
		super(AoAProjectiles.FLOWER_SHOT.get(), world);
	}

	public FlowerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAProjectiles.FLOWER_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public FlowerShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.FLOWER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}
}
