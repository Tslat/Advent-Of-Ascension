package net.tslat.aoa3.item.misc;

import net.tslat.aoa3.common.registration.BlockRegister;

public class HollyTopPetals extends SimpleItem {
	public HollyTopPetals() {
		super("HollyTopPetals", "holly_top_petals");
		BlockRegister.HOLLY_TOPS_CROP.setCrop(this);
	}
}
