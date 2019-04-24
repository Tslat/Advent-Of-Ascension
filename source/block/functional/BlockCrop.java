package net.nevermine.block.functional;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCrop extends BlockCrops {
	private String use;
	public IIcon[] iconArray;

	public BlockCrop(final String nameplant) {
		use = nameplant;
		setTickRandomly(true);
	}

	public void registerBlockIcons(final IIconRegister par1IconRegister) {
		iconArray = new IIcon[5];
		for (int i = 0; i < iconArray.length; ++i) {
			iconArray[i] = par1IconRegister.registerIcon("nevermine:" + use + "_" + i);
		}
	}

	public IIcon getIcon(final int par1, int par2) {
		switch (par2) {
			case 0:
				return iconArray[0];
			case 1:
			case 2:
				return iconArray[1];
			case 3:
			case 4:
				return iconArray[2];
			case 5:
			case 6:
				return iconArray[3];
			case 7:
				return iconArray[4];
		}

		return iconArray[0];
	}
}
