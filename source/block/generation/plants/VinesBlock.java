package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.util.BlockUtil;

public class VinesBlock extends VineBlock {
	public VinesBlock(MaterialColor mapColour) {
		super(new BlockUtil.CompactProperties(Material.REPLACEABLE_PLANT, mapColour).stats(0.2f, 0f).sound(SoundType.GRASS).noClip().randomTicks().get());
	}

	public BlockState getStateForPosition(IWorld world, BlockPos pos) {
		BlockState state = defaultBlockState();
		boolean attached = false;

		 if (VineBlock.isAcceptableNeighbour(world, pos.relative(Direction.UP), Direction.UP)) {
			 state = state.setValue(UP, true);
			 attached = true;
		 }

		 if (VineBlock.isAcceptableNeighbour(world, pos.relative(Direction.NORTH), Direction.NORTH)) {
		 	state = state.setValue(NORTH, true);
			 attached = true;
		 }

		 if (VineBlock.isAcceptableNeighbour(world, pos.relative(Direction.SOUTH), Direction.SOUTH)) {
		 	state = state.setValue(SOUTH, true);
			 attached = true;
		 }

		 if (VineBlock.isAcceptableNeighbour(world, pos.relative(Direction.EAST), Direction.EAST)) {
		 	state = state.setValue(EAST, true);
			 attached = true;
		 }

		 if (VineBlock.isAcceptableNeighbour(world, pos.relative(Direction.WEST), Direction.WEST)) {
		 	state = state.setValue(WEST, true);
			 attached = true;
		 }

		 if (!attached) {
		 	BlockState upState = world.getBlockState(pos.above());

		 	if (upState.getBlock() == this)
		 		return upState;
		 }

		 return state;
	}
}
