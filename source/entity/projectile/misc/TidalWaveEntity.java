package net.tslat.aoa3.entity.projectile.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
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

		shootFromRotation(shooter, shooter.xRot, shooter.yRot, 0, 1.5f, 0.05f);
		setPos(getX() + xOffset, getY(), getZ() + zOffset);
	}

	@Override
	public float getGravity() {
		return (tickCount / 3) % 2 == 0 ? -0.1f : 0.1f;
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public void tick() {
		if (tickCount > 20) {
			remove();

			return;
		}

		super.tick();
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (!level.isClientSide) {
			if (result.getType() == RayTraceResult.Type.BLOCK) {
				remove();
			}
			else if (result.getType() == RayTraceResult.Type.ENTITY) {
				EntityRayTraceResult rayTraceResult = (EntityRayTraceResult)result;

				if (rayTraceResult.getEntity() != getOwner()) {
					Vector3d motion = getDeltaMovement();

					rayTraceResult.getEntity().push(motion.x() * 0.3, motion.y() * 0.3, motion.z() * 0.3);
					rayTraceResult.getEntity().hurtMarked = true;
				}
			}
		}
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
