package net.tslat.aoa3.object.entity.projectile.gun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;

public class LimoniteBulletEntity extends BaseBullet implements HardProjectile {
	public LimoniteBulletEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public LimoniteBulletEntity(World world) {
		super(AoAEntities.Projectiles.BULLET.get(), world);
	}

	public LimoniteBulletEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.BULLET.get(), shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public LimoniteBulletEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.BULLET.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public LimoniteBulletEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BULLET.get(), world, x, y, z);
	}

	public void doImpactEffect(Entity target) {}
}
