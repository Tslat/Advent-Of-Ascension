package net.tslat.aoa3.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class UltraGreenBallEntity extends BaseBullet implements HardProjectile {
	public UltraGreenBallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public UltraGreenBallEntity(World world) {
		super(AoAEntities.Projectiles.ULTRA_GREEN_BALL.get(), world);
	}

	public UltraGreenBallEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.ULTRA_GREEN_BALL.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public UltraGreenBallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ULTRA_GREEN_BALL.get(), world, x, y, z);
	}
}
