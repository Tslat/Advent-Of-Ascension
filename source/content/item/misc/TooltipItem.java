package net.tslat.aoa3.content.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipItem extends Item {
	private final int numberOfDescriptions;

	public TooltipItem(int descAmount, Item.Properties properties) {
		super(properties);

		this.numberOfDescriptions = descAmount;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		for (int i = 1; i <= numberOfDescriptions; i++) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, i));
		}
	}
}
