package net.tslat.aoa3.entity.projectile.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.thrown.HellfireEntity;

public class HellfireProjectileEntity extends ThrowableEntity {
	public HellfireProjectileEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HellfireProjectileEntity(World world) {
		super(AoAEntities.Projectiles.HELLFIRE_TAIL.get(), world);
	}

	public HellfireProjectileEntity(HellfireEntity source, double targetPosX, double targetPosY, double targetPosZ) {
		super(AoAEntities.Projectiles.HELLFIRE_TAIL.get(), source.getPosX(), source.getPosY(), source.getPosZ(), source.world);

		Vec3d motion = new Vec3d(targetPosX - source.getPosX(), targetPosY - source.getPosY(), targetPosZ - source.getPosZ());

		setMotion(motion);
		shoot(motion.getX(), motion.getY() + 0.5, motion.getZ(), 1.5f, 1.0f);
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
			remove();
	}

	@Override
	protected void registerData() {}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
