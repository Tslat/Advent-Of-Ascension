package net.tslat.aoa3.entity.mobs.runandor;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityRunicorn extends AoAMeleeMob {
	public static final float entityWidth = 0.6875f;

	public EntityRunicorn(EntityRunicornRider rider, float health) {
		this(rider.world);

		setLocationAndAngles(rider.posX, rider.posY, rider.posZ, rider.rotationYaw, rider.rotationPitch);
		setHealth(health);
	}

	public EntityRunicorn(World world) {
		super(world, entityWidth, 2f);

		setSlipperyMovement();
		setAIMoveSpeed(1.6f);
	}

	@Override
	public float getEyeHeight() {
		return 1.6875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.15;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 132;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 14d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_RAINICORN_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_RAINICORN_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_RAINICORN_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityRunicorn;
	}
}
