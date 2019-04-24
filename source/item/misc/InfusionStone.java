package net.tslat.aoa3.item.misc;

import net.minecraft.item.Item;

public class InfusionStone extends SimpleItem {
	private final Item powerStone;
	private final int lvl;
	private final float xp;

	public InfusionStone(String name, String registryName, int lvl, float xp, Item powerStone) {
		super(name, registryName);
		this.lvl = lvl;
		this.xp = xp;
		this.powerStone = powerStone;
	}

	public Item getPowerStone() {
		return powerStone;
	}

	public int getLvl() {
		return lvl;
	}

	public float getXp() {
		return xp;
	}
}
