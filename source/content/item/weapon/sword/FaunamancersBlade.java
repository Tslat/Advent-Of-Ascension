package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.ItemAbilities;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class FaunamancersBlade extends AoASword {
	public FaunamancersBlade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility ItemAbility) {
		if (ItemAbility == ItemAbilities.SWORD_SWEEP)
			return false;

		return super.canPerformAction(stack, ItemAbility);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
