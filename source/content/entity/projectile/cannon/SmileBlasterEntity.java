package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class SmileBlasterEntity extends PhysicalWeaponProjectile {
	public int particleColourStage = 24;

	public SmileBlasterEntity(EntityType<? extends SmileBlasterEntity> entityType, Level level) {
		super(entityType, level);
	}

	public SmileBlasterEntity(EntityType<? extends SmileBlasterEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public SmileBlasterEntity(Level level, WeaponFiringContext context) {
		super(AoAProjectiles.SMILE_BLASTER.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}
}
