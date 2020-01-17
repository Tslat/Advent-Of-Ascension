package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityHaunterShot extends BaseEnergyShot {
	public EntityHaunterShot(World world) {
		super(world);
	}

	public EntityHaunterShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityHaunterShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote && getAge() % 4 == 0)
			WorldUtil.createExplosion(getThrower(), world, this, 2.0f);
	}
}
