package net.tslat.aoa3.entity.projectiles.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityPlutonSticklerStuck extends EntityThrowable {
	private EntityLivingBase target;
	private EntityLivingBase shooter;
	private int age;

	public EntityPlutonSticklerStuck(World world) {
		super(world);
	}

	public EntityPlutonSticklerStuck(EntityLivingBase shooter, BaseGun gun, EntityLivingBase target, float bulletDmgMultiplier) {
		super(shooter.world);
		this.target = target;
		this.shooter = shooter;
		setLocationAndAngles(target.posX, target.posY + target.getEyeHeight(), target.posZ, 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public EntityPlutonSticklerStuck(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}

	@Override
	protected void onImpact(RayTraceResult result) {}

	@Override
	public void onUpdate() {
		super.onUpdate();

		age++;

		if (world.isRemote)
			return;

		if (target != null && !target.isDead) {
			setLocationAndAngles(target.posX, target.posY + target.getEyeHeight(), target.posZ, 0, 360);
		}
		else {
			WorldUtil.createExplosion(shooter, world, this, 2.0f);
			explodeCoins();

			if (!world.isRemote)
				setDead();
		}

		if (age >= 100) {
			WorldUtil.createExplosion(shooter, world, posX, posY + 1, posZ, 2.0f);
			explodeCoins();

			if (!world.isRemote)
				setDead();
		}
	}

	private void explodeCoins() {
		for (float x = -0.5f; x <= 0.5f; x += 0.5f) {
			for (float y = -0.5f; y <= 0.5f; y += 0.5f) {
				for (float z = -0.5f; z <= 0.5f; z += 0.5f) {
					EntityItem coin = new EntityItem(world, posX, posY, posZ, new ItemStack(ItemRegister.coinCopper));

					coin.setPickupDelay(120);
					coin.addVelocity(x, y, z);
					coin.lifespan = 140;
					world.spawnEntity(coin);
				}
			}
		}
	}
}
