package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.mobs.creeponia.EntityCreeperlock;
import net.tslat.aoa3.entity.mobs.creeponia.EntityMagicalCreeper;
import net.tslat.aoa3.library.Enums;

public class EntityCreeperShot extends BaseMobProjectile {
	public EntityCreeperShot(World world) {
		super(world);
	}

	public EntityCreeperShot(EntityCreeperlock shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	public EntityCreeperShot(EntityMagicalCreeper shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
