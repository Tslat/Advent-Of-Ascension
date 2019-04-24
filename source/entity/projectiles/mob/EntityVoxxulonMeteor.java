package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.boss.voxxulon.EntityVoxxulon;
import net.tslat.aoa3.library.Enums;

public class EntityVoxxulonMeteor extends BaseMobProjectile {
	public EntityVoxxulonMeteor(World world) {
		super(world);
	}

	public EntityVoxxulonMeteor(EntityVoxxulon shooter, Entity target, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, target, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0;
	}
}
