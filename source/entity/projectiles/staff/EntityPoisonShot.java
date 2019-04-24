package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityPoisonShot extends BaseEnergyShot {
	public EntityPoisonShot(World world) {
		super(world);
	}

	public EntityPoisonShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityPoisonShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
