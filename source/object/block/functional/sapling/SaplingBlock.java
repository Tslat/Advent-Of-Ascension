package net.tslat.aoa3.object.block.functional.sapling;

import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.tslat.aoa3.common.registration.AoATags;

public class SaplingBlock extends net.minecraft.block.SaplingBlock {
	public SaplingBlock(Tree tree, Properties properties) {
		super(tree, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
		return state.is(AoATags.Blocks.GRASS) || super.mayPlaceOn(state, world, pos);
	}
}
