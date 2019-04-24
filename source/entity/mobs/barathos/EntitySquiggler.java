package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySquiggler extends AoAMeleeMob {
	public static final float entityWidth = 0.5625f;

	public EntitySquiggler(World world) {
		super(world, entityWidth, 1.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSquigglerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSquigglerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSquigglerDeath;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 20 && super.getCanSpawnHere();
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(WeaponRegister.greatbladeUnderworld, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinSilver, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getAttackTarget() != null && getAttackTarget().getDistance(this) < 20)
			getAttackTarget().setFire(8);
	}
}
