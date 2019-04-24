package net.tslat.aoa3.item.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class DimensionIngot extends SimpleItem {
	public DimensionIngot(String name, String registryName) {
		super(name, registryName);
		setCreativeTab(CreativeTabsRegister.miscTab);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab)) {
			for (int i = 0; i < 14; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	public static String getIngotType(ItemStack stack) {
		switch (stack.getMetadata()) {
			case 0:
				return "Emberstone";
			case 1:
				return "Mystite";
			case 2:
				return "Lyon";
			case 3:
				return "Varsium";
			case 4:
				return "Elecanium";
			case 5:
				return "Baronyte";
			case 6:
				return "Blazium";
			case 7:
				return "Ghastly";
			case 8:
				return "Ghoulish";
			case 9:
				return "Lunar";
			case 10:
				return "Shyrestone";
			case 11:
				return "Shyregem";
			case 12:
				return "Skeletal";
			case 13:
				return "ChargedRunium";
			default:
				return "Emberstone";
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return I18n.translateToLocal("item." + getIngotType(stack) + "Ingot");
	}
}
