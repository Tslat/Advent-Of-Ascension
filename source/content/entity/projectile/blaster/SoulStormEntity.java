package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class SoulStormEntity extends NonPhysicalWeaponProjectile {
	public SoulStormEntity(EntityType<? extends SoulStormEntity> entityType, Level level) {
		super(entityType, level);
	}

	public SoulStormEntity(EntityType<? extends SoulStormEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public SoulStormEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.SOUL_STORM_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.6f;
	}
}
