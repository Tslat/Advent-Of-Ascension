package net.tslat.aoa3.item.misc;

import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class SimpleItem extends Item {
	public SimpleItem(String name, String registryName) {
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.miscTab);
	}
}
