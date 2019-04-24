package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityParalyzerShot extends BaseEnergyShot {
	public EntityParalyzerShot(World world) {
		super(world);
	}

	public EntityParalyzerShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityParalyzerShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
