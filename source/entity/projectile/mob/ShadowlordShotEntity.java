package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.ShadowlordEntity;

import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile.Type;

public class ShadowlordShotEntity extends BaseMobProjectile {
	public boolean toggle = false;
	public int counter = 0;

	public ShadowlordShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ShadowlordShotEntity(World world) {
		super(AoAEntities.Projectiles.SHADOWLORD_SHOT.get(), world);
	}

	public ShadowlordShotEntity(ShadowlordEntity shooter, Type projectileType) {
		super(AoAEntities.Projectiles.SHADOWLORD_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
