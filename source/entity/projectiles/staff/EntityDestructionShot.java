package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityDestructionShot extends BaseEnergyShot {
	public EntityDestructionShot(World world) {
		super(world);
	}

	public EntityDestructionShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityDestructionShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		motionX *= 0.3;
		motionY *= 0.3;
		motionZ *= 0.3;

		if (getAge() >= 55) {
			world.createExplosion(getThrower(), posX, posY, posZ, 2.6f, false);
			setDead();
		}
	}
}
