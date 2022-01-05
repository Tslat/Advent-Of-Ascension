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

public class HeavyTriDischargeShotEntity extends BaseBullet implements HardProjectile {
	public HeavyTriDischargeShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HeavyTriDischargeShotEntity(World world) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), world);
	}

	public HeavyTriDischargeShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public HeavyTriDischargeShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HeavyTriDischargeShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HEAVY_TRI_DISCHARGE_SHOT.get(), world, x, y, z);
	}
}
