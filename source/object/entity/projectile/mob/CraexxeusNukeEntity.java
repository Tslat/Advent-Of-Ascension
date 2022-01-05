package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.base.AoAFlyingRangedMob;

public class CraexxeusNukeEntity extends BaseMobProjectile {
	public CraexxeusNukeEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public CraexxeusNukeEntity(World world) {
		super(AoAEntities.Projectiles.CRAEXXEUS_NUKE.get(), world);
	}

	public CraexxeusNukeEntity(AoAFlyingRangedMob shooter, Type projectileType) {
		super(AoAEntities.Projectiles.CRAEXXEUS_NUKE.get(), shooter.level, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
