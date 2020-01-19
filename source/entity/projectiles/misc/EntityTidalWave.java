package net.tslat.aoa3.entity.projectiles.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTidalWave extends EntityThrowable {
	public EntityTidalWave(World world) {
		super(world);
	}

	public EntityTidalWave(World world, EntityLivingBase shooter, double xOffset, double zOffset) {
		super(world, shooter);
		shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0, 1.5f, 0.05f);
		setPosition(posX + xOffset, posY, posZ + zOffset);
	}

	@Override
	public float getGravityVelocity() {
		return (ticksExisted / 3) % 2 == 0 ? -0.1f : 0.1f;
	}

	@Override
	public void onUpdate() {
		if (ticksExisted > 20) {
			setDead();

			return;
		}

		super.onUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.typeOfHit == RayTraceResult.Type.BLOCK)
				setDead();

			if (result.entityHit != null && result.entityHit != thrower) {
				result.entityHit.addVelocity(motionX * 0.3, motionY * 0.3, motionZ * 0.3);

				result.entityHit.velocityChanged = true;
			}
		}
	}
}
