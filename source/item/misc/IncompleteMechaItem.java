package net.tslat.aoa3.item.misc;

import net.minecraft.item.Item;

public class IncompleteMechaItem extends SimpleItem {
	private final Item fixedItem;

	public IncompleteMechaItem(String name, String registryName, Item fixedItem) {
		super(name, registryName);
		this.fixedItem = fixedItem;
	}

	public Item getFixedItem() {
		return fixedItem;
	}
}
