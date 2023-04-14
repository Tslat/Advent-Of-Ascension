package net.tslat.aoa3.integration.jei.ingredient.subtype;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class TrophySubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
	public static final TrophySubtypeInterpreter INSTANCE = new TrophySubtypeInterpreter();

	@Override
	public String apply(ItemStack ingredient, UidContext context) {
		if (!ingredient.hasTag())
			return "";

		CompoundTag tag = ingredient.getTag();

		if (!tag.contains("BlockEntityTag"))
			return "";

		CompoundTag blockEntityTag = tag.getCompound("BlockEntityTag");

		if (!blockEntityTag.contains("EntityID"))
			return "";

		return blockEntityTag.getString("EntityID");
	}
}