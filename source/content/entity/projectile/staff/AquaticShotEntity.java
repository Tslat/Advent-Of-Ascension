package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class AquaticShotEntity extends BaseEnergyShot {
	public AquaticShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public AquaticShotEntity(Level world) {
		super(AoAProjectiles.AQUATIC_SHOT.get(), world);
	}

	public AquaticShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.AQUATIC_SHOT.get(), shooter, weapon, maxAge);
	}

	public AquaticShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.AQUATIC_SHOT.get(), world, x, y, z);
	}
}
