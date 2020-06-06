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
				return WeaponRegister.AQUA_CANNON;
			case 1:
				return WeaponRegister.CORAL_ARCHERGUN;
			case 2:
				return WeaponRegister.CORAL_CANNON;
			case 3:
				return WeaponRegister.CORAL_CLOGGER;
			case 4:
				return WeaponRegister.REEFER;
			default:
				return null;
		}
	}
}
