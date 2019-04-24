package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityNoxiousShot extends BaseEnergyShot {
	public EntityNoxiousShot(World world) {
		super(world);
	}

	public EntityNoxiousShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public EntityNoxiousShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}
}
