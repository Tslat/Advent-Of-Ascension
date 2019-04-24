package net.tslat.aoa3.entity.mobs.candyland;

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

public class EntityCandyCorny extends AoAMeleeMob {
	public static final float entityWidth = 0.625f;

	public EntityCandyCorny(World world) {
		super(world, entityWidth, 2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.53125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 13;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.candyThump;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.candyThump;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.candyCorn, 1);

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensCandyland, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(75 - lootingMod) == 0)
			dropItem(WeaponRegister.sniperSweetTooth, 1);

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerCandy), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(3 + lootingMod));

		if (rand.nextInt(4) == 0) {
			dropItem(Item.getItemFromBlock(BlockRegister.chocolateBlock), 2 + rand.nextInt(3 + lootingMod));
		}
		else if (rand.nextInt(4) == 0) {
			dropItem(Item.getItemFromBlock(BlockRegister.chocolateBlockDark), 2 + rand.nextInt(3 + lootingMod));
		}
		else if (rand.nextInt(4) == 0) {
			dropItem(Item.getItemFromBlock(BlockRegister.chocolateBlockWhite), 2 + rand.nextInt(3 + lootingMod));
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}
}
