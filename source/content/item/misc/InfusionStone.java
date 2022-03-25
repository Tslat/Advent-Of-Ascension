package net.tslat.aoa3.content.item.misc;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class InfusionStone extends Item {
	private final RegistryObject<Item> powerStone;
	private final int lvl;
	private final float xp;

	public InfusionStone(int lvl, float xp, RegistryObject<Item> powerStone) {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
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
