package net.tslat.aoa3.content.entity.mob.mysterium;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class RunicGolemEntity extends AoAMeleeMob<RunicGolemEntity> {
	private static final EntityDataAccessor<Boolean> SHIELDED = SynchedEntityData.<Boolean>defineId(RunicGolemEntity.class, EntityDataSerializers.BOOLEAN);
	private int shieldCooldown = 120;
	private int runeStoneCooldown = 0;

	public RunicGolemEntity(EntityType<? extends RunicGolemEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.59375f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		getEntityData().define(SHIELDED, false);
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_RUNIC_GOLEM_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_RUNIC_GOLEM_HURT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return SoundEvents.IRON_GOLEM_STEP;
	}

	public void deactivateShield() {
		this.shieldCooldown = 120;
		setShielded(false);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!level().isClientSide) {
			if (isShielded() && EntityUtil.Predicates.SURVIVAL_PLAYER.test(source.getEntity())) {
				if (DamageUtil.isMeleeDamage(source) && runeStoneCooldown <= 0) {
					runeStoneCooldown = 120;
					spawnAtLocation(AoAItems.ACTIVE_RUNE_STONE.get(), 1);
				}

				return false;
			}
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean isInvulnerable() {
		return super.isInvulnerable() || isShielded();
	}

	public boolean isShielded() {
		return this.entityData.get(SHIELDED);
	}

	private void setShielded(boolean shielded) {
		this.entityData.set(SHIELDED, shielded);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (runeStoneCooldown > 0)
			runeStoneCooldown--;

		if (shieldCooldown > 0) {
			shieldCooldown--;
		}
		else if (shieldCooldown == 0) {
			shieldCooldown = -1;
			playSound(AoASounds.ENTITY_RUNIC_GOLEM_CHARGE.get(), 1.0f, 1.0f);
			setShielded(true);
		}
	}
}
