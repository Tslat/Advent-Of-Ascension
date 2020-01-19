package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityRosidTangle extends BaseEnergyShot {
	private boolean impacted = false;

	public EntityRosidTangle(EntityRosidTangle tangleShot, double posX, double posY, double posZ) {
		super(tangleShot.thrower, tangleShot.weapon, posX, posY, posZ, -1);

		lifespan = 15;
	}

	public EntityRosidTangle(EntityLivingBase shooter, EnergyProjectileWeapon weapon) {
		super(shooter, weapon, 180);
	}

	public EntityRosidTangle(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote && impacted && ticksExisted % 5 == 0) {
			double posX = this.posX + rand.nextGaussian() * 5;
			double posZ = this.posZ + rand.nextGaussian() * 5;
			double posY = world.getHeight((int)posX, (int)posZ);

			world.spawnEntity(new EntityRosidTangle(this, posX, posY, posZ));
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null || impacted) {
			super.onImpact(result);
		}
		else {
			motionX = 0;
			motionZ = 0;
			impacted = true;
		}
	}
}
