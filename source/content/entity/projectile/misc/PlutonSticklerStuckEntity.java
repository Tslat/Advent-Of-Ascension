package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class PlutonSticklerStuckEntity extends ThrowableProjectile {
	private LivingEntity target;
	private LivingEntity shooter;
	private int age;

	public PlutonSticklerStuckEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public PlutonSticklerStuckEntity(Level world) {
		super(AoAProjectiles.PLUTON_STICKLER_STUCK.get(), world);
	}

	public PlutonSticklerStuckEntity(LivingEntity shooter, BaseGun gun, LivingEntity target, float bulletDmgMultiplier) {
		super(AoAProjectiles.PLUTON_STICKLER_STUCK.get(), shooter.level());
		this.target = target;
		this.shooter = shooter;
		moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public PlutonSticklerStuckEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.PLUTON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}

	@Override
	protected void onHit(HitResult result) {}

	@Override
	protected void defineSynchedData() {}

	@Override
	public void tick() {
		super.tick();

		age++;

		if (level().isClientSide)
			return;

		if (target != null && target.isAlive()) {
			moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 360);
		}
		else {
			WorldUtil.createExplosion(shooter, level(), this, 2.0f);
			explodeCoins();

			if (!level().isClientSide)
				discard();
		}

		if (age >= 100) {
			WorldUtil.createExplosion(shooter, level(), getX(), getY() + 1, getZ(), 2.0f);
			explodeCoins();

			if (!level().isClientSide)
				discard();
		}
	}

	private void explodeCoins() {
		for (float x = -0.5f; x <= 0.5f; x += 0.5f) {
			for (float y = -0.5f; y <= 0.5f; y += 0.5f) {
				for (float z = -0.5f; z <= 0.5f; z += 0.5f) {
					ItemEntity coin = new ItemEntity(level(), getX(), getY(), getZ(), new ItemStack(AoAItems.COPPER_COIN.get()));

					coin.setPickUpDelay(120);
					coin.push(x, y, z);
					coin.lifespan = 140;
					level().addFreshEntity(coin);
				}
			}
		}
	}
}
