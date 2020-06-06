package net.tslat.aoa3.item.misc;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class EyeBulb extends ItemSeedFood {
	public EyeBulb() {
		super(2, 0.4f, BlockRegister.EYE_BULB_CROP, Blocks.FARMLAND);
		setTranslationKey("EyeBulb");
		setRegistryName("aoa3:eye_bulb");
		setCreativeTab(CreativeTabsRegister.MISC);
		BlockRegister.EYE_BULB_CROP.setCrop(this);
		BlockRegister.EYE_BULB_CROP.setSeeds(this);
	}
}
