package net.tslat.aoa3.object.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class WeightedShowerShotEntity extends BaseEnergyShot {
	public WeightedShowerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public WeightedShowerShotEntity(World world) {
		super(AoAEntities.Projectiles.WEIGHTED_SHOWER_SHOT.get(), world);
	}

	public WeightedShowerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.WEIGHTED_SHOWER_SHOT.get(), shooter, weapon, maxAge);
	}

	public WeightedShowerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.WEIGHTED_SHOWER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
