package net.tslat.aoa3.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RawHalyconBeef extends BasicFood {
	public RawHalyconBeef() {
		super("RawHalyconBeef", "raw_halycon_beef", 2, 0.3f);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		if (!world.isRemote) {
			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 150, 0, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 40, 0, true, false));
		}
	}
}
