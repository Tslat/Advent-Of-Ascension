package net.tslat.aoa3.content.entity.projectile.cannon;

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
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.item.weapon.cannon.AoACannon;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;

public class CannonGrenade extends PhysicalWeaponProjectile {
	public CannonGrenade(EntityType<? extends CannonGrenade> entityType, Level level) {
		super(entityType, level);
	}

	public CannonGrenade(EntityType<? extends CannonGrenade> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public CannonGrenade(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.CANNON_GRENADE.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.075f;
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
		if (level() instanceof ServerLevel level)
			AoAExplosionBuilder.at(level, position, AoAExplosions.ancientBomber(target -> getShotContext().weaponStack().getItem() instanceof AoACannon cannon ? cannon.addCannonDamageBonus(1, target) : 1),
								   StandardExplosion::new).explodingEntity(this).explode();
	}
}
