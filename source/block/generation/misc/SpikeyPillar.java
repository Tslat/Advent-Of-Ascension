package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.tslat.aoa3.block.decoration.BasicDecorationBlock;

public class SpikeyPillar extends BasicDecorationBlock {
	public SpikeyPillar() {
		super("SpikeyPillar", "spikey_pillar", Material.ROCK);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
}
