package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.library.Enums;

public class EntityPolytomShot extends BaseMobProjectile {
	public EntityPolytomShot(World world) {
		super(world);
	}

	public EntityPolytomShot(AoAFlyingRangedMob shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0;
	}
}
