package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class OrangeCannonballEntity extends PhysicalWeaponProjectile {
	public OrangeCannonballEntity(EntityType<? extends OrangeCannonballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public OrangeCannonballEntity(EntityType<? extends OrangeCannonballEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public OrangeCannonballEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.ORANGE_CANNONBALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}
}
