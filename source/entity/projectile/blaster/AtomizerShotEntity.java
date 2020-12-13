package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class AtomizerShotEntity extends BaseEnergyShot {
	public AtomizerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public AtomizerShotEntity(World world) {
		super(AoAEntities.Projectiles.ATOMIZER_SHOT.get(), world);
	}

	public AtomizerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ATOMIZER_SHOT.get(), shooter, weapon, maxAge);
	}

	public AtomizerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ATOMIZER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}
}
