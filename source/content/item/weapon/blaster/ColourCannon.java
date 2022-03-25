package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.RainbowShotEntity;

import javax.annotation.Nullable;

public class ColourCannon extends BaseBlaster {
	public ColourCannon(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(new Item.Properties().tab(AoAItemGroups.BLASTERS).durability(durability).rarity(Rarity.EPIC), dmg, fireDelayTicks, energyCost);
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
