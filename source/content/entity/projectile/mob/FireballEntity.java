package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class FireballEntity extends BaseMobProjectile implements GeoEntity {
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public FireballEntity(EntityType<? extends FireballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public FireballEntity(Level world, AoARangedAttacker shooter, Type projectileType) {
		super(AoAProjectiles.FIREBALL.get(), world, shooter, projectileType);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericLivingController(this).setAnimationSpeed(1.5f));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.level.isClientSide()) {
			level.addParticle(ParticleTypes.LARGE_SMOKE, getX(0.5f), getY(0.5f), getZ(0.5f), 0, 0, 0);
			level.addParticle(ParticleTypes.FLAME, getRandomX(0.25f), getRandomY(), getRandomZ(0.25f), 0, 0, 0);
			level.addParticle(ParticleTypes.FLAME, getRandomX(0.25f), getRandomY(), getRandomZ(0.25f), 0, 0, 0);
		}
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
