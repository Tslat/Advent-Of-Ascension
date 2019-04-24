package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityMoonMaker extends BaseBullet implements HardProjectile {
	public EntityMoonMaker(World world) {
		super(world);
	}

	public EntityMoonMaker(EntityLivingBase shooter, BaseGun gun, int piercingValue) {
		super(shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public EntityMoonMaker(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
