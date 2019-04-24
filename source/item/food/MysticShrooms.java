package net.tslat.aoa3.item.food;

import net.tslat.aoa3.common.registration.BlockRegister;

public class MysticShrooms extends BasicFood {
	public MysticShrooms() {
		super("MysticShrooms", "mystic_shrooms", 2, 0.4f);
		BlockRegister.cropMysticShrooms.setCrop(this);
	}
}
