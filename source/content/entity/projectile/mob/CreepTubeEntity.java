package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.util.WorldUtil;

public class CreepTubeEntity extends BaseMobProjectile {
	public CreepTubeEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CreepTubeEntity(Level world) {
		super(AoAProjectiles.CREEP_TUBE.get(), world);
	}

	public CreepTubeEntity(AoARangedMob shooter, Type projectileType) {
		super(AoAProjectiles.CREEP_TUBE.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide && tickCount % 4 == 0)
			WorldUtil.createExplosion(getOwner(), level(), this, 2f);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
