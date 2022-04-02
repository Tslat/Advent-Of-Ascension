package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class LyonicShotEntity extends BaseEnergyShot {
	public LyonicShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public LyonicShotEntity(Level world) {
		super(AoAProjectiles.LYONIC_SHOT.get(), world);
	}

	public LyonicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.LYONIC_SHOT.get(), shooter, weapon, maxAge);
	}

	public LyonicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAProjectiles.LYONIC_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public LyonicShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.LYONIC_SHOT.get(), world, x, y, z);
	}
}
