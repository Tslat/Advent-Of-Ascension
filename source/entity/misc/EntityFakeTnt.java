package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFakeTnt extends EntityTNTPrimed {
	public EntityFakeTnt(World world, BlockPos position) {
		super(world);
		setLocationAndAngles(position.getX(), position.getY(), position.getZ(), rand.nextFloat() * 360.0f, 0.0f);
		setSize(1.0f, 1.0f);
	}

	public EntityFakeTnt(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (!hasNoGravity())
			motionY -= 0.03999999910593033D;

		move(MoverType.SELF, motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;

		if (onGround)
		{
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
			motionY *= -0.5D;
		}

		setFuse(getFuse() - 1);

		if (getFuse() <= 0) {
			setDead();

			if (!world.isRemote)
				explode();
		}
		else {
			handleWaterMovement();
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode() {
		world.createExplosion(this, posX, posY, posZ, 4.0f, false);
	}
}
