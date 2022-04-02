package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class HeavyRedBulletEntity extends BaseBullet implements HardProjectile {
	public HeavyRedBulletEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public HeavyRedBulletEntity(Level world) {
		super(AoAProjectiles.HEAVY_RED_BULLET.get(), world);
	}

	public HeavyRedBulletEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.HEAVY_RED_BULLET.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public HeavyRedBulletEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.HEAVY_RED_BULLET.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.1f;
	}
}
