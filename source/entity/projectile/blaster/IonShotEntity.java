package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class IonShotEntity extends BaseEnergyShot {
	public IonShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public IonShotEntity(World world) {
		super(AoAEntities.Projectiles.ION_SHOT.get(), world);
	}

	public IonShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ION_SHOT.get(), shooter, weapon, maxAge);
	}

	public IonShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ION_SHOT.get(), world, x, y, z);
	}
}
