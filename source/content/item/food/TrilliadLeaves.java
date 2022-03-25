package net.tslat.aoa3.content.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TrilliadLeaves extends Item {
	public TrilliadLeaves() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(
				new Food.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(Effects.BLINDNESS, 130).build(), 1)
						.effect(new EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 100).level(11).build(), 1)
						.effect(new EffectBuilder(Effects.REGENERATION, 100).level(3).build(), 1)
						.effect(new EffectBuilder(Effects.POISON, 100).level(8).build(), 1)
						.effect(new EffectBuilder(Effects.JUMP, 100).level(129).build(), 1)
						.build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if (!world.isClientSide)
			entity.removeEffect(Effects.CONFUSION);

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
