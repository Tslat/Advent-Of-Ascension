package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityRosidianShot extends BaseEnergyShot {
	public EntityRosidianShot(World world) {
		super(world);
	}

	public EntityRosidianShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityRosidianShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
