package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class EradicatorShotEntity extends BaseEnergyShot {
	public EradicatorShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public EradicatorShotEntity(Level world) {
		super(AoAProjectiles.ERADICATOR_SHOT.get(), world);
	}

	public EradicatorShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.ERADICATOR_SHOT.get(), shooter, weapon, maxAge);
	}

	public EradicatorShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.ERADICATOR_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.3f;
	}
}
