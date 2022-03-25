package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class MoonShotEntity extends BaseBullet implements HardProjectile {
	public float yOffset1;
	public float yOffset2;
	public boolean toggle1;
	public boolean toggle2 = true;

	public MoonShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public MoonShotEntity(World world) {
		super(AoAEntities.Projectiles.MOON_SHOT.get(), world);
	}

	public MoonShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.MOON_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public MoonShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MOON_SHOT.get(), world, x, y, z);
	}
}
