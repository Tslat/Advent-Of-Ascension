package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

public class LuxonSticklerStuckEntity extends ThrowableProjectile {
	private LivingEntity target;
	private LivingEntity shooter;
	private int age;

	public LuxonSticklerStuckEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public LuxonSticklerStuckEntity(Level world) {
		super(AoAProjectiles.LUXON_STICKLER_STUCK.get(), world);
	}

	public LuxonSticklerStuckEntity(LivingEntity shooter, BaseGun gun, LivingEntity target, float bulletDmgMultiplier) {
		super(AoAProjectiles.LUXON_STICKLER_STUCK.get(), shooter.level);
		this.target = target;
		this.shooter = shooter;
		moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public LuxonSticklerStuckEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.LUXON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}

	@Override
	protected void onHit(HitResult result) {}

	@Override
	public void tick() {
		super.tick();

		age++;

		if (level.isClientSide)
			return;

		if (target != null && target.isAlive()) {
			moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 360);
		}
		else {
			WorldUtil.createExplosion(getOwner(), level, this, 2.0f);

			if (!level.isClientSide)
				discard();

			return;
		}

		if (age >= 100) {
			WorldUtil.createExplosion(getOwner(), level, getX(), getY() + 1, getZ(), 2.0f);

			if (!level.isClientSide)
				discard();

			return;
		}

		if (level.getGameTime() % 40 == 0)
			EntityUtil.applyPotions(level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(7), EntityUtil.Predicates.HOSTILE_MOB), new EffectBuilder(MobEffects.GLOWING, 45));
	}

	@Override
	protected void defineSynchedData() {}
}
