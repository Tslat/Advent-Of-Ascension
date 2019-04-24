package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityAquaticShot extends BaseEnergyShot {
	public EntityAquaticShot(World world) {
		super(world);
	}

	public EntityAquaticShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityAquaticShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
