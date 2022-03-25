package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoARangedMob;

public class VineWizardShotEntity extends BaseMobProjectile {
	public VineWizardShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public VineWizardShotEntity(World world) {
		super(AoAEntities.Projectiles.VINE_WIZARD_SHOT.get(), world);
	}

	public VineWizardShotEntity(AoARangedMob shooter, Entity target, Type projectileType) {
		super(AoAEntities.Projectiles.VINE_WIZARD_SHOT.get(), shooter.level, shooter, target, projectileType);
	}

	@Override
	public float getGravity() {
		return 0;
	}
}
