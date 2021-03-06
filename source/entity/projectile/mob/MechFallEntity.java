package net.tslat.aoa3.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.MechbotEntity;

import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile.Type;

public class MechFallEntity extends BaseMobProjectile {
	public MechFallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public MechFallEntity(World world) {
		super(AoAEntities.Projectiles.MECH_FALL.get(), world);
	}

	public MechFallEntity(MechbotEntity shooter, double posX, double posY, double posZ, Type projectileType) {
		super(AoAEntities.Projectiles.MECH_FALL.get(), shooter.level, shooter, posX, posY, posZ, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
