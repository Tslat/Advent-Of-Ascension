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

public class MoonDestroyerShotEntity extends NonPhysicalWeaponProjectile {
	public MoonDestroyerShotEntity(EntityType<? extends MoonDestroyerShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public MoonDestroyerShotEntity(EntityType<? extends MoonDestroyerShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public MoonDestroyerShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.MOON_DESTROYER_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.15f;
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		explode(rayTrace.getLocation());
	}

	@Override
	protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {
		explode(rayTrace.getLocation());
	}

	protected void explode(Vec3 position) {
		if (level() instanceof ServerLevel level) {
			for (double x = -5; x <= 5; x += 2.5) {
				for (double y = -5; y <= 5; y += 2.5f) {
					for (double z = -5; z <= 5; z += 2.5f) {
						AoAExplosionBuilder.at(level, position.add(x, y, z), AoAExplosions.MOON_DESTROYER, StandardExplosion::new).explodingEntity(this).explode();
					}
				}
			}
		}
	}
}
