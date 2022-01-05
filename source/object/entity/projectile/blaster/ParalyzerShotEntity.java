package net.tslat.aoa3.object.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class ParalyzerShotEntity extends BaseEnergyShot {
	public ParalyzerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public ParalyzerShotEntity(World world) {
		super(AoAEntities.Projectiles.PARALYZER_SHOT.get(), world);
	}

	public ParalyzerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.PARALYZER_SHOT.get(), shooter, weapon, maxAge);
	}

	public ParalyzerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.PARALYZER_SHOT.get(), world, x, y, z);
	}
}
