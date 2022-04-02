package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class AquaballEntity extends BaseMobProjectile {
	public AquaballEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public AquaballEntity(Level world) {
		super(AoAProjectiles.AQUABALL.get(), world);
	}

	public AquaballEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.AQUABALL.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
