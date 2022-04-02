package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class ReeferShotEntity extends BaseEnergyShot {
	public float yOffset1;
	public float yOffset2;
	public boolean toggle1;
	public boolean toggle2 = true;

	public ReeferShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public ReeferShotEntity(Level world) {
		super(AoAProjectiles.REEFER_SHOT.get(), world);
	}

	public ReeferShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.REEFER_SHOT.get(), shooter, weapon, maxAge);
	}

	public ReeferShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.REEFER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.06f;
	}
}
