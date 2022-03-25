package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class SkulloShotEntity extends BaseEnergyShot {
	public SkulloShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SkulloShotEntity(World world) {
		super(AoAEntities.Projectiles.SKULLO_SHOT.get(), world);
	}

	public SkulloShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.SKULLO_SHOT.get(), shooter, weapon, maxAge);
	}

	public SkulloShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SKULLO_SHOT.get(), world, x, y, z);
	}
}
