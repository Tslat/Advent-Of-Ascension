package net.tslat.aoa3.entity.mobs.runandor;

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

public class EntityBouncer extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;
	private int jumpCooldown = 70;

	public EntityBouncer(World world) {
		super(world, entityWidth, 1.4375f);
	}

	@Override
	public float getEyeHeight() {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
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
		return SoundsRegister.mobBouncerLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBouncerDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBouncerHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(45 - lootingMod) == 0)
			dropItem(WeaponRegister.gunPurityRifle, 1);

		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneBarathos, 1);

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerRunic), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		if (jumpCooldown > 0) {
			jumpCooldown--;
		}
		else {
			motionY = 1.2;
			jumpCooldown = 70;
		}
	}
}
