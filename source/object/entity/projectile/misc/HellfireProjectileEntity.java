package net.tslat.aoa3.object.entity.projectile.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.thrown.HellfireEntity;

public class HellfireProjectileEntity extends ThrowableEntity {
	public HellfireProjectileEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HellfireProjectileEntity(World world) {
		super(AoAEntities.Projectiles.HELLFIRE_TAIL.get(), world);
	}

	public HellfireProjectileEntity(HellfireEntity source, double targetPosX, double targetPosY, double targetPosZ) {
		super(AoAEntities.Projectiles.HELLFIRE_TAIL.get(), source.getX(), source.getY(), source.getZ(), source.level);

		Vector3d motion = new Vector3d(targetPosX - source.getX(), targetPosY - source.getY(), targetPosZ - source.getZ());

		setDeltaMovement(motion);
		shoot(motion.x(), motion.y() + 0.5, motion.z(), 1.5f, 1.0f);
	}

	@Override
	public float getGravity() {
		if (tickCount >= 5)
			return 1.0f;

		return 0.0f;
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (!level.isClientSide)
			remove();
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
