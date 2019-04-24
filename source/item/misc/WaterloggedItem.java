package net.tslat.aoa3.item.misc;

import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.WeaponRegister;

public class WaterloggedItem extends SimpleItem {
	private final int fixedItem;

	public WaterloggedItem(String name, String registryName, int fixedItemIndex) {
		super(name, registryName);
		this.fixedItem = fixedItemIndex;
	}

	public Item getFixedItem() {
		switch (fixedItem) {
			case 0:
				return WeaponRegister.cannonAquaCannon;
			case 1:
				return WeaponRegister.archergunCoral;
			case 2:
				return WeaponRegister.cannonCoralCannon;
			case 3:
				return WeaponRegister.gunCoralClogger;
			case 4:
				return WeaponRegister.blasterReefer;
			default:
				return null;
		}
	}
}
