package net.tslat.aoa3.entity.projectiles.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityLuxonSticklerStuck extends EntityThrowable {
	private EntityLivingBase target;
	private EntityLivingBase shooter;
	private int age;

	public EntityLuxonSticklerStuck(World world) {
		super(world);
	}

	public EntityLuxonSticklerStuck(EntityLivingBase shooter, BaseGun gun, EntityLivingBase target, float bulletDmgMultiplier) {
		super(shooter.world);
		this.target = target;
		this.shooter = shooter;
		setLocationAndAngles(target.posX, target.posY + target.getEyeHeight(), target.posZ, 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public EntityLuxonSticklerStuck(World world, double x, double y, double z) {
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
			WorldUtil.createExplosion(thrower, world, this, 2.0f);

			if (!world.isRemote)
				setDead();

			return;
		}

		if (age >= 100) {
			WorldUtil.createExplosion(thrower, world, posX, posY + 1, posZ, 2.0f);

			if (!world.isRemote)
				setDead();

			return;
		}

		if (world.getTotalWorldTime() % 40 == 0) {
			for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(7), PredicateUtil.IS_HOSTILE_MOB)) {
				entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 45, 0, false, true));
			}
		}
	}
}
