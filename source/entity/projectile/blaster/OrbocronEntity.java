package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class OrbocronEntity extends BaseEnergyShot {
	public OrbocronEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public OrbocronEntity(World world) {
		super(AoAEntities.Projectiles.ORBOCRON_SHOT.get(), world);
	}

	public OrbocronEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ORBOCRON_SHOT.get(), shooter, weapon, maxAge);
	}

	public OrbocronEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ORBOCRON_SHOT.get(), world, x, y, z);
	}
}
