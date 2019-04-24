package net.tslat.aoa3.entity.mobs.haven;

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

public class EntityRainicorn extends AoAMeleeMob {
	public static final float entityWidth = 1.4f;

	public EntityRainicorn(World world) {
		super(world, entityWidth, 1.3125f);
		setSlipperyMovement();
		setAIMoveSpeed(2.3f);
	}

	@Override
	public float getEyeHeight() {
		return 1.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.5;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
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
		return SoundsRegister.mobRainicornLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRainicornDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRainicornHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if(rand.nextBoolean())
			dropItem(ItemRegister.tokensHaven, 1);

		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitLight, 1);

		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterColourCannon, 1);

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerUtopian), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}
}
