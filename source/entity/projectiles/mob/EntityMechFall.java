package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.boss.mechbot.EntityMechbot;
import net.tslat.aoa3.library.Enums;

public class EntityMechFall extends BaseMobProjectile {
	public EntityMechFall(World world) {
		super(world);
	}

	public EntityMechFall(EntityMechbot shooter, double posX, double posY, double posZ, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, posX, posY, posZ, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0;
	}
}
