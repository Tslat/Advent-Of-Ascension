package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class GreenBulletEntity extends BaseBullet implements HardProjectile {
	public GreenBulletEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public GreenBulletEntity(World world) {
		super(AoAEntities.Projectiles.GREEN_BULLET.get(), world);
	}

	public GreenBulletEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.GREEN_BULLET.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public GreenBulletEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.GREEN_BULLET.get(), world, x, y, z);
	}
}
