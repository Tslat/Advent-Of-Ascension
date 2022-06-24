package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.RainbowShotEntity;

import javax.annotation.Nullable;

public class ColourCannon extends BaseBlaster {
	public ColourCannon(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(new Item.Properties().tab(AoACreativeModeTabs.BLASTERS).durability(durability).rarity(Rarity.EPIC), dmg, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_COLOUR_CANNON_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new RainbowShotEntity(shooter, this, 120));
	}
}
