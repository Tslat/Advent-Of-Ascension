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
import net.tslat.aoa3.util.WorldUtil;


public class TriDischargeShotEntity extends BaseBullet implements HardProjectile {
	public TriDischargeShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public TriDischargeShotEntity(World world) {
		super(AoAEntities.Projectiles.TRI_DISCHARGE_SHOT.get(), world);
	}

	public TriDischargeShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAEntities.Projectiles.TRI_DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public TriDischargeShotEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.TRI_DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public TriDischargeShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.TRI_DISCHARGE_SHOT.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level, this, 3.0f);
	}
}
