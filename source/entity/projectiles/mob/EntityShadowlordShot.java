package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.boss.shadowlord.EntityShadowlord;
import net.tslat.aoa3.library.Enums;

public class EntityShadowlordShot extends BaseMobProjectile {
	public boolean toggle = false;
	public int counter = 0;

	public EntityShadowlordShot(World world) {
		super(world);
	}

	public EntityShadowlordShot(EntityShadowlord shooter, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}
}
