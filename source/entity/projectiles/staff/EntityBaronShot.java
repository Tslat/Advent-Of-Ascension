package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityBaronShot extends BaseEnergyShot {
	public EntityBaronShot(World world) {
		super(world);
	}

	public EntityBaronShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityBaronShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
