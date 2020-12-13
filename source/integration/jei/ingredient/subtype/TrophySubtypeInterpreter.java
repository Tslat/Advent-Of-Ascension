package net.tslat.aoa3.integration.jei.ingredient.subtype;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class TrophySubtypeInterpreter implements ISubtypeInterpreter {
	public static final TrophySubtypeInterpreter INSTANCE = new TrophySubtypeInterpreter();

	@Override
	public String apply(ItemStack stack) {
		if (!stack.hasTag())
			return "";

		CompoundNBT tag = stack.getTag();

		if (!tag.contains("BlockEntityTag"))
			return "";

		CompoundNBT blockEntityTag = tag.getCompound("BlockEntityTag");

		if (!blockEntityTag.contains("EntityID"))
			return "";

		return blockEntityTag.getString("EntityID");
	}
}
