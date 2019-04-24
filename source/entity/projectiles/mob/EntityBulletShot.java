package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.library.Enums;

public class EntityBulletShot extends BaseMobProjectile {
	public EntityBulletShot(World world) {
		super(world);
	}

	public EntityBulletShot(AoAFlyingRangedMob shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0;
	}
}
