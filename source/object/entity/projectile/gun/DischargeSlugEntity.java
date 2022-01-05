package net.tslat.aoa3.object.entity.projectile.gun;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class DischargeSlugEntity extends BaseBullet implements HardProjectile {
	public DischargeSlugEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public DischargeSlugEntity(World world) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), world);
	}

	public DischargeSlugEntity(LivingEntity shooter, BaseGun gun, int piercingValue) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public DischargeSlugEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public DischargeSlugEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DISCHARGE_SLUG.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level, this, 1.8f);
	}
}
