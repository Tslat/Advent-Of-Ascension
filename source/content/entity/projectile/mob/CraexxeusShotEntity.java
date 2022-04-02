package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;

public class CraexxeusShotEntity extends BaseMobProjectile {
	public CraexxeusShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public CraexxeusShotEntity(Level world) {
		super(AoAProjectiles.CRAEXXEUS_SHOT.get(), world);
	}

	public CraexxeusShotEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAProjectiles.CRAEXXEUS_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
