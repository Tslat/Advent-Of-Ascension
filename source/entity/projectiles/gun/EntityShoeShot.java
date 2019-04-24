package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityShoeShot extends BaseBullet implements HardProjectile {
	public EntityShoeShot(World world) {
		super(world);
	}

	public EntityShoeShot(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.13f;
	}


	public EntityShoeShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public void doImpactEffect(Entity target) {}
}