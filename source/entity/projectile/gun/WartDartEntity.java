package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class WartDartEntity extends BaseBullet implements HardProjectile {
	public WartDartEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public WartDartEntity(World world) {
		super(AoAEntities.Projectiles.WART_DART.get(), world);
	}

	public WartDartEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.WART_DART.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public WartDartEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.WART_DART.get(), world, x, y, z);
	}
}
