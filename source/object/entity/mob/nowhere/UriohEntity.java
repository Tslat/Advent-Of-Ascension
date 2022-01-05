package net.tslat.aoa3.object.entity.mob.nowhere;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class UriohEntity extends AoAMeleeMob {
	double lastHealth;

	public UriohEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		lastHealth = getHealth();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
		return size.height * 0.6f;
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
	public EntitySize getDimensions(Pose pose) {
		float scale = Math.max(0.1f, getHealth() / getMaxHealth());

		return super.getDimensions(pose).scale(0.5f * scale, 0.9375f * scale);
	}
}
