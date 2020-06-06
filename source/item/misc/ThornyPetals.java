package net.tslat.aoa3.item.misc;

import net.tslat.aoa3.common.registration.BlockRegister;

public class ThornyPetals extends SimpleItem {
	public ThornyPetals() {
		super("ThornyPetals", "thorny_petals");
		BlockRegister.THORNY_PLANT_CROP.setCrop(this);
	}
}
