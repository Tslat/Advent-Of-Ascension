package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntitySoulSpark extends BaseEnergyShot {
	public EntitySoulSpark(World world) {
		super(world);
	}

	public EntitySoulSpark(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntitySoulSpark(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		motionX *= 0.1;
		motionY *= 0.1;
		motionZ *= 0.1;
	}
}
