package net.tslat.aoa3.entity.mobs.candyland;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

public class EntityGingerbird extends AoAFlyingMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntityGingerbird(World world) {
		super(world, entityWidth, 0.5625f);
	}

	@Override
	public float getEyeHeight() {
		return 0.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.plantThump;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.plantThump;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.gingerbreadWing, 1);

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensCandyland, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerGingerbread), 1);

		if (rand.nextInt(70 - lootingMod) == 0)
			dropItem(WeaponRegister.swordSweet, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.gingerbread), 2 + rand.nextInt(3 + lootingMod));
		dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(3 + lootingMod));
	}
}
