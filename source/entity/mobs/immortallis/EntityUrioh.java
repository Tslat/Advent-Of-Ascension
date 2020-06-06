package net.tslat.aoa3.entity.mobs.immortallis;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityUrioh extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntityUrioh(World world) {
		super(world, entityWidth, 0.9375f);
	}

	@Override
	public float getEyeHeight() {
		return height * 0.6f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_APPARITION_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_APPARITION_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_APPARITION_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		float scale = Math.max(0.1f, getHealth() / getMaxHealth());
		setSize(0.5f * scale, 0.9375f * scale);
	}
}
