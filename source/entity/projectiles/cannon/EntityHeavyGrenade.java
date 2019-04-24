package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.thrown.EntityGrenade;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityHeavyGrenade extends EntityGrenade implements HardProjectile {
	public EntityHeavyGrenade(World world) {
		super(world);
	}

	public EntityHeavyGrenade(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, piercingValue);
	}

	public EntityHeavyGrenade(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}
}
