package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.base.AoARangedMob;

public class SpiritualShotEntity extends BaseMobProjectile {
	public SpiritualShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SpiritualShotEntity(World world) {
		super(AoAEntities.Projectiles.SPIRITUAL_SHOT.get(), world);
	}

	public SpiritualShotEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.SPIRITUAL_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
