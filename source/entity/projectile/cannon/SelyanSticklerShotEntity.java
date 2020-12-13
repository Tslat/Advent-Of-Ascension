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

public class SelyanSticklerShotEntity extends BaseBullet implements HardProjectile {
	public SelyanSticklerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public SelyanSticklerShotEntity(World world) {
		super(AoAEntities.Projectiles.SELYAN_STICKLER_SHOT.get(), world);
	}

	public SelyanSticklerShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SELYAN_STICKLER_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public SelyanSticklerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SELYAN_STICKLER_SHOT.get(), world, x, y, z);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.015f;
	}
}
