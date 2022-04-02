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

public class SmileBlasterEntity extends BaseBullet implements HardProjectile {
	public SmileBlasterEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public SmileBlasterEntity(Level world) {
		super(AoAProjectiles.SMILE_BLASTER.get(), world);
	}

	public SmileBlasterEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.SMILE_BLASTER.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public SmileBlasterEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.SMILE_BLASTER.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
