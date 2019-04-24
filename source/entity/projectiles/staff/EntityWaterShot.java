package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityWaterShot extends BaseEnergyShot {
	public EntityWaterShot(World world) {
		super(world);
	}

	public EntityWaterShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityWaterShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
