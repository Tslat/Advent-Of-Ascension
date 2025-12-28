package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class EradicatorShotEntity extends NonPhysicalWeaponProjectile {
	public EradicatorShotEntity(EntityType<? extends EradicatorShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public EradicatorShotEntity(EntityType<? extends EradicatorShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public EradicatorShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.ERADICATOR_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.3f;
	}
}
