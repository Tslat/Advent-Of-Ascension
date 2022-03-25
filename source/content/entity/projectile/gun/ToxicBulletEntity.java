package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class ToxicBulletEntity extends BaseBullet implements HardProjectile {
	public ToxicBulletEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ToxicBulletEntity(World world) {
		super(AoAEntities.Projectiles.TOXIC_BULLET.get(), world);
	}

	public ToxicBulletEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.TOXIC_BULLET.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public ToxicBulletEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.TOXIC_BULLET.get(), world, x, y, z);
	}
}
