package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.library.Enums;

public class EntitySpectralShot extends BaseMobProjectile {
	public EntitySpectralShot(World world) {
		super(world);
	}

	public EntitySpectralShot(AoARangedAttacker shooter, Enums.MobProjectileType projectileType) {
		super(((Entity)shooter).world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
