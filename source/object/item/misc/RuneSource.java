package net.tslat.aoa3.object.item.misc;

import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class RuneSource extends Item {
	private final int runeGenFactor;

	public RuneSource(int runeGenFactor) {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));

		this.runeGenFactor = runeGenFactor;
	}

	public int getRuneGenFactor() {
		return this.runeGenFactor;
	}
}
