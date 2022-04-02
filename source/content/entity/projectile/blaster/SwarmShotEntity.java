package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class SwarmShotEntity extends BaseEnergyShot {
	public SwarmShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public SwarmShotEntity(Level world) {
		super(AoAProjectiles.SWARM_SHOT.get(), world);
	}

	public SwarmShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAProjectiles.SWARM_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public SwarmShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.SWARM_SHOT.get(), world, x, y, z);
	}
}
