package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class WartDartEntity extends BaseBullet implements HardProjectile {
	public WartDartEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public WartDartEntity(Level world) {
		super(AoAProjectiles.WART_DART.get(), world);
	}

	public WartDartEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.WART_DART.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public WartDartEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.WART_DART.get(), world, x, y, z);
	}
}
