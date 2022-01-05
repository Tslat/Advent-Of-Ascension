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

public class GhoulBallEntity extends BaseBullet implements HardProjectile {
	public GhoulBallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public GhoulBallEntity(World world) {
		super(AoAEntities.Projectiles.GHOUL_BALL.get(), world);
	}

	public GhoulBallEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.GHOUL_BALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public GhoulBallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.GHOUL_BALL.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.05f;
	}
}
