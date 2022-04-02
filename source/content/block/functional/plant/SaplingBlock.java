package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoATags;

public class SaplingBlock extends net.minecraft.world.level.block.SaplingBlock {
	public SaplingBlock(AbstractTreeGrower tree, Properties properties) {
		super(tree, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
		return state.is(AoATags.Blocks.GRASS) || super.mayPlaceOn(state, world, pos);
	}
}
