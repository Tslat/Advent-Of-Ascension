package net.tslat.aoa3.item.food;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class MysticShrooms extends ItemSeedFood {
	public MysticShrooms() {
		super(2, 0.4f, BlockRegister.MYSTIC_SHROOM_CROP, Blocks.FARMLAND);
		setTranslationKey("MysticShrooms");
		setRegistryName("aoa3:mystic_shrooms");
		setCreativeTab(CreativeTabsRegister.FOOD);
		BlockRegister.MYSTIC_SHROOM_CROP.setCrop(this);
		BlockRegister.MYSTIC_SHROOM_CROP.setSeeds(this);
	}
}
