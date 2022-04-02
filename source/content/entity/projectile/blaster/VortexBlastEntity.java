package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class VortexBlastEntity extends BaseEnergyShot {
	public VortexBlastEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public VortexBlastEntity(Level world) {
		super(AoAProjectiles.VORTEX_BLAST.get(), world);
	}

	public VortexBlastEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.VORTEX_BLAST.get(), shooter, weapon, maxAge);
	}

	public VortexBlastEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.VORTEX_BLAST.get(), world, x, y, z);
	}
}
