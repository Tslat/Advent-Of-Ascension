package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;

public class CottonCandorShotEntity extends BaseMobProjectile {
	public CottonCandorShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CottonCandorShotEntity(Level world) {
		super(AoAProjectiles.COTTON_CANDOR_SHOT.get(), world);
	}

	public CottonCandorShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAProjectiles.COTTON_CANDOR_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
