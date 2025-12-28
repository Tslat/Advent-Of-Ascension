package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.ThrownItemProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

public class SliceStarEntity extends ThrownItemProjectile {
	public SliceStarEntity(EntityType<? extends SliceStarEntity> entityType, Level level) {
		super(entityType, level);
	}

	public SliceStarEntity(EntityType<? extends SliceStarEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public SliceStarEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.SLICE_STAR.get(), level, context);
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		if (DamageUtil.doProjectileAttack(getOwner(), this, hitEntity, getShotContext().damage()) && RandomUtil.oneInNChance(10))
			EntityUtil.applyPotions(hitEntity, getOwner(), new EffectBuilder(AoAMobEffects.BLEEDING, 80).hideParticles());
	}
}
