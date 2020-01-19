package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityNospike extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityNospike(World world) {
		super(world, entityWidth, 1.25f);
		setSlipperyMovement();
		setAIMoveSpeed(3.7f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.5;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 65;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobNospikeLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobNospikeDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobNospikeHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityNospike;
	}
}
