package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class VineWizardShotEntity extends BaseMobProjectile {
	public VineWizardShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public VineWizardShotEntity(Level world) {
		super(AoAProjectiles.VINE_WIZARD_SHOT.get(), world);
	}

	public VineWizardShotEntity(AoARangedMob shooter, Entity target, Type projectileType) {
		super(AoAProjectiles.VINE_WIZARD_SHOT.get(), shooter.level, shooter, target, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
