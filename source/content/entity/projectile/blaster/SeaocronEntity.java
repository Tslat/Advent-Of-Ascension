package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class SeaocronEntity extends BaseEnergyShot {
	public SeaocronEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SeaocronEntity(World world) {
		super(AoAEntities.Projectiles.SEAOCRON_SHOT.get(), world);
	}

	public SeaocronEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.SEAOCRON_SHOT.get(), shooter, weapon, maxAge);
	}

	public SeaocronEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SEAOCRON_SHOT.get(), world, x, y, z);
	}
}
