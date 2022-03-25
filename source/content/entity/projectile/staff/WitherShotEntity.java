package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class WitherShotEntity extends BaseEnergyShot {
	public WitherShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public WitherShotEntity(World world) {
		super(AoAEntities.Projectiles.WITHER_SHOT.get(), world);
	}

	public WitherShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.WITHER_SHOT.get(), shooter, weapon, maxAge);
	}

	public WitherShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.WITHER_SHOT.get(), world, x, y, z);
	}
}
