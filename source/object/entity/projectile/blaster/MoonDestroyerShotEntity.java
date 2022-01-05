package net.tslat.aoa3.object.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class MoonDestroyerShotEntity extends BaseEnergyShot {
	public MoonDestroyerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public MoonDestroyerShotEntity(World world) {
		super(AoAEntities.Projectiles.MOON_DESTROYER_SHOT.get(), world);
	}

	public MoonDestroyerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.MOON_DESTROYER_SHOT.get(), shooter, weapon, maxAge);
	}

	public MoonDestroyerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MOON_DESTROYER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.15f;
	}
}
