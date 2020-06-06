package net.tslat.aoa3.item.misc;

import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class AmmoItem extends SimpleItem {
	public AmmoItem(String name, String registryName) {
		super(name, registryName);
		setCreativeTab(CreativeTabsRegister.AMMUNITION);
	}
}
