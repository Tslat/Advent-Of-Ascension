package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class GhostEntity extends AoAMeleeMob {
	public GhostEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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
	public boolean addEffect(MobEffectInstance effect, @Nullable Entity source) {
		if (effect.getEffect() != MobEffects.INVISIBILITY)
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

		if (!level.isClientSide && getTarget() instanceof Player) {
			if (EntityUtil.isPlayerLookingAtEntity(((Player)getTarget()), this) && hasLineOfSight(getTarget()))
				EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 200).isAmbient());
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

}
