package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class BaronessShotEntity extends BaseMobProjectile {
	public BaronessShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public BaronessShotEntity(World world) {
		super(AoAEntities.Projectiles.BARONESS_SHOT.get(), world);
	}

	public BaronessShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.BARONESS_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
