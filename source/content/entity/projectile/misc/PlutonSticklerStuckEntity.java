package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class PlutonSticklerStuckEntity extends ThrowableEntity {
	private LivingEntity target;
	private LivingEntity shooter;
	private int age;

	public PlutonSticklerStuckEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public PlutonSticklerStuckEntity(World world) {
		super(AoAEntities.Projectiles.PLUTON_STICKLER_STUCK.get(), world);
	}

	public PlutonSticklerStuckEntity(LivingEntity shooter, BaseGun gun, LivingEntity target, float bulletDmgMultiplier) {
		super(AoAEntities.Projectiles.PLUTON_STICKLER_STUCK.get(), shooter.level);
		this.target = target;
		this.shooter = shooter;
		moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public PlutonSticklerStuckEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.PLUTON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}

	@Override
	protected void onHit(RayTraceResult result) {}

	@Override
	protected void defineSynchedData() {}

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
			WorldUtil.createExplosion(shooter, level, this, 2.0f);
			explodeCoins();

			if (!level.isClientSide)
				remove();
		}

		if (age >= 100) {
			WorldUtil.createExplosion(shooter, level, getX(), getY() + 1, getZ(), 2.0f);
			explodeCoins();

			if (!level.isClientSide)
				remove();
		}
	}

	private void explodeCoins() {
		for (float x = -0.5f; x <= 0.5f; x += 0.5f) {
			for (float y = -0.5f; y <= 0.5f; y += 0.5f) {
				for (float z = -0.5f; z <= 0.5f; z += 0.5f) {
					ItemEntity coin = new ItemEntity(level, getX(), getY(), getZ(), new ItemStack(AoAItems.COPPER_COIN.get()));

					coin.setPickUpDelay(120);
					coin.push(x, y, z);
					coin.lifespan = 140;
					level.addFreshEntity(coin);
				}
			}
		}
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
