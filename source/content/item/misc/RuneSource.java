package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;

public class RuneSource extends Item {
	private final int runeGenFactor;

	public RuneSource(int runeGenFactor) {
		super(new Item.Properties());

		this.runeGenFactor = runeGenFactor;
	}

	public int getRuneGenFactor() {
		return this.runeGenFactor;
	}
}
