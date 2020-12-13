package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class FlowerShotEntity extends BaseEnergyShot {
	public FlowerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public FlowerShotEntity(World world) {
		super(AoAEntities.Projectiles.FLOWER_SHOT.get(), world);
	}

	public FlowerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.FLOWER_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public FlowerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.FLOWER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}
}
