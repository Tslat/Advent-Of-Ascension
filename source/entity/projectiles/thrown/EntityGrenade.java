package net.tslat.aoa3.entity.projectiles.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityGrenade extends BaseBullet implements HardProjectile {
	private float explosionStrength = 1.5f;

	public EntityGrenade(World world) {
		super(world);
	}

	public EntityGrenade(EntityLivingBase shooter, BaseGun gun) {
		super(shooter, gun, 1.0f, 0, 1.5f);
	}

	public EntityGrenade(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityGrenade(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	public void doImpactEffect() {
		world.createExplosion(thrower, posX, posY, posZ, explosionStrength, false);
	}

	@Override
	public void doEntityImpact(Entity target) {
		world.createExplosion(thrower, posX, posY, posZ, explosionStrength, false);
	}
}
