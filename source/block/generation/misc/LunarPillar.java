package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.tslat.aoa3.block.decoration.BasicDecorationBlock;

public class LunarPillar extends BasicDecorationBlock {
	public LunarPillar() {
		super("LunarPillar", "lunar_pillar", Material.ROCK);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
}