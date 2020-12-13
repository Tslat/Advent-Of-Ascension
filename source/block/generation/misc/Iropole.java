package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.tslat.aoa3.block.WaterloggableBlock;
import net.tslat.aoa3.util.BlockUtil;

public class Iropole extends WaterloggableBlock {
	private static final VoxelShape SHAPE = makeCuboidShape(3, 0, 3, 13, 16, 13);

	public Iropole() {
		super(BlockUtil.generateBlockProperties(Material.IRON, MaterialColor.BLACK, 1.5f, 10f, SoundType.METAL));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
