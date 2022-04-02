/*
package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

import net.tslat.aoa3.content.entity.boss.ShadowlordEntity;

public class ShadowlordShotEntity extends BaseMobProjectile {
	public boolean toggle = false;
	public int counter = 0;

	public ShadowlordShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public ShadowlordShotEntity(Level world) {
		super(AoAProjectiles.SHADOWLORD_SHOT.get(), world);
	}

	public ShadowlordShotEntity(ShadowlordEntity shooter, Type projectileType) {
		super(AoAProjectiles.SHADOWLORD_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
*/
