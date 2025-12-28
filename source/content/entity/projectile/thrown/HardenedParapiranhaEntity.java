package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HardenedParapiranhaEntity extends PhysicalWeaponProjectile implements GeoEntity {
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public HardenedParapiranhaEntity(EntityType<? extends HardenedParapiranhaEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HardenedParapiranhaEntity(EntityType<? extends HardenedParapiranhaEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HardenedParapiranhaEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HARDENED_PARAPIRANHA.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		Vec3 velocity = getDeltaMovement();
		double lateralVelocity = velocity.horizontalDistance();
		this.yRotO = getYRot();
		this.xRotO = getXRot();

		setYRot((float)(Mth.atan2(velocity.x(), velocity.z()) * Mth.RAD_TO_DEG));
		setXRot((float)(Mth.atan2(velocity.y(), lateralVelocity) * Mth.RAD_TO_DEG));
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		if (DamageUtil.doProjectileAttack(getShooter(), this, hitEntity, getShotContext().damage()))
			EntityUtil.applyPotions(hitEntity, getOwner(), new EffectBuilder(MobEffects.WITHER, 60).level(2));
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericFlyController(this));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
