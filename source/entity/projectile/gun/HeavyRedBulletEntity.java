package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class HeavyRedBulletEntity extends BaseBullet implements HardProjectile {
	public HeavyRedBulletEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HeavyRedBulletEntity(World world) {
		super(AoAEntities.Projectiles.HEAVY_RED_BULLET.get(), world);
	}

	public HeavyRedBulletEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HEAVY_RED_BULLET.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public HeavyRedBulletEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HEAVY_RED_BULLET.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.1f;
	}
}
