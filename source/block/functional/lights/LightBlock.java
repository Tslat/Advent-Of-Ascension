package net.tslat.aoa3.block.functional.lights;

import net.minecraft.block.material.Material;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class LightBlock extends BasicBlock {
	public LightBlock(String name, String registryName, Material material, float luminosity, float hardness, float resistance) {
		super(name, registryName, material, hardness, resistance);
		setLightLevel(luminosity);
		setLightOpacity(0);
		setCreativeTab(CreativeTabsRegister.decorationBlocksTab);
	}
}
