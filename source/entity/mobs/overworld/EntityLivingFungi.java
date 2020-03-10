package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityLivingFungi extends AoAMeleeMob {
	public static final float entityWidth = 1.1f;

	public EntityLivingFungi(World world) {
		super(world, entityWidth, 2.4375f);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(1.8f);
	}

	@Override
	public float getEyeHeight() {
		return 1.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobFungiLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFungiDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFungiHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityLivingFungi;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
