package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class BonePelletEntity extends BaseEnergyShot {
	public BonePelletEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public BonePelletEntity(World world) {
		super(AoAEntities.Projectiles.BONE_PELLET.get(), world);
	}

	public BonePelletEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.BONE_PELLET.get(), shooter, weapon, maxAge);
	}

	public BonePelletEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BONE_PELLET.get(), world, x, y, z);
	}
}
