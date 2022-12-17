package net.tslat.aoa3.content.item.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class CharredChar extends Item {
	public CharredChar() {
		super(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build()));
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return 1200;
	}
}
