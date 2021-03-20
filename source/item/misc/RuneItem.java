package net.tslat.aoa3.item.misc;

import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class RuneItem extends Item {
	private final boolean charged;

	public RuneItem(boolean isChargedRune) {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));

		this.charged = isChargedRune;
	}

	public boolean isCharged() {
		return charged;
	}
}
