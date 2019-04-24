package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityPowerShot extends BaseEnergyShot {
	public EntityPowerShot(World world) {
		super(world);
	}

	public EntityPowerShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityPowerShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
