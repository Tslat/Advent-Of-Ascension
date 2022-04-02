package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class GigaGreenBallEntity extends BaseBullet {
	public GigaGreenBallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public GigaGreenBallEntity(Level world) {
		super(AoAProjectiles.GIGA_GREEN_BALL.get(), world);
	}

	public GigaGreenBallEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.GIGA_GREEN_BALL.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public GigaGreenBallEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.GIGA_GREEN_BALL.get(), world, x, y, z);
	}
}
