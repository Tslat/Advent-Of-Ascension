package net.tslat.aoa3.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.advent.AdventOfAscension;

public class OreDictUtil {
	public static void addDefaultEntries() {
		AdventOfAscension.logOptionalMessage("Adding default OreDict entries");

		for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
			if (item instanceof ItemFood) {
				NonNullList<ItemStack> subItems = NonNullList.create();

				item.getSubItems(CreativeTabs.SEARCH, subItems);

				for (ItemStack stack : subItems) {
					AdventOfAscension.logOptionalMessage("Found food item: " + item.getRegistryName());
					OreDictionary.registerOre("listAllFood", stack);
				}
			}
		}

		OreDictionary.registerOre("foodAllMushroom", Blocks.BROWN_MUSHROOM);
		OreDictionary.registerOre("foodAllMushroom", Blocks.RED_MUSHROOM);
	}
}
