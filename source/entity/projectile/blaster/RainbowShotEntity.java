package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class RainbowShotEntity extends BaseEnergyShot {
	public RainbowShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public RainbowShotEntity(World world) {
		super(AoAEntities.Projectiles.RAINBOW_SHOT.get(), world);
	}

	public RainbowShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.RAINBOW_SHOT.get(), shooter, weapon, maxAge);
	}

	public RainbowShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.RAINBOW_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}
}
