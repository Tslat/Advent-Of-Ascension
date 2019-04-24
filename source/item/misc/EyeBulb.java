package net.tslat.aoa3.item.misc;

import net.tslat.aoa3.common.registration.BlockRegister;

public class EyeBulb extends SimpleItem {
	public EyeBulb() {
		super("EyeBulb", "eye_bulb");
		BlockRegister.cropEyeBulbs.setCrop(this);
	}
}
