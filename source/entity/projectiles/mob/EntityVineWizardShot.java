package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.library.Enums;

public class EntityVineWizardShot extends BaseMobProjectile {
	public EntityVineWizardShot(World world) {
		super(world);
	}

	public EntityVineWizardShot(AoARangedMob shooter, Entity target, Enums.MobProjectileType projectileType) {
		super(shooter.world, shooter, target, projectileType);
	}

	@Override
	public float getGravityVelocity() {
		return 0;
	}
}
