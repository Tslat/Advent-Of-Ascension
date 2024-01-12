package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class InfusionStone extends Item {
	private final DeferredItem<Item> powerStone;
	private final int lvl;
	private final float xp;

	public InfusionStone(int lvl, float xp, DeferredItem<Item> powerStone) {
		super(new Item.Properties());
		this.lvl = lvl;
		this.xp = xp;
		this.powerStone = powerStone;
	}

	public Item getPowerStone() {
		return powerStone.get();
	}

	public int getLvl() {
		return lvl;
	}

	public float getXp() {
		return xp;
	}
}
