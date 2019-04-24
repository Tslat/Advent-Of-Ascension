package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityKeeler extends AoAMeleeMob {
	public static final float entityWidth = 0.6875f;

	public EntityKeeler(World world) {
		super(world, entityWidth, 1.4f);
	}

	@Override
	public float getEyeHeight() {
		return 0.6875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobKeelerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobKeelerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobKeelerHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 50 && super.getCanSpawnHere();
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(60 - lootingMod) == 0)
			dropItem(WeaponRegister.bowBaron, 1);

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitRocky, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getHealth() < 8 && getHealth() > 0) {
			setHealth(getMaxHealth());
			world.playSound(null, posX, posY, posZ, SoundsRegister.mobKeelerRevive, SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
	}
}
