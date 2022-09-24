package net.tslat.aoa3.content.entity.mob.nowhere;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class UriohEntity extends AoAMeleeMob {
	double lastHealth;

	public UriohEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		lastHealth = getHealth();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.6f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_APPARITION_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_APPARITION_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_APPARITION_HURT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (lastHealth != getHealth()) {
			refreshDimensions();
			lastHealth = getHealth();
		}
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		float scale = Math.max(0.1f, getHealth() / getMaxHealth());

		return super.getDimensions(pose).scale(0.5f * scale, 0.9375f * scale);
	}
}
