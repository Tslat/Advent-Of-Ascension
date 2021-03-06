package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class ShoeShotEntity extends BaseBullet implements HardProjectile {
	public ShoeShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public ShoeShotEntity(World world) {
		super(AoAEntities.Projectiles.SHOE_SHOT.get(), world);
	}

	public ShoeShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SHOE_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public ShoeShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SHOE_SHOT.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.13f;
	}

	public void doImpactEffect(Entity target) {}
}