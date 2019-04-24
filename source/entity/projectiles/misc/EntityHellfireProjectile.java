package net.tslat.aoa3.entity.projectiles.misc;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.thrown.EntityHellfire;

public class EntityHellfireProjectile extends EntityThrowable {
	public EntityHellfireProjectile(World world) {
		super(world);
	}

	public EntityHellfireProjectile(EntityHellfire source, double targetPosX, double targetPosY, double targetPosZ) {
		super(source.world, source.posX, source.posY, source.posZ);
		motionX = targetPosX - source.posX;
		motionY = targetPosY - source.posY;
		motionZ = targetPosZ - source.posZ;
		shoot(motionX, motionY + 0.5, motionZ, 1.5f, 1.0f);
	}

	@Override
	public float getGravityVelocity() {
		if (ticksExisted >= 5)
			return 1.0f;

		return 0.0f;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote)
			setDead();
	}
}
