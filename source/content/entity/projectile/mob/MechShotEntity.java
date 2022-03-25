package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.boss.MechbotEntity;

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
