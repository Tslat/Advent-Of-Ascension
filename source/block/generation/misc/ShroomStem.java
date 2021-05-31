package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.block.WaterloggableBlock;
import net.tslat.aoa3.util.BlockUtil;

public class ShroomStem extends WaterloggableBlock {
	private static final VoxelShape SHAPE = box(5, 0, 5, 11, 16, 11);

	public ShroomStem() {
		super(new BlockUtil.CompactProperties(Material.VEGETABLE, MaterialColor.TERRACOTTA_WHITE).stats(2f, 0.5f).tool(ToolType.AXE).sound(SoundType.STEM).get());
	}

	@Override
	public boolean isToolEffective(BlockState state, ToolType tool) {
		return tool == ToolType.AXE || tool == ToolType.get("sword");
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
