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

public class HiveBallEntity extends BaseBullet implements HardProjectile {
	public HiveBallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public HiveBallEntity(World world) {
		super(AoAEntities.Projectiles.HIVE_BALL.get(), world);
	}

	public HiveBallEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HIVE_BALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HiveBallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HIVE_BALL.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.1f;
	}
}
