package net.tslat.aoa3.entity.mob.precasia;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class TerradonEntity extends AoAMeleeMob {
	private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>createKey(TerradonEntity.class, DataSerializers.BOOLEAN);
	private int invulnCooldown = 0;

	public TerradonEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		isSlipperyMovement = true;

		setAIMoveSpeed(1.8f);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.3125f;
	}

	@Override
	protected void registerData() {
		super.registerData();

		this.dataManager.register(INVULNERABLE, false);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 105d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
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
		return super.isInvulnerable() || dataManager.get(INVULNERABLE);
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!world.isRemote) {
			if (invulnCooldown > 0)
				invulnCooldown--;

			if (invulnCooldown <= 0)
				setInvulnerable(false);

			if (!isInvulnerable() && RandomUtil.oneInNChance(200))
				setInvulnerable(true);
		}
	}
}
