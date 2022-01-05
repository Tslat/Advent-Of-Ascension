package net.tslat.aoa3.object.entity.mob.mysterium;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;

import javax.annotation.Nullable;

public class RunicGolemEntity extends AoAMeleeMob {
	private static final DataParameter<Boolean> SHIELDED = EntityDataManager.<Boolean>defineId(RunicGolemEntity.class, DataSerializers.BOOLEAN);
	private int shieldCooldown = 120;
	private int runeStoneCooldown = 0;

	public RunicGolemEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.59375f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHIELDED, false);
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
		if (!level.isClientSide) {
			if (isShielded()) {
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
