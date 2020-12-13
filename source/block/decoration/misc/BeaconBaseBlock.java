package net.tslat.aoa3.block.decoration.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class BeaconBaseBlock extends Block {
	public BeaconBaseBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isToolEffective(BlockState state, ToolType tool) {
		return false;
	}

	@Override
	public boolean isBeaconBase(BlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
		return true;
	}
}
