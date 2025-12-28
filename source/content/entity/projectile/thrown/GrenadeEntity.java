package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.ThrownItemProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.tme.api.explosion.ShrapnelExplosion;

public class GrenadeEntity extends ThrownItemProjectile {
	public GrenadeEntity(EntityType<? extends GrenadeEntity> entityType, Level level) {
		super(entityType, level);
	}

	public GrenadeEntity(EntityType<? extends GrenadeEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public GrenadeEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.GRENADE.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.075f;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			if (source.is(DamageTypeTags.IS_FIRE)) {
				explode(position());

				if (getOwner() instanceof ServerPlayer pl)
					AdvancementUtil.grantCriterion(pl, AdventOfAscension.id("completionist/darwin_award"), "fire_grenade");

				discard();
			}

			return true;
		}

		return super.hurt(source, amount);
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
			AoAExplosionBuilder.at(level, position, AoAExplosions.GRENADE, ShrapnelExplosion::new).explodingEntity(this).indirectSource(getOwner()).explode();
	}
}
