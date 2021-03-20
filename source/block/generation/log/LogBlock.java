package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock.Properties;

public class LogBlock extends RotatedPillarBlock {
	public LogBlock(MaterialColor mapColour, MaterialColor logEndMapColour) {
		super(generateBlockProperties(mapColour, logEndMapColour));
	}

	private static Properties generateBlockProperties(MaterialColor mapColour, MaterialColor logEndMapColour) {
		return AbstractBlock.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? logEndMapColour : mapColour)
				.strength(2.0f)
				.sound(SoundType.WOOD)
				.harvestTool(ToolType.AXE);
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