package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class LimoniteBulletEntity extends BaseBullet implements HardProjectile {
	public LimoniteBulletEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public LimoniteBulletEntity(Level world) {
		super(AoAProjectiles.BULLET.get(), world);
	}

	public LimoniteBulletEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAProjectiles.BULLET.get(), shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public LimoniteBulletEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.BULLET.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public LimoniteBulletEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.BULLET.get(), world, x, y, z);
	}

	public void doImpactEffect(Entity target) {}
}
