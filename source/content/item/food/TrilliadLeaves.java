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

public class TrilliadLeaves extends Item {
	public TrilliadLeaves() {
		super(new Item.Properties().tab(AoACreativeModeTabs.FOOD).food(
				new FoodProperties.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(MobEffects.BLINDNESS, 130).build(), 1)
						.effect(new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 100).level(11).build(), 1)
						.effect(new EffectBuilder(MobEffects.REGENERATION, 100).level(3).build(), 1)
						.effect(new EffectBuilder(MobEffects.POISON, 100).level(8).build(), 1)
						.effect(new EffectBuilder(MobEffects.JUMP, 100).level(129).build(), 1)
						.build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide)
			entity.removeEffect(MobEffects.CONFUSION);

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
