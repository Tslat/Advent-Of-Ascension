package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class StaticMushroomBlock extends MushroomBlock {
	public StaticMushroomBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return false;
	}
}
