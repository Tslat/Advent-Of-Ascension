package net.tslat.aoa3.entity.projectiles.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.EntityUtil;

public class EntitySelyanSticklerStuck extends EntityThrowable {
	private EntityLivingBase target;
	private EntityLivingBase shooter;
	private int age;

	public EntitySelyanSticklerStuck(World world) {
		super(world);
	}

	public EntitySelyanSticklerStuck(EntityLivingBase shooter, BaseGun gun, EntityLivingBase target, float bulletDmgMultiplier) {
		super(shooter.world);
		this.target = target;
		this.shooter = shooter;
		setLocationAndAngles(target.posX, target.posY + target.getEyeHeight(), target.posZ, 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public EntitySelyanSticklerStuck(World world, double x, double y, double z) {
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
			EntityUtil.healEntity(shooter, 0.03f);
		}
		else {
			world.createExplosion(shooter, posX, posY, posZ, 2.0f, false);

			if (!world.isRemote)
				setDead();
		}

		if (age >= 100) {
			world.createExplosion(shooter, posX, posY, posZ, 2.0f, false);

			if (!world.isRemote)
				setDead();
		}
	}
}
