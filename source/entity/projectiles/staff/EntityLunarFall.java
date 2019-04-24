package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityLunarFall extends BaseEnergyShot {
	public EntityLunarFall(World world) {
		super(world);
	}

	public EntityLunarFall(EntityLivingBase shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(shooter, weapon, posX, posY, posZ, velocity);
	}

	public EntityLunarFall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
