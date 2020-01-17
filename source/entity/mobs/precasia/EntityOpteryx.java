package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityOpteryx extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;
	private int jumpCooldown = 70;

	public EntityOpteryx(World world) {
		super(world, entityWidth, 1.375f);

		setSlipperyMovement();
		setAIMoveSpeed(1.8f);
	}

	@Override
	public float getEyeHeight() {
		return 1.34375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 75d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobChargerLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobChargerHit;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobChargerHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityOpteryx;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (jumpCooldown <= 0) {
			jumpCooldown = rand.nextInt(100) + 30;
			motionY = 0.8;
			motionX *= 1.2;
			motionZ *= 1.2;
		}
	}
}
