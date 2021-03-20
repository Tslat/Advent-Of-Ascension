package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class FieryChops extends Item {
	public FieryChops() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(new Food.Builder().nutrition(5).saturationMod(0.5f).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entityLiving) {
		if (entityLiving instanceof ServerPlayerEntity)
			PlayerUtil.addResourceToPlayer((ServerPlayerEntity)entityLiving, Resources.RAGE, 20);

		return super.finishUsingItem(stack, world, entityLiving);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
