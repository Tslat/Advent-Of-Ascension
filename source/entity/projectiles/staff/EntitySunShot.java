package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.utils.EntityUtil;

public class EntitySunShot extends BaseEnergyShot {
	public EntitySunShot(World world) {
		super(world);
	}

	public EntitySunShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntitySunShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		motionX *= 0.3;
		motionY *= 0.3;
		motionZ *= 0.3;

		for (EntityLivingBase e : world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().grow(10), EntityUtil::isHostileMob)) {
			e.setFire(5);
		}

		if (getAge() >= 400) {
			world.createExplosion(getThrower(), posX, posY, posZ, 4.0f, false);
			setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		motionX = 0;
		motionZ = 0;

		if (world.getBlockState(getPosition().down()).getBlock() != Blocks.AIR) {
			motionY = 1;
		}
	}
}
