package net.tslat.aoa3.content.entity.mob.unused;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import org.jetbrains.annotations.Nullable;


public class SkelekyteEntity extends AoAMeleeMob<SkelekyteEntity> {
	private int cloakCooldown = 80;

	public SkelekyteEntity(EntityType<? extends SkelekyteEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.71875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SKELETON_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SKELETON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.SKELETON_HURT;
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		cloakCooldown--;

		if (cloakCooldown == 0) {
			cloakCooldown = 80;

			setDeltaMovement(getDeltaMovement().multiply(0.5f, 1, 0.5f));
			EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 20));
		}
	}
}
