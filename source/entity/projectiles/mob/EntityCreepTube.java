package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityCreepTube extends BaseMobProjectile {
	public EntityCreepTube(World world) {
		super(world);
	}

	public EntityCreepTube(AoARangedMob shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote && ticksExisted % 4 == 0)
			WorldUtil.createExplosion(getThrower(), world, this, 2f);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
