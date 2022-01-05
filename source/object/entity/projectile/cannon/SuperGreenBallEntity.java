package net.tslat.aoa3.object.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;

public class SuperGreenBallEntity extends BaseBullet implements HardProjectile {
	public SuperGreenBallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SuperGreenBallEntity(World world) {
		super(AoAEntities.Projectiles.SUPER_GREEN_BALL.get(), world);
	}

	public SuperGreenBallEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SUPER_GREEN_BALL.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public SuperGreenBallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SUPER_GREEN_BALL.get(), world, x, y, z);
	}
}
