package net.tslat.aoa3.block.decoration.glass;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.tslat.aoa3.util.BlockUtil;

public class GlassBlock extends net.minecraft.block.GlassBlock {
	public GlassBlock(MaterialColor mapColour, float hardness, float resistance) {
		super(BlockUtil.generateBlockProperties(Material.GLASS, mapColour, hardness, resistance).notSolid());
	}
}
