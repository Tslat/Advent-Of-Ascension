package net.tslat.aoa3.content.item.food;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Lunacrike extends Item {
	public Lunacrike() {
		super(new Item.Properties().tab(AoACreativeModeTabs.FOOD).food(
				new FoodProperties.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(MobEffects.JUMP, 40).level(2).build(), 1)
						.build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide)
			entity.push(entity.getDeltaMovement().x(), 2, entity.getDeltaMovement().z());

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}