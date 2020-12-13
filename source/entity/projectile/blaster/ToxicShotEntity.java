package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class ToxicShotEntity extends BaseEnergyShot {
	public ToxicShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public ToxicShotEntity(World world) {
		super(AoAEntities.Projectiles.TOXIC_SHOT.get(), world);
	}

	public ToxicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.TOXIC_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public ToxicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.TOXIC_SHOT.get(), shooter, weapon, maxAge);
	}

	public ToxicShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.TOXIC_SHOT.get(), world, x, y, z);
	}
}
