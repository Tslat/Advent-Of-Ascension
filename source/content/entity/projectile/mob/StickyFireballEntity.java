package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;

public class StickyFireballEntity extends FireballEntity {
	public StickyFireballEntity(EntityType<? extends StickyFireballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public StickyFireballEntity(Level world, AoARangedAttacker shooter, Type projectileType) {
		super(AoAProjectiles.STICKY_FIREBALL.get(), world, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		if (level instanceof ServerLevel serverLevel && this.tickCount == 99) {
			new StandardExplosion(AoAExplosions.STICKY_FIREBALL, serverLevel, this, getOwner(), position()).explode();

			discard();

			return;
		}

		if (this.onGround)
			setDeltaMovement(0, 0, 0);

		if (this.level.isClientSide() && (getDeltaMovement().lengthSqr() != 0 || this.tickCount % 4 == 0))
			ParticleBuilder.forRandomPosInEntity(ParticleTypes.CAMPFIRE_COSY_SMOKE, this).lifespan(40).scaleMod(0.5f).spawnParticles();
	}

	@Override
	protected void onHit(HitResult result) {
		if (result.getType() != HitResult.Type.BLOCK || level.getBlockState(BlockPos.containing(result.getLocation())).getMaterial().blocksMotion()) {
			if (result instanceof EntityHitResult entityHitResult) {
				if (this.level.isClientSide || entityHitResult.getEntity() == shooter || shooter == null)
					return;

				onHitEntity(entityHitResult);
				shooter.doRangedAttackEntity(this, entityHitResult.getEntity());

				discard();
			}
			else if (result instanceof BlockHitResult blockHitResult) {
				if (!this.level.isClientSide && shooter != null & getDeltaMovement().lengthSqr() != 0) {
					onHitBlock(blockHitResult);
					shooter.doRangedAttackBlock(this, level.getBlockState(BlockPos.containing(result.getLocation())), BlockPos.containing(result.getLocation()), blockHitResult.getDirection());
				}

				if (!this.onGround) {
					setPos(blockHitResult.getLocation().subtract(getDeltaMovement().multiply(0.25f, 0.25f, 0.25f)));
					setDeltaMovement(0, 0, 0);

					this.onGround = true;
				}
			}
		}
	}
}
