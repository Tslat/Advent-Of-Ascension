package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

public class GooBallEntity extends BaseBullet implements HardProjectile, ItemSupplier {
	public GooBallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public GooBallEntity(Level world) {
		super(AoAProjectiles.GOO_BALL.get(), world);
	}

	public GooBallEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAProjectiles.GOO_BALL.get(), shooter, gun, 1.0f, 0, 3.0f);
	}

	public GooBallEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.GOO_BALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public GooBallEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.GOO_BALL.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}

	@Override
	public void doEntityImpact(Entity target, Vec3 impactLocation) {
		if (DamageUtil.doProjectileAttack(getOwner(), this, target, AoAWeapons.GOO_BALL.get().getDamage()))
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 60).level(2));

		level().playSound(null, getX(), getY(), getZ(), AoASounds.GOO_BALL_IMPACT.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.GOO_BALL.get());
	}
}
