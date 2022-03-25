package net.tslat.aoa3.content.item.tool.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

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
		return super.getHaulStrengthMod(player, stack, bobber) * 2f;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> tooltip, ITooltipFlag pFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
