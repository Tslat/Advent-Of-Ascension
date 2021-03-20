package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
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

public class GoldicapPetals extends Item {
	public GoldicapPetals() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(
				new Food.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new PotionUtil.EffectBuilder(Effects.MOVEMENT_SPEED, 40).level(2).build(), 1)
						.effect(new PotionUtil.EffectBuilder(Effects.JUMP, 40).level(2).build(), 1)
						.build()));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 24;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}