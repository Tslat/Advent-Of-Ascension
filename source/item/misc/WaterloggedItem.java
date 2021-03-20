package net.tslat.aoa3.item.misc;

import net.minecraft.item.Item;

import java.util.function.Supplier;

import net.minecraft.item.Item.Properties;

public class WaterloggedItem extends Item {
	private final Supplier<Item> decloggedItem;

	public WaterloggedItem(Supplier<Item> decloggedItem, Properties properties) {
		super(properties);

		this.decloggedItem = decloggedItem;
	}

	public Item getDecloggedItem() {
		return this.decloggedItem.get();
	}
}
