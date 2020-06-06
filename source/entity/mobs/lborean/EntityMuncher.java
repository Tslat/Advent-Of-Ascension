package net.tslat.aoa3.entity.mobs.lborean;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityMuncher extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityMuncher(World world) {
		super(world, entityWidth, 2.0625f);
		setSlipperyMovement();
		setAIMoveSpeed(1.6f);
	}

	@Override
	public float getEyeHeight() {
		return 1.96875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.5;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 135;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 13.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_MUNCHER_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_MUNCHER_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_MUNCHER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMuncher;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0 && getHealth() < getMaxHealth())
			heal(0.2f);
	}
}
