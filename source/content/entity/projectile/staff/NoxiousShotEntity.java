package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class NoxiousShotEntity extends NonPhysicalWeaponProjectile {
	public NoxiousShotEntity(EntityType<? extends NoxiousShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public NoxiousShotEntity(EntityType<? extends NoxiousShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public NoxiousShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.NOXIOUS_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.05f;
	}
}
