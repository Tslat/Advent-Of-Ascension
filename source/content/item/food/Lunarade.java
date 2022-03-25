package net.tslat.aoa3.content.item.food;

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
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Lunarade extends Item {
	public Lunarade() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD)
				.craftRemainder(AoAItems.LUNARADE_MUG.get())
				.food(new Food.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(Effects.NIGHT_VISION, 40).level(2).build(), 1)
						.effect(new EffectBuilder(Effects.JUMP, 40).build(), 1)
						.build()));
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity user) {
		if (!world.isClientSide)
			user.removeEffect(Effects.BLINDNESS);

		ItemStack consumedStack = super.finishUsingItem(stack, world, user);

		return user instanceof PlayerEntity && ((PlayerEntity)user).abilities.instabuild ? consumedStack : getContainerItem(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
