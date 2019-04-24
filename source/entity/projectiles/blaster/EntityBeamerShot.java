package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityBeamerShot extends BaseEnergyShot {
	public EntityBeamerShot(World world) {
		super(world);
	}

	public EntityBeamerShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public EntityBeamerShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.4f;
	}
}
