package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.tslat.aoa3.util.BlockUtil;

public class WoodButtonBlock extends net.minecraft.block.WoodButtonBlock {
	public WoodButtonBlock(MaterialColor mapColour) {
		super(BlockUtil.generateBlockProperties(Material.DECORATION, mapColour, 0.5f, 0.5f, SoundType.WOOD).noCollission());
	}
}
