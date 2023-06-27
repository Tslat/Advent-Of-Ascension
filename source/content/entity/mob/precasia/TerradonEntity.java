package net.tslat.aoa3.content.entity.mob.precasia;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;

public class TerradonEntity extends AoAMeleeMob<TerradonEntity> {
	private static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.<Boolean>defineId(TerradonEntity.class, EntityDataSerializers.BOOLEAN);
	private int invulnCooldown = 0;

	public TerradonEntity(EntityType<? extends TerradonEntity> entityType, Level world) {
		super(entityType, world);

		setSpeed(1.8f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.3125f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(INVULNERABLE, false);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_TERRADON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_TERRADON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_TERRADON_HURT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
	}

	@Override
	public boolean isInvulnerable() {
		return super.isInvulnerable() || entityData.get(INVULNERABLE);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!level().isClientSide) {
			if (invulnCooldown > 0)
				invulnCooldown--;

			if (invulnCooldown <= 0)
				setInvulnerable(false);

			if (!isInvulnerable() && RandomUtil.oneInNChance(200))
				setInvulnerable(true);
		}
	}
}
