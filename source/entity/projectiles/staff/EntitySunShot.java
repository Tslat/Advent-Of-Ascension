package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.WorldUtil;

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

		for (EntityLivingBase e : world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().grow(10), PredicateUtil.IS_HOSTILE_MOB)) {
			if (!e.isBurning() && !e.isImmuneToFire() && !EntityUtil.isTypeImmune(e, Enums.MobProperties.MAGIC_IMMUNE) && !EntityUtil.isTypeImmune(e, Enums.MobProperties.FIRE_IMMUNE))
				e.setFire(1);
		}

		if (getAge() >= 260) {
			WorldUtil.createExplosion(thrower, world, this, 3.0f);
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
