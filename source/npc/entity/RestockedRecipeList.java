package net.nevermine.npc.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.village.MerchantRecipeList;

public class RestockedRecipeList extends MerchantRecipeList {
	public RestockedRecipeList() {
	}

	public RestockedRecipeList(final NBTTagCompound tag) {
		super(tag);
	}

	public void readRecipiesFromTags(final NBTTagCompound tag) {
		final NBTTagList list = tag.getTagList("Recipes", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			final NBTTagCompound nbttagcompound1 = list.getCompoundTagAt(i);
			add(new RestockedRecipe(nbttagcompound1));
		}
	}
}
