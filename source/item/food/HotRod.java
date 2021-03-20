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

public class HotRod extends Item {
	public HotRod() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(new Food.Builder().nutrition(9).saturationMod(0.9f).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entity) {
		if (entity instanceof ServerPlayerEntity)
			PlayerUtil.addResourceToPlayer((ServerPlayerEntity)entity, Resources.RAGE, 20);

		return super.finishUsingItem(stack, worldIn, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
