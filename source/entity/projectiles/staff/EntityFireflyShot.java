package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityFireflyShot extends BaseEnergyShot {
	public EntityFireflyShot(World world) {
		super(world);
	}

	public EntityFireflyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, EntityFireflyShot shot, double motionX, double motionY, double motionZ) {
		super(shooter, weapon, shot.posX, shot.posY, shot.posZ, motionX, motionY, motionZ);
	}

	public EntityFireflyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityFireflyShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}
}
