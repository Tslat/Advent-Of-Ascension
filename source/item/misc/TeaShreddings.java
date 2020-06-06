package net.tslat.aoa3.item.misc;

import net.tslat.aoa3.common.registration.BlockRegister;

public class TeaShreddings extends SimpleItem {
	public TeaShreddings() {
		super("TeaShreddings", "tea_shreddings");
		BlockRegister.TEA_CROP.setCrop(this);
	}
}
