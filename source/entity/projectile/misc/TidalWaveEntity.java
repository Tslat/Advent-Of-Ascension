package net.tslat.aoa3.entity.projectile.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;

public class TidalWaveEntity extends ThrowableEntity {
	public TidalWaveEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public TidalWaveEntity(World world) {
		super(AoAEntities.Projectiles.TIDAL_WAVE.get(), world);
	}

	public TidalWaveEntity(World world, LivingEntity shooter, double xOffset, double zOffset) {
		super(AoAEntities.Projectiles.TIDAL_WAVE.get(), shooter, world);

		shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0, 1.5f, 0.05f);
		setPosition(getPosX() + xOffset, getPosY(), getPosZ() + zOffset);
	}

	@Override
	public float getGravityVelocity() {
		return (ticksExisted / 3) % 2 == 0 ? -0.1f : 0.1f;
	}

	@Override
	protected void registerData() {}

	@Override
	public void tick() {
		if (ticksExisted > 20) {
			remove();

			return;
		}

		super.tick();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.getType() == RayTraceResult.Type.BLOCK) {
				remove();
			}
			else if (result.getType() == RayTraceResult.Type.ENTITY) {
				EntityRayTraceResult rayTraceResult = (EntityRayTraceResult)result;

				if (rayTraceResult.getEntity() != owner) {
					Vec3d motion = getMotion();

					rayTraceResult.getEntity().addVelocity(motion.getX() * 0.3, motion.getY() * 0.3, motion.getZ() * 0.3);
					rayTraceResult.getEntity().velocityChanged = true;
				}
			}
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
