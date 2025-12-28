package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.util.RandomUtil;

public class AtomizerShotEntity extends NonPhysicalWeaponProjectile {
	private boolean bounced = false;

	public AtomizerShotEntity(EntityType<? extends AtomizerShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public AtomizerShotEntity(EntityType<? extends AtomizerShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public AtomizerShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.ATOMIZER_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}

	@Override
	protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {
		if (this.bounced) {
			explode(rayTrace.getLocation());
		}
		else {
			bounce();
		}
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		explode(rayTrace.getLocation());

		if (!this.bounced)
			bounce();
	}

	private void explode(Vec3 position) {
		if (level() instanceof ServerLevel level)
			AoAExplosionBuilder.at(level, position, AoAExplosions.ATOMIZER, StandardExplosion::new).explodingEntity(this).explode();
	}

	private void bounce() {
		setDeltaMovement(RandomUtil.scaledGaussianValue(0.5f), 1.3f, RandomUtil.scaledGaussianValue(0.5f));
		this.hasImpulse = true;
		this.bounced = true;
	}
}
