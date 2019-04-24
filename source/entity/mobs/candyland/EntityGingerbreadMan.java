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

public class EntityGingerbreadMan extends AoAMeleeMob {
	public static final float entityWidth = 0.59375f;
	public EntityGingerbreadMan(World world) {
		super(world, entityWidth, 2.125f);
	}

	@Override
	public float getEyeHeight() {
		return 1.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
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
	protected SoundEvent getDeathSound() {
		return SoundsRegister.plantThump;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.plantThump;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.gingerbreadCookie, 1);

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensCandyland, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(WeaponRegister.shotgunGingerBlaster, 1);

		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerGingerbread), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.gingerbread), 2 + rand.nextInt(3  + lootingMod));
		dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(3 + lootingMod));
	}
}
