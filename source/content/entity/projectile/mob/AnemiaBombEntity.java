package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;

public class AnemiaBombEntity extends BaseMobProjectile {
	public AnemiaBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public AnemiaBombEntity(Level world) {
		super(AoAProjectiles.ANEMIA_BOMB.get(), world);
	}

	public AnemiaBombEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAProjectiles.ANEMIA_BOMB.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
