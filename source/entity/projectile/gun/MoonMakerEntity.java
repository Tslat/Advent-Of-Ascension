package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class MoonMakerEntity extends BaseBullet implements HardProjectile {
	public MoonMakerEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public MoonMakerEntity(World world) {
		super(AoAEntities.Projectiles.MOON_MAKER.get(), world);
	}

	public MoonMakerEntity(LivingEntity shooter, BaseGun gun, int piercingValue) {
		super(AoAEntities.Projectiles.MOON_MAKER.get(), shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public MoonMakerEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MOON_MAKER.get(), world, x, y, z);
	}
}
