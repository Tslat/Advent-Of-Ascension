package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.WorldUtil;

public class GrenadeEntity extends BaseBullet implements HardProjectile {
	private float explosionStrength = 1.5f;

	public GrenadeEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public GrenadeEntity(Level world) {
		super(AoAProjectiles.GRENADE.get(), world);
	}

	public GrenadeEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAProjectiles.GRENADE.get(), shooter, gun, 1.0f, 0, 1.5f);
	}

	public GrenadeEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.GRENADE.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public GrenadeEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.GRENADE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source == DamageSource.ON_FIRE || source == DamageSource.LAVA) {
			doImpactEffect();

			if (getOwner() instanceof ServerPlayer pl) {
				pl.getAdvancements().award(AdvancementUtil.getAdvancement(AdventOfAscension.id("completionist/darwin_award")), "fire_grenade");
			}

			discard();

			return true;
		}

		return super.hurt(source, amount);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level, this, explosionStrength);
	}

	@Override
	public void doEntityImpact(Entity target) {
		WorldUtil.createExplosion(getOwner(), level, this, explosionStrength);
	}
}
