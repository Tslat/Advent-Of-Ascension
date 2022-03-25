package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class LyonicShotEntity extends BaseEnergyShot {
	public LyonicShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public LyonicShotEntity(World world) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), world);
	}

	public LyonicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), shooter, weapon, maxAge);
	}

	public LyonicShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), shooter, weapon, maxAge, xMod, yMod, zMod);
	}

	public LyonicShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LYONIC_SHOT.get(), world, x, y, z);
	}
}
