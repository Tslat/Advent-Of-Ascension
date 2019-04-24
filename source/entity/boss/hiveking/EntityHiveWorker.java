package net.tslat.aoa3.entity.boss.hiveking;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityHiveWorker extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityHiveWorker(EntityHiveKing hiveKing) {
		this(hiveKing.world);

		setLocationAndAngles(hiveKing.posX, hiveKing.posY, hiveKing.posZ, rand.nextFloat() * 360, 1);
	}

	public EntityHiveWorker(World world) {
		super(world, entityWidth, 1.8f);
	}

	@Override
	public float getEyeHeight() {
		return 1.1625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobParasectLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobParasectDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobParasectHit;
	}
}
