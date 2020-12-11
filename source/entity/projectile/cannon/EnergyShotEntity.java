package net.tslat.aoa3.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EnergyShotEntity extends BaseBullet implements HardProjectile {
	public float yOffset1;
	public float yOffset2;
	public boolean toggle1;
	public boolean toggle2 = true;

	public EnergyShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public EnergyShotEntity(World world) {
		super(AoAEntities.Projectiles.ENERGY_SHOT.get(), world);
	}

	public EnergyShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.ENERGY_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EnergyShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ENERGY_SHOT.get(), world, x, y, z);
	}
}
