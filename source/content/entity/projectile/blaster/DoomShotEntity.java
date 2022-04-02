package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class DoomShotEntity extends BaseEnergyShot {
	public DoomShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public DoomShotEntity(Level world) {
		super(AoAProjectiles.DOOM_SHOT.get(), world);
	}

	public DoomShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.DOOM_SHOT.get(), shooter, weapon, maxAge);
	}

	public DoomShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.DOOM_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.03f;
	}
}
