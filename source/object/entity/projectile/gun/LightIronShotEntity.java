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

public class LightIronShotEntity extends BaseBullet implements HardProjectile {
	public LightIronShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public LightIronShotEntity(World world) {
		super(AoAEntities.Projectiles.LIGHT_IRON_SHOT.get(), world);
	}

	public LightIronShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.LIGHT_IRON_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public LightIronShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LIGHT_IRON_SHOT.get(), world, x, y, z);
	}

	public void doImpactEffect(Entity target) {}
}
