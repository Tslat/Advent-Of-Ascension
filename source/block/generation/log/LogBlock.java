package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class LogBlock extends net.minecraft.block.LogBlock {
	public LogBlock(MaterialColor mapColour, MaterialColor logEndMapColour) {
		super(logEndMapColour, generateBlockProperties(mapColour));
	}

	private static Properties generateBlockProperties(MaterialColor mapColour) {
		return Properties.create(Material.WOOD, mapColour)
				.hardnessAndResistance(2.0f)
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