package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class AtomizerShotEntity extends BaseEnergyShot {
	public AtomizerShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public AtomizerShotEntity(Level world) {
		super(AoAProjectiles.ATOMIZER_SHOT.get(), world);
	}

	public AtomizerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.ATOMIZER_SHOT.get(), shooter, weapon, maxAge);
	}

	public AtomizerShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.ATOMIZER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
