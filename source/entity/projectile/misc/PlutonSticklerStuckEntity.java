package net.tslat.aoa3.entity.projectile.misc;

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
import net.tslat.aoa3.item.weapon.gun.BaseGun;
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
		super(AoAEntities.Projectiles.PLUTON_STICKLER_STUCK.get(), shooter.world);
		this.target = target;
		this.shooter = shooter;
		setLocationAndAngles(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public PlutonSticklerStuckEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.PLUTON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}

	@Override
	protected void onImpact(RayTraceResult result) {}

	@Override
	protected void registerData() {}

	@Override
	public void tick() {
		super.tick();

		age++;

		if (world.isRemote)
			return;

		if (target != null && target.isAlive()) {
			setLocationAndAngles(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ(), 0, 360);
		}
		else {
			WorldUtil.createExplosion(shooter, world, this, 2.0f);
			explodeCoins();

			if (!world.isRemote)
				remove();
		}

		if (age >= 100) {
			WorldUtil.createExplosion(shooter, world, getPosX(), getPosY() + 1, getPosZ(), 2.0f);
			explodeCoins();

			if (!world.isRemote)
				remove();
		}
	}

	private void explodeCoins() {
		for (float x = -0.5f; x <= 0.5f; x += 0.5f) {
			for (float y = -0.5f; y <= 0.5f; y += 0.5f) {
				for (float z = -0.5f; z <= 0.5f; z += 0.5f) {
					ItemEntity coin = new ItemEntity(world, getPosX(), getPosY(), getPosZ(), new ItemStack(AoAItems.COPPER_COIN.get()));

					coin.setPickupDelay(120);
					coin.addVelocity(x, y, z);
					coin.lifespan = 140;
					world.addEntity(coin);
				}
			}
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
