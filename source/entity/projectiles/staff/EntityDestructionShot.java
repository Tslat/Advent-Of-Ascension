package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.utils.WorldUtil;

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

		motionX *= 0.4;
		motionY *= 0.4;
		motionZ *= 0.4;

		if (getAge() >= 65) {
			WorldUtil.createExplosion(thrower, world, this, 3.0f);
			setDead();
		}
	}
}
