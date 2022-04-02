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

public class HotShotEntity extends BaseBullet implements HardProjectile {
	public HotShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HotShotEntity(Level world) {
		super(AoAProjectiles.HOT_SHOT.get(), world);
	}

	public HotShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.HOT_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HotShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.HOT_SHOT.get(), world, x, y, z);
	}

	public void doImpactEffect(Entity target) {}
}
