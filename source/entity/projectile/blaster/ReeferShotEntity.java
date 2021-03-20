package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class ReeferShotEntity extends BaseEnergyShot {
	public float yOffset1;
	public float yOffset2;
	public boolean toggle1;
	public boolean toggle2 = true;

	public ReeferShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ReeferShotEntity(World world) {
		super(AoAEntities.Projectiles.REEFER_SHOT.get(), world);
	}

	public ReeferShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.REEFER_SHOT.get(), shooter, weapon, maxAge);
	}

	public ReeferShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.REEFER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.06f;
	}
}
