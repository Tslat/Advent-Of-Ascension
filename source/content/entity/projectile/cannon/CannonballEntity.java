package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class CannonballEntity extends BaseBullet implements HardProjectile {
	public CannonballEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public CannonballEntity(Level world) {
		super(AoAProjectiles.CANNONBALL.get(), world);
	}

	public CannonballEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAProjectiles.CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue, xMod, yMod, zMod);
	}

	public CannonballEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public CannonballEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.CANNONBALL.get(), world, x, y, z);
	}
}
