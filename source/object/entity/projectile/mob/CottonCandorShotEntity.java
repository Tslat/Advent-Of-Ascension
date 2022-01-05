package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.base.AoAFlyingRangedMob;

public class CottonCandorShotEntity extends BaseMobProjectile {
	public CottonCandorShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public CottonCandorShotEntity(World world) {
		super(AoAEntities.Projectiles.COTTON_CANDOR_SHOT.get(), world);
	}

	public CottonCandorShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.COTTON_CANDOR_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
