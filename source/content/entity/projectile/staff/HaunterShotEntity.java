package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;

public class HaunterShotEntity extends NonPhysicalWeaponProjectile {
	public HaunterShotEntity(EntityType<? extends HaunterShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HaunterShotEntity(EntityType<? extends HaunterShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HaunterShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HAUNTER_SHOT.get(), level, context);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide && this.tickCount % 4 == 0)
			AoAExplosionBuilder.at(this, AoAExplosions.HAUNTER_SHOT, StandardExplosion::new).explode();
	}
}
