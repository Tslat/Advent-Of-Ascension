package net.tslat.aoa3.object.entity.mob.overworld;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class GhostEntity extends AoAMeleeMob {
	public GhostEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.8125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GHOST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GHOST_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GHOST_HURT.get();
	}

	@Override
	public boolean addEffect(EffectInstance effect) {
		if (effect.getEffect() != Effects.INVISIBILITY)
			return false;

		return super.addEffect(effect);
	}

	@Override
	public boolean isAffectedByPotions() {
		return false;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!level.isClientSide && getTarget() instanceof PlayerEntity) {
			if (EntityUtil.isPlayerLookingAtEntity(((PlayerEntity)getTarget()), this) && canSee(getTarget()))
				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 200).isAmbient());
		}
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.UNDEAD;
	}

}
