package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.sound.SoundBuilder;

public class BubbleShotEntity extends NonPhysicalWeaponProjectile {
	public BubbleShotEntity(EntityType<? extends BubbleShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public BubbleShotEntity(EntityType<? extends BubbleShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public BubbleShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.BUBBLE_SHOT.get(), level, context);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.3d, 0.3d, 0.3d));

		if (!level().isClientSide) {
			if (this.tickCount >= getDefaultShotContext().projectileLifespan()) {
				SoundBuilder.at(AoASounds.BUBBLE_SHOT_POP, this).category(getOwner() instanceof LivingEntity owner ? owner.getSoundSource() : getSoundSource()).play();

				discard();
			}
			else if (getDeltaMovement().lengthSqr() < 0.01d) {
				EntityRetrievalUtil.getNearestEntity(level(), getBoundingBox(), position().add(0, getBbHeight() * 0.5f, 0), LivingEntity.class, entity -> EntityUtil.areProbablyEnemies(entity, getOwner()))
						.ifPresent(target -> onHit(new EntityHitResult(target, position())));
			}
		}
	}
}
