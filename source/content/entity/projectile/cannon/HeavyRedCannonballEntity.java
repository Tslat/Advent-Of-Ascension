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

public class HeavyRedCannonballEntity extends BaseBullet implements HardProjectile {
	public HeavyRedCannonballEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HeavyRedCannonballEntity(World world) {
		super(AoAEntities.Projectiles.HEAVY_RED_CANNONBALL.get(), world);
	}

	public HeavyRedCannonballEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HEAVY_RED_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HeavyRedCannonballEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HEAVY_RED_CANNONBALL.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}