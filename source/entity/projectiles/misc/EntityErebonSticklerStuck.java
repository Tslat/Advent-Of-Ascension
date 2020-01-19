package net.tslat.aoa3.entity.projectiles.misc;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityErebonSticklerStuck extends EntityThrowable {
	private EntityLivingBase target;
	private EntityLivingBase shooter;
	private int age;
	private EntityAreaEffectCloud cloud = null;

	public EntityErebonSticklerStuck(World world) {
		super(world);
	}

	public EntityErebonSticklerStuck(EntityLivingBase shooter, BaseGun gun, EntityLivingBase target, float bulletDmgMultiplier) {
		super(shooter.world);
		this.target = target;
		this.shooter = shooter;

		setLocationAndAngles(target.posX, target.posY + target.getEyeHeight(), target.posZ, 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public EntityErebonSticklerStuck(World world, double x, double y, double z) {
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

		if (world.isRemote)
			return;

		age++;

		if (target != null && !target.isDead) {
			setLocationAndAngles(target.posX, target.posY + target.getEyeHeight(), target.posZ, 0, 360);
		}
		else {
			WorldUtil.createExplosion(shooter, world, this, 2.0f);
			setDead();

			if (cloud != null)
				cloud.setDead();

			return;
		}

		if (age >= 100) {
			WorldUtil.createExplosion(thrower, world, posX, posY + 1, posZ, 2.0f);
			setDead();

			if (cloud != null)
				cloud.setDead();

			return;
		}

		if (cloud == null) {
			cloud = new EntityAreaEffectCloud(world, target.posX, target.posY, target.posZ);

			cloud.setDuration(100 - age);
			cloud.setRadius(2);
			cloud.addEffect(new PotionEffect(MobEffects.WITHER, 40, 0, false, true));
			world.spawnEntity(cloud);
		}
		else {
			cloud.setPositionAndUpdate(target.posX, target.posY, target.posZ);
		}
	}
}
