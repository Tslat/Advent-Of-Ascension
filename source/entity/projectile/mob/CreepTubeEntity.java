package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.util.WorldUtil;

public class CreepTubeEntity extends BaseMobProjectile {
	public CreepTubeEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public CreepTubeEntity(World world) {
		super(AoAEntities.Projectiles.CREEP_TUBE.get(), world);
	}

	public CreepTubeEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CREEP_TUBE.get(), shooter.world, shooter, projectileType);
	}

	@Override
	public void tick() {
		super.tick();

		if (!world.isRemote && ticksExisted % 4 == 0)
			WorldUtil.createExplosion(getThrower(), world, this, 2f);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
