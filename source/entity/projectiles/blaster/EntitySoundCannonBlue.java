package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntitySoundCannonBlue extends BaseEnergyShot {
	public EntitySoundCannonBlue(World world) {
		super(world);
	}

	public EntitySoundCannonBlue(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntitySoundCannonBlue(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
