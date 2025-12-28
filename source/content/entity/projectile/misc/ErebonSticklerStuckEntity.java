package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class ErebonSticklerStuckEntity extends AttachedSticklerEntity {
	private AreaEffectCloud effectCloud = null;

	public ErebonSticklerStuckEntity(EntityType<? extends AttachedSticklerEntity> entityType, Level world) {
		super(entityType, world);
	}

	public ErebonSticklerStuckEntity(Level level, Entity shooter, LivingEntity target, Vec3 stuckOffset, WeaponFiringContext context) {
		super(AoAProjectiles.EREBON_STICKLER_STUCK.get(), level, shooter, target, stuckOffset, context);
	}

	@Override
	protected void updatePosition() {
		super.updatePosition();

		if (this.effectCloud != null)
			this.effectCloud.setPos(position());
	}

	@Override
	protected void explode() {
		super.explode();

		if (!level().isClientSide && this.effectCloud != null)
			this.effectCloud.discard();
	}

	protected void checkEffectCloud() {
		if (this.effectCloud == null) {
			this.effectCloud = new AreaEffectCloud(level(), getX(), getY(), getZ());

			this.effectCloud.setDuration(this.tickCount);
			this.effectCloud.setRadius(2);
			this.effectCloud.setDurationOnUse(0);
			this.effectCloud.setRadiusOnUse(0);
			this.effectCloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0, false, true));
			level().addFreshEntity(this.effectCloud);
		}
	}

	@Override
	public void tick() {
		super.tick();
		checkEffectCloud();
	}
}
