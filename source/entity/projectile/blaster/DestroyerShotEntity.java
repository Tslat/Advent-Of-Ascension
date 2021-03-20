package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class DestroyerShotEntity extends BaseEnergyShot {
	public DestroyerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public DestroyerShotEntity(World world) {
		super(AoAEntities.Projectiles.DESTROYER_SHOT.get(), world);
	}

	public DestroyerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.DESTROYER_SHOT.get(), shooter, weapon, maxAge);
	}

	public DestroyerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DESTROYER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.15f;
	}
}
