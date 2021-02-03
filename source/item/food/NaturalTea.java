package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class NaturalTea extends Item {
	public NaturalTea() {
		super(new Item.Properties().group(AoAItemGroups.FOOD)
				.containerItem(AoAItems.CUP.get())
				.maxStackSize(1)
				.food(new Food.Builder()
						.hunger(0)
						.saturation(0)
						.setAlwaysEdible()
						.effect(new PotionUtil.EffectBuilder(Effects.REGENERATION, 50).build(), 1)
						.effect(new PotionUtil.EffectBuilder(Effects.RESISTANCE, 150).level(2).build(), 1)
						.build()));
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity user) {
		ItemStack consumedStack = super.onItemUseFinish(stack, world, user);

		return user instanceof PlayerEntity && ((PlayerEntity)user).abilities.isCreativeMode ? consumedStack : getContainerItem(stack);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
