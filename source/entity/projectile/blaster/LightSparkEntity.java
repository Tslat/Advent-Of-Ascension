package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class LightSparkEntity extends BaseEnergyShot {
	public LightSparkEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public LightSparkEntity(World world) {
		super(AoAEntities.Projectiles.LIGHT_SPARK.get(), world);
	}

	public LightSparkEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.LIGHT_SPARK.get(), shooter, weapon, maxAge);
	}

	public LightSparkEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LIGHT_SPARK.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.1d, 0.1d, 0.1d));
	}
}
