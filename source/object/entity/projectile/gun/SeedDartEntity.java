package net.tslat.aoa3.object.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;

public class SeedDartEntity extends BaseBullet implements HardProjectile {
	public SeedDartEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public SeedDartEntity(World world) {
		super(AoAEntities.Projectiles.SEED_DART.get(), world);
	}

	public SeedDartEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SEED_DART.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public SeedDartEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SEED_DART.get(), world, x, y, z);
	}
}
