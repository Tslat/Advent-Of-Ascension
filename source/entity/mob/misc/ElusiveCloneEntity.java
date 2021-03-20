package net.tslat.aoa3.entity.mob.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.ElusiveEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ElusiveCloneEntity extends AoAMeleeMob {
	private final ElusiveEntity elusive;

	public ElusiveCloneEntity(ElusiveEntity elusive) {
		super(AoAEntities.Mobs.ELUSIVE_CLONE.get(), elusive.level);

		moveTo(elusive.getX(), elusive.getY(), elusive.getZ(), elusive.yRot, elusive.xRot);
		this.elusive = elusive;
	}

	public ElusiveCloneEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		this.elusive = null;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.BLINDNESS, 80).level(3));
		EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 40));
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
