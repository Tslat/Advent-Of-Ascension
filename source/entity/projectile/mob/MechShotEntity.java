package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.MechbotEntity;

import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile.Type;

public class MechShotEntity extends BaseMobProjectile {
	public MechShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public MechShotEntity(World world) {
		super(AoAEntities.Projectiles.MECH_SHOT.get(), world);
	}

	public MechShotEntity(MechbotEntity shooter, Type projectileType) {
		super(AoAEntities.Projectiles.MECH_SHOT.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}
}
