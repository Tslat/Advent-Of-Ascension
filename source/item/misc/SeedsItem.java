package net.tslat.aoa3.item.misc;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.tslat.aoa3.block.functional.crops.CropBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class SeedsItem extends ItemSeeds {
	public SeedsItem(String name, String registryName, CropBlock plant, Block growthMaterial) {
		super(plant, growthMaterial);
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.MISC);
		plant.setSeeds(this);
	}
}
