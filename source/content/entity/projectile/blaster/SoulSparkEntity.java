package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class SoulSparkEntity extends BaseEnergyShot {
	public SoulSparkEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public SoulSparkEntity(Level world) {
		super(AoAProjectiles.SOUL_SPARK.get(), world);
	}

	public SoulSparkEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.SOUL_SPARK.get(), shooter, weapon, maxAge);
	}

	public SoulSparkEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.SOUL_SPARK.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.1d, 0.1d, 0.1d));
	}
}
