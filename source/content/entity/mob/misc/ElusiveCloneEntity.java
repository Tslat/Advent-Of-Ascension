/*
package net.tslat.aoa3.content.entity.mob.misc;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.boss.ElusiveEntity;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;


public class ElusiveCloneEntity extends AoAMeleeMob<ElusiveCloneEntity> {
	private final ElusiveEntity elusive;

	public ElusiveCloneEntity(ElusiveEntity elusive) {
		super(AoAMobs.ELUSIVE_CLONE.get(), elusive.level);

		moveTo(elusive.getX(), elusive.getY(), elusive.getZ(), elusive.getYRot(), elusive.getXRot());
		this.elusive = elusive;
	}

	public ElusiveCloneEntity(EntityType<? extends ElusiveCloneEntity> entityType, Level world) {
		super(entityType, world);

		this.elusive = null;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.5f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ELUSIVE_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ELUSIVE_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ELUSIVE_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.BLINDNESS, 80).level(3));
		EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 40));
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide && elusive == null)
			remove();
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide && elusive != null)
			WorldUtil.createExplosion(elusive, level, getX(), getY(), getZ(), 2f);
	}
}
*/
