package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityAtomizerBounce extends BaseEnergyShot {
	public EntityAtomizerBounce(World world) {
		super(world);
	}

	public EntityAtomizerBounce(EntityLivingBase shooter, EnergyProjectileWeapon weapon, EntityAtomizerShot shot, double motionX, double motionY, double motionZ) {
		super(shooter, weapon, shot.posX, shot.posY, shot.posZ, motionX, motionY, motionZ);
	}

	public EntityAtomizerBounce(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}
}
