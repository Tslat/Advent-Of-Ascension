package net.tslat.aoa3.entity.mob.mysterium;

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
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;

import javax.annotation.Nullable;

public class RunicGolemEntity extends AoAMeleeMob {
	private static final DataParameter<Boolean> SHIELDED = EntityDataManager.<Boolean>createKey(RunicGolemEntity.class, DataSerializers.BOOLEAN);
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
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SHIELDED, false);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 95;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.265;
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
		return SoundEvents.ENTITY_IRON_GOLEM_STEP;
	}

	public void deactivateShield() {
		this.shieldCooldown = 120;
		setShielded(false);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote) {
			if (isShielded()) {
				if (DamageUtil.isMeleeDamage(source) && runeStoneCooldown <= 0) {
					runeStoneCooldown = 120;
					entityDropItem(AoAItems.ACTIVE_RUNE_STONE.get(), 1);
				}

				return false;
			}
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean isInvulnerable() {
		return super.isInvulnerable() || isShielded();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return isInvulnerable() || super.isInvulnerableTo(source);
	}

	public boolean isShielded() {
		return this.dataManager.get(SHIELDED);
	}

	private void setShielded(boolean shielded) {
		this.dataManager.set(SHIELDED, shielded);
	}

	@Override
	public void livingTick() {
		super.livingTick();

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
