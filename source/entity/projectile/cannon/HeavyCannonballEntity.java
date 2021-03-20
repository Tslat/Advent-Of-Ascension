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

public class HeavyCannonballEntity extends BaseBullet implements HardProjectile {
	public HeavyCannonballEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HeavyCannonballEntity(World world) {
		super(AoAEntities.Projectiles.HEAVY_CANNONBALL.get(), world);
	}

	public HeavyCannonballEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.HEAVY_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue, xMod, yMod, zMod);
	}

	public HeavyCannonballEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HEAVY_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HeavyCannonballEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HEAVY_CANNONBALL.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}
}
