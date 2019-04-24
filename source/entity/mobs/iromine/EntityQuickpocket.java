package net.tslat.aoa3.entity.mobs.iromine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityQuickpocket extends AoAMeleeMob {
	public static final float entityWidth = 0.375f;

	public EntityQuickpocket(World world) {
		super(world, entityWidth, 1.25f);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
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
		return SoundsRegister.mobQuickpocketLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobQuickpocketDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobQuickpocketHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(WeaponRegister.sniperBoltRifle, 1);

		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitGolden, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayer) {
			EntityPlayer pl = (EntityPlayer)target;

			if (pl.getHeldItem(EnumHand.MAIN_HAND) != ItemStack.EMPTY) {
				ItemStack heldStack = pl.getHeldItem(EnumHand.MAIN_HAND).copy();

				pl.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
				entityDropItem(heldStack, 0.5f);
				pl.inventoryContainer.detectAndSendChanges();
			}
			else if (pl.getHeldItem(EnumHand.OFF_HAND) != ItemStack.EMPTY) {
				ItemStack heldStack = pl.getHeldItem(EnumHand.OFF_HAND).copy();

				pl.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
				entityDropItem(heldStack, 0.5f);
				pl.inventoryContainer.detectAndSendChanges();
			}
		}
	}
}
