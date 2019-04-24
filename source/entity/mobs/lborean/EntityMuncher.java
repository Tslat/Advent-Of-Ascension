package net.tslat.aoa3.entity.mobs.lborean;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
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
		return 80;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMuncherLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMuncherDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMuncherHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(50 - lootingMod) == 0)
			dropItem(ItemRegister.waterloggedReefer, 1);

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitSeaside, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0)
			heal(0.6f);
	}
}
