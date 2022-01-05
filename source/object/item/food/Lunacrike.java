package net.tslat.aoa3.object.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Lunacrike extends Item {
	public Lunacrike() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(
				new Food.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new PotionUtil.EffectBuilder(Effects.JUMP, 40).level(2).build(), 1)
						.build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if (!world.isClientSide)
			entity.push(entity.getDeltaMovement().x(), 2, entity.getDeltaMovement().z());

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}