package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;

public class RuneSource extends Item {
	private final int runeGenFactor;

	public RuneSource(int runeGenFactor) {
		super(new Item.Properties().tab(AoACreativeModeTabs.MISC_ITEMS));

		this.runeGenFactor = runeGenFactor;
	}

	public int getRuneGenFactor() {
		return this.runeGenFactor;
	}
}
