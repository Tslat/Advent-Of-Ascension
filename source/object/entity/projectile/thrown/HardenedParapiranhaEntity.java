package net.tslat.aoa3.object.entity.projectile.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class HardenedParapiranhaEntity extends BaseBullet implements HardProjectile, IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public HardenedParapiranhaEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public HardenedParapiranhaEntity(World world) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), world);
	}

	public HardenedParapiranhaEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), shooter, gun, 1.0f, 0, 1.5f);
	}

	public HardenedParapiranhaEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HardenedParapiranhaEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		Vector3d motion = getDeltaMovement();
		float motionVector = MathHelper.sqrt(motion.x() * motion.x() + motion.z() * motion.z());
		yRot = (float)(MathHelper.atan2(motion.x(), motion.z()) * (180D / Math.PI));
		xRot = (float)(MathHelper.atan2(motion.y(), motionVector) * (180D / Math.PI));
		yRotO = yRot;
		xRotO = xRot;
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (result instanceof BlockRayTraceResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public void doEntityImpact(Entity target) {
		if (target.hurt(DamageSource.thrown(this, null), (float)AoAWeapons.HARDENED_PARAPIRANHA.get().getDamage()))
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WITHER, 60).level(2));
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
