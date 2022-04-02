package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class ToxicBulletEntity extends BaseBullet implements HardProjectile {
	public ToxicBulletEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public ToxicBulletEntity(Level world) {
		super(AoAProjectiles.TOXIC_BULLET.get(), world);
	}

	public ToxicBulletEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.TOXIC_BULLET.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public ToxicBulletEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.TOXIC_BULLET.get(), world, x, y, z);
	}
}
