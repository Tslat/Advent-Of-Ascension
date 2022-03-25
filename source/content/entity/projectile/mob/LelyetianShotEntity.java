package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class LelyetianShotEntity extends BaseMobProjectile {
	public LelyetianShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public LelyetianShotEntity(World world) {
		super(AoAEntities.Projectiles.LELYETIAN_SHOT.get(), world);
	}

	public LelyetianShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.LELYETIAN_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
