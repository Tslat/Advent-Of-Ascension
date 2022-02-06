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

public class HeavyGrenadeEntity extends BaseBullet implements HardProjectile {
	public HeavyGrenadeEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public HeavyGrenadeEntity(World world) {
		super(AoAEntities.Projectiles.HEAVY_GRENADE.get(), world);
	}

	public HeavyGrenadeEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HEAVY_GRENADE.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public HeavyGrenadeEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HEAVY_GRENADE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}
