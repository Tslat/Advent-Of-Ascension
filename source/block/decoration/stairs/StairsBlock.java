package net.tslat.aoa3.block.decoration.stairs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class StairsBlock extends BlockStairs {
	public StairsBlock(String name, String registryName, Block baseBlock) {
		super(baseBlock.getDefaultState());
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.DECORATION_BLOCKS);
		useNeighborBrightness = true;

		if (baseBlock.getMaterial(getDefaultState()) == Material.WOOD)
			Blocks.FIRE.setFireInfo(this, 5, 20);
	}
}
