package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class ReeferShotEntity extends NonPhysicalWeaponProjectile {
	public ReeferShotEntity(EntityType<? extends ReeferShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public ReeferShotEntity(EntityType<? extends ReeferShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public ReeferShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.REEFER_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.06f;
	}
}
