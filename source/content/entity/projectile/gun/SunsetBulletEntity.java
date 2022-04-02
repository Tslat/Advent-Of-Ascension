package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class SunsetBulletEntity extends BaseBullet implements HardProjectile {
	public SunsetBulletEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public SunsetBulletEntity(Level world) {
		super(AoAProjectiles.SUNSET_BULLET.get(), world);
	}

	public SunsetBulletEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.SUNSET_BULLET.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public SunsetBulletEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.SUNSET_BULLET.get(), world, x, y, z);
	}
}
