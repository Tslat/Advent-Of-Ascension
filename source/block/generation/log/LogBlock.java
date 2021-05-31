package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.BlockUtil;

public class LogBlock extends RotatedPillarBlock {
	public LogBlock(MaterialColor mapColour, MaterialColor logEndMapColour) {
		super(new BlockUtil.CompactProperties(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? logEndMapColour : mapColour).stats(2f).tool(ToolType.AXE).get());
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 5;
	}
}