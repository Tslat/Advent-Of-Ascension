package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class VortexBlastEntity extends BaseEnergyShot {
	public VortexBlastEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public VortexBlastEntity(World world) {
		super(AoAEntities.Projectiles.VORTEX_BLAST.get(), world);
	}

	public VortexBlastEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.VORTEX_BLAST.get(), shooter, weapon, maxAge);
	}

	public VortexBlastEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.VORTEX_BLAST.get(), world, x, y, z);
	}
}
