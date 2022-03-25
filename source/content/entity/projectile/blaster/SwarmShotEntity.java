package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class SwarmShotEntity extends BaseEnergyShot {
	public SwarmShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public SwarmShotEntity(World world) {
		super(AoAEntities.Projectiles.SWARM_SHOT.get(), world);
	}

	public SwarmShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.SWARM_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public SwarmShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SWARM_SHOT.get(), world, x, y, z);
	}
}
