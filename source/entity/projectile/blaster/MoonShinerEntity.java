package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class MoonShinerEntity extends BaseEnergyShot {
	public MoonShinerEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public MoonShinerEntity(World world) {
		super(AoAEntities.Projectiles.MOON_SHINER_SHOT.get(), world);
	}

	public MoonShinerEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.MOON_SHINER_SHOT.get(), shooter, weapon, maxAge);
	}

	public MoonShinerEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MOON_SHINER_SHOT.get(), world, x, y, z);
	}
}
