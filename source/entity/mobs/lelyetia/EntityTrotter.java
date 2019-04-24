package net.tslat.aoa3.entity.mobs.lelyetia;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityTrotter extends AoAMeleeMob {
	public static final float entityWidth = 0.75f;

	public EntityTrotter(World world) {
		super(world, entityWidth, 1.1875f);
		setSlipperyMovement();
		setAIMoveSpeed(1.16f);
	}

	@Override
	public float getEyeHeight() {
		return 0.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.5;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 45;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobTrotterLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTrotterDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTrotterHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(55 - lootingMod) == 0)
			dropItem(WeaponRegister.gunCoreRifle, 1);

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensLelyetia, 1 + rand.nextInt(3 + lootingMod));

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitLelyetian, 1);

		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerLelyetian), 1);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (ticksExisted % 140 == 0)
			motionY *= 1.2;
	}
}
