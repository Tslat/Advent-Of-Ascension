package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityVolatileCannonball extends BaseBullet implements HardProjectile {
	private EntityLivingBase shooter;

	public EntityVolatileCannonball(World world) {
		super(world);
	}

	public EntityVolatileCannonball(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public EntityVolatileCannonball(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.05f;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (getAge() % 4 == 1)
			world.createExplosion(shooter, posX, posY, posZ, 2.0f, false);
	}
}
