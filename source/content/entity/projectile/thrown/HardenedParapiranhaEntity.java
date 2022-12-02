package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class HardenedParapiranhaEntity extends BaseBullet implements HardProjectile, IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public HardenedParapiranhaEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HardenedParapiranhaEntity(Level world) {
		super(AoAProjectiles.HARDENED_PARAPIRANHA.get(), world);
	}

	public HardenedParapiranhaEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAProjectiles.HARDENED_PARAPIRANHA.get(), shooter, gun, 1.0f, 0, 1.5f);
	}

	public HardenedParapiranhaEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.HARDENED_PARAPIRANHA.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HardenedParapiranhaEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.HARDENED_PARAPIRANHA.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		Vec3 motion = getDeltaMovement();
		double motionVector = Math.sqrt(motion.x() * motion.x() + motion.z() * motion.z());
		setYRot((float)(Mth.atan2(motion.x(), motion.z()) * (180D / Math.PI)));
		setXRot((float)(Mth.atan2(motion.y(), motionVector) * (180D / Math.PI)));
		yRotO = getYRot();
		xRotO = getXRot();
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public void doEntityImpact(Entity target, Vec3 impactLocation) {
		if (target.hurt(DamageSource.thrown(this, null), (float)AoAWeapons.HARDENED_PARAPIRANHA.get().getDamage()))
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 60).level(2));
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(AoAAnimations.genericFlyController(this));
	}

	@Override
	public AnimationFactory getFactory() {
		return animationFactory;
	}
}
