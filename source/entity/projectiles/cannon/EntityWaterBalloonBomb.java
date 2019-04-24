package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityWaterBalloonBomb extends BaseBullet implements HardProjectile {
	EntityLivingBase shooter;

	public EntityWaterBalloonBomb(World world) {
		super(world);
	}

	public EntityWaterBalloonBomb(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public EntityWaterBalloonBomb(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.1f;
	}

	@Override
	public void doImpactEffect() {
		world.createExplosion(shooter, posX, posY, posZ, 1.5f, false);
	}
}
