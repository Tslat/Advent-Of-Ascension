package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class HeavyBoneCannonballEntity extends PhysicalWeaponProjectile {
	public HeavyBoneCannonballEntity(EntityType<? extends HeavyBoneCannonballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HeavyBoneCannonballEntity(EntityType<? extends HeavyBoneCannonballEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HeavyBoneCannonballEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HEAVY_BONE_CANNONBALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}
}
