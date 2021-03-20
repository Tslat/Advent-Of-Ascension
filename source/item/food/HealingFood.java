package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HealingFood extends Item {
	private final float healingAmount;

	public HealingFood(float healAmount, Item.Properties itemProperties) {
		super(itemProperties);

		this.healingAmount = healAmount;
	}

	public float getHealAmount() {
		return this.healingAmount;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		float percentHunger = 100;

		if (entity instanceof PlayerEntity)
			percentHunger = Math.min((20 - ((PlayerEntity)entity).getFoodData().getFoodLevel()) / (float)this.getFoodProperties().getNutrition(), 1);

		ItemStack returnStack = super.finishUsingItem(stack, world, entity);

		if (entity instanceof PlayerEntity)
			EntityUtil.healEntity(entity, healingAmount * percentHunger);

		return returnStack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.healingFood.desc.1", LocaleUtil.ItemDescriptionType.NEUTRAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.healingFood.desc.2", LocaleUtil.ItemDescriptionType.NEUTRAL, new StringTextComponent(Float.toString(healingAmount))));
	}
}
