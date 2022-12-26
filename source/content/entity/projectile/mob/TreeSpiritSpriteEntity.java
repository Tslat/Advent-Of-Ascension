package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.mob.overworld.TreeSpiritEntity;
import net.tslat.aoa3.util.EntityUtil;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TreeSpiritSpriteEntity extends BaseMobProjectile implements GeoEntity {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private final Entity target;

	public TreeSpiritSpriteEntity(EntityType<? extends TreeSpiritSpriteEntity> entityType, Level level) {
		super(entityType, level);

		this.target = null;
	}

	public TreeSpiritSpriteEntity(TreeSpiritEntity treeSpirit, Entity target) {
		super(AoAProjectiles.TREE_SPIRIT_SPRITE.get(), treeSpirit.level);

		this.projectileType = Type.MAGIC;
		this.shooter = treeSpirit;
		this.target = target;

		moveTo(treeSpirit.getX(), treeSpirit.getEyeY(), treeSpirit.getZ());
		setDeltaMovement(0, 0.5f, 0);
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.level.isClientSide()) {
			if (this.target == null || this.target.level != this.level || !this.target.isAlive()) {
				kill();

				return;
			}

			setDeltaMovement(
					EntityUtil.getEntityCenter(this.target)
							.subtract(EntityUtil.getEntityCenter(this))
							.normalize()
							.scale(0.3d)
							.vectorTo(getDeltaMovement())
							.scale(0.2d)
							.vectorTo(getDeltaMovement()));
		}
		else {
			if (this.tickCount % 4 == 0)
				level.addParticle(ParticleTypes.END_ROD, getX(), getY(), getZ(), 0, 0, 0);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!isInvulnerableTo(source)) {
			markHurt();
			level.playSound(null, getX(), getY(), getZ(), SoundEvents.SLIME_JUMP, SoundSource.HOSTILE, 1, 1);
			level.addParticle(ParticleTypes.ITEM_SLIME, getRandomX(0.5d), getY(), getRandomZ(0.5d), 0, 0, 0);
			kill();

			return true;
		}

		return false;
	}

	@Override
	public boolean isPickable() {
		return isAlive();
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Living", 0, state -> state.setAndContinue(DefaultAnimations.LIVING)));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
