package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;

public class ConstructTerrorShotEntity extends BaseMobProjectile {
	public ConstructTerrorShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ConstructTerrorShotEntity(Level world) {
		super(AoAProjectiles.TERROR_CONSTRUCT_SHOT.get(), world);
	}

	public ConstructTerrorShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAProjectiles.TERROR_CONSTRUCT_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
