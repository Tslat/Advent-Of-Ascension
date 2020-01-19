package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityLyonicShot extends BaseEnergyShot {
	public EntityLyonicShot(World world) {
		super(world);
	}

	public EntityLyonicShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityLyonicShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public EntityLyonicShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
