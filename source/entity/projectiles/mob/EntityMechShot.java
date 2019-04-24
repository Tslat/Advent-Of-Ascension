package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.boss.mechbot.EntityMechbot;
import net.tslat.aoa3.library.Enums;

public class EntityMechShot extends BaseMobProjectile {
	public EntityMechShot(World world) {
		super(world);
	}

	public EntityMechShot(EntityMechbot shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
