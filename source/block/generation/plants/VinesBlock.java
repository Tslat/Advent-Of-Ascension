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
		super(BlockUtil.generateBlockProperties(Material.TALL_PLANTS, mapColour, 0.2f, 0, SoundType.PLANT).doesNotBlockMovement().tickRandomly());
	}

	public BlockState getStateForPosition(IWorld world, BlockPos pos) {
		BlockState state = getDefaultState();
		boolean attached = false;

		 if (VineBlock.canAttachTo(world, pos.offset(Direction.UP), Direction.UP)) {
			 state = state.with(UP, true);
			 attached = true;
		 }

		 if (VineBlock.canAttachTo(world, pos.offset(Direction.NORTH), Direction.NORTH)) {
		 	state = state.with(NORTH, true);
			 attached = true;
		 }

		 if (VineBlock.canAttachTo(world, pos.offset(Direction.SOUTH), Direction.SOUTH)) {
		 	state = state.with(SOUTH, true);
			 attached = true;
		 }

		 if (VineBlock.canAttachTo(world, pos.offset(Direction.EAST), Direction.EAST)) {
		 	state = state.with(EAST, true);
			 attached = true;
		 }

		 if (VineBlock.canAttachTo(world, pos.offset(Direction.WEST), Direction.WEST)) {
		 	state = state.with(WEST, true);
			 attached = true;
		 }

		 if (!attached) {
		 	BlockState upState = world.getBlockState(pos.up());

		 	if (upState.getBlock() == this)
		 		return upState;
		 }

		 return state;
	}
}
