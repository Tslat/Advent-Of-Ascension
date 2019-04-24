package net.tslat.aoa3.item.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class OverworldIngot extends SimpleItem {
	public OverworldIngot(String name, String registryName) {
		super(name, registryName);
		setCreativeTab(CreativeTabsRegister.miscTab);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab)) {
			for (int i = 0; i < 7; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	public static String getIngotType(ItemStack stack) {
		switch (stack.getMetadata()) {
			case 0:
				return "Limonite";
			case 1:
				return "Sapphire";
			case 2:
				return "Jade";
			case 3:
				return "Rosite";
			case 4:
				return "Amethyst";
			case 5:
				return "Runium";
			case 6:
				return "RustedIron";
			default:
				return "Limonite";
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return I18n.translateToLocal("item." + getIngotType(stack) + "Ingot");
	}
}
