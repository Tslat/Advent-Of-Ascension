package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

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
