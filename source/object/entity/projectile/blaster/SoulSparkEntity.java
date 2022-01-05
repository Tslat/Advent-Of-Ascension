package net.tslat.aoa3.object.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class SoulSparkEntity extends BaseEnergyShot {
	public SoulSparkEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public SoulSparkEntity(World world) {
		super(AoAEntities.Projectiles.SOUL_SPARK.get(), world);
	}

	public SoulSparkEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.SOUL_SPARK.get(), shooter, weapon, maxAge);
	}

	public SoulSparkEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SOUL_SPARK.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.1d, 0.1d, 0.1d));
	}
}
