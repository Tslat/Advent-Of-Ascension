package net.tslat.aoa3.content.entity.mob.unused;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import org.jetbrains.annotations.Nullable;


public class ShavoEntity extends AoAMeleeMob<ShavoEntity> {
	public ShavoEntity(EntityType<? extends ShavoEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.3125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_FIEND_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_FIEND_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_FIEND_HURT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}
}
