package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityRosidianShot extends BaseEnergyShot {
	private boolean impacted = false;

	public EntityRosidianShot(EntityRosidianShot tangleShot, double posX, double posY, double posZ) {
		super(tangleShot.thrower, tangleShot.weapon, posX, posY, posZ, -1f);

		lifespan = 2;
	}

	public EntityRosidianShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon) {
		super(shooter, weapon, 180);
	}

	public EntityRosidianShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityRosidianShot(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote && impacted && lifespan > 15 && ticksExisted % 5 == 0) {
			double posX = this.posX + rand.nextGaussian() * 3;
			double posZ = this.posZ + rand.nextGaussian() * 3;
			double posY = world.getHeight((int)posX, (int)posZ);

			world.spawnEntity(new EntityRosidianShot(this, posX, posY + 0.5, posZ));
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			super.onImpact(result);
		}
		else {
			motionX = 0;
			motionY = 0;
			motionZ = 0;
			impacted = true;
		}
	}
}
