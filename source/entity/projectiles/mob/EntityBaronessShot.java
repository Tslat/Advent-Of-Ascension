package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.library.Enums;

public class EntityBaronessShot extends BaseMobProjectile {
	public EntityBaronessShot(World world) {
		super(world);
	}

	public EntityBaronessShot(AoARangedMob shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
