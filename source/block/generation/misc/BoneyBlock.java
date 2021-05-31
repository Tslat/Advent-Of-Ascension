package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.tslat.aoa3.util.BlockUtil;

public class BoneyBlock extends Block {
	private static final VoxelShape SHAPE = VoxelShapes.create(new AxisAlignedBB(0.001d, 0.001d, 0.001d, 0.999d, 0.999d, 0.999d));

	public BoneyBlock() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_GRAY).stats(3f).get());
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPE;
	}
}
