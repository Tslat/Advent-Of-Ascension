package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class HotShotEntity extends BaseBullet implements HardProjectile {
	public HotShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public HotShotEntity(World world) {
		super(AoAEntities.Projectiles.HOT_SHOT.get(), world);
	}

	public HotShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HOT_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HotShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HOT_SHOT.get(), world, x, y, z);
	}

	public void doImpactEffect(Entity target) {}
}
