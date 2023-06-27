package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class HeavyRunicGuardianShotEntity extends BaseMobProjectile {
	public HeavyRunicGuardianShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public HeavyRunicGuardianShotEntity(Level world) {
		super(AoAProjectiles.HEAVY_RUNIC_GUARDIAN_SHOT.get(), world);
	}

	public HeavyRunicGuardianShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.HEAVY_RUNIC_GUARDIAN_SHOT.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.095f;
	}
}
