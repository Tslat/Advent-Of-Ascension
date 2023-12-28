package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.RainbowShotEntity;
import org.jetbrains.annotations.Nullable;


public class ColourCannon extends BaseBlaster {
	public ColourCannon(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(new Item.Properties().durability(durability).rarity(Rarity.EPIC), dmg, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_COLOUR_CANNON_FIRE.get();
	}

	@Override
	public void fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster) {
		shooter.level().addFreshEntity(new RainbowShotEntity(shooter, this, 120));
	}
}
