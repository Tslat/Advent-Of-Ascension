package net.tslat.aoa3.content.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltipItem extends Item {
	private final int numberOfDescriptions;

	public TooltipItem(int descAmount, Item.Properties properties) {
		super(properties);

		this.numberOfDescriptions = descAmount;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		for (int i = 1; i <= numberOfDescriptions; i++) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, i));
		}
	}
}
