package net.tslat.aoa3.content.block.decoration.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.tslat.aoa3.util.BlockUtil;

public class PlankBlock extends Block {
	public PlankBlock(MaterialColor materialColour) {
		super(new BlockUtil.CompactProperties(Material.WOOD, materialColour).stats(2f, 3f).get());
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 20;
	}
}
