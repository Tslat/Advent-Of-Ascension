package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class BeamerShotEntity extends BaseEnergyShot {
	public BeamerShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public BeamerShotEntity(World world) {
		super(AoAEntities.Projectiles.BEAMER_SHOT.get(), world);
	}

	public BeamerShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.BEAMER_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public BeamerShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BEAMER_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.4f;
	}
}
