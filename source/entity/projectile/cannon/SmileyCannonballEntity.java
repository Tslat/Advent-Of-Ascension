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

public class SmileyCannonballEntity extends BaseBullet implements HardProjectile {
	public SmileyCannonballEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public SmileyCannonballEntity(World world) {
		super(AoAEntities.Projectiles.SMILEY_CANNONBALL.get(), world);
	}

	public SmileyCannonballEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SMILEY_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public SmileyCannonballEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SMILEY_CANNONBALL.get(), world, x, y, z);
	}

}
