package net.tslat.aoa3.object.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class BaronShotEntity extends BaseEnergyShot {
	public BaronShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public BaronShotEntity(World world) {
		super(AoAEntities.Projectiles.BARON_SHOT.get(), world);
	}

	public BaronShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.BARON_SHOT.get(), shooter, weapon, maxAge);
	}

	public BaronShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BARON_SHOT.get(), world, x, y, z);
	}
}
