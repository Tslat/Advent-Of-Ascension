package net.tslat.aoa3.object.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;

public class CannonballEntity extends BaseBullet implements HardProjectile {
	public CannonballEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public CannonballEntity(World world) {
		super(AoAEntities.Projectiles.CANNONBALL.get(), world);
	}

	public CannonballEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue, xMod, yMod, zMod);
	}

	public CannonballEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public CannonballEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CANNONBALL.get(), world, x, y, z);
	}
}
