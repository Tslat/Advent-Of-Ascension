package net.tslat.aoa3.content.item.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.tslat.aoa3.common.registration.AoAItemGroups;

import javax.annotation.Nullable;

public class CharredChar extends Item {
	public CharredChar() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(new Food.Builder().nutrition(2).saturationMod(0.2f).build()));
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
		return 1200;
	}
}
