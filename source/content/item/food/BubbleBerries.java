package net.tslat.aoa3.content.item.food;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAFood;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BubbleBerries extends Item {
	public BubbleBerries() {
		super(new Item.Properties().food(AoAFood.BUBBLE_BERRIES));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		if (!worldIn.isClientSide)
			entityLiving.setAirSupply(Math.min(300, entityLiving.getAirSupply() + 50));

		return super.finishUsingItem(stack, worldIn, entityLiving);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 18;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}