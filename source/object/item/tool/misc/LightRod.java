package net.tslat.aoa3.object.item.tool.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.object.entity.misc.HaulingFishingBobberEntity;

public class LightRod extends HaulingRod {
	public LightRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	protected HaulingFishingBobberEntity getNewBobber(PlayerEntity player, ItemStack stack, int lureMod, int luckMod) {
		return new HaulingFishingBobberEntity(player, player.level, stack, luckMod, lureMod);
	}

	@Override
	public float getHaulStrengthMod(PlayerEntity player, ItemStack stack, HaulingFishingBobberEntity bobber) {
		return super.getHaulStrengthMod(player, stack, bobber) * 1.5f;
	}
}
