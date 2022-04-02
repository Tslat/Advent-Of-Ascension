package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class NoxiousShotEntity extends BaseEnergyShot {
	public NoxiousShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public NoxiousShotEntity(Level world) {
		super(AoAProjectiles.NOXIOUS_SHOT.get(), world);
	}

	public NoxiousShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAProjectiles.NOXIOUS_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public NoxiousShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.NOXIOUS_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}
}
