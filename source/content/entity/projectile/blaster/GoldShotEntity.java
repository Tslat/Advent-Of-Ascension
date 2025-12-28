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

public class GoldShotEntity extends NonPhysicalWeaponProjectile {
	public GoldShotEntity(EntityType<? extends GoldShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public GoldShotEntity(EntityType<? extends GoldShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public GoldShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.GOLD_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.03f;
	}

	@Override
	protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {
		explode(rayTrace.getLocation());
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		explode(rayTrace.getLocation());
	}

	protected void explode(Vec3 position) {
		if (level() instanceof ServerLevel level)
			AoAExplosionBuilder.at(level, position, AoAExplosions.GOLD_BRINGER, StandardExplosion::new).explodingEntity(this).explode();
	}
}
