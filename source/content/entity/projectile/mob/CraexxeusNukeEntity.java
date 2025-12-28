package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMobOld;

public class CraexxeusNukeEntity extends BaseMobProjectile {
	public CraexxeusNukeEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public CraexxeusNukeEntity(Level world) {
		super(AoAProjectiles.CRAEXXEUS_NUKE.get(), world);
	}

	public CraexxeusNukeEntity(AoAFlyingRangedMobOld shooter, Type projectileType) {
		super(AoAProjectiles.CRAEXXEUS_NUKE.get(), shooter.level(), shooter, projectileType);
	}

	@Override
	public double getDefaultGravity() {
		return 0;
	}
}
