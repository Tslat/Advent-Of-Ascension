package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;

public class TidalWaveEntity extends ThrowableProjectile {
	public TidalWaveEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public TidalWaveEntity(Level world) {
		super(AoAProjectiles.TIDAL_WAVE.get(), world);
	}

	public TidalWaveEntity(Level world, LivingEntity shooter, double xOffset, double zOffset) {
		super(AoAProjectiles.TIDAL_WAVE.get(), shooter, world);

		shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0, 1.5f, 0.05f);
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
			discard();

			return;
		}

		super.tick();
	}

	@Override
	protected void onHit(HitResult result) {
		if (!level.isClientSide) {
			if (result.getType() == HitResult.Type.BLOCK) {
				discard();
			}
			else if (result.getType() == HitResult.Type.ENTITY) {
				EntityHitResult rayTraceResult = (EntityHitResult)result;

				if (rayTraceResult.getEntity() != getOwner()) {
					Vec3 motion = getDeltaMovement();

					rayTraceResult.getEntity().push(motion.x() * 0.3, motion.y() * 0.3, motion.z() * 0.3);
					rayTraceResult.getEntity().hurtMarked = true;
				}
			}
		}
	}
}
