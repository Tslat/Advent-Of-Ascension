package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.content.block.WaterloggableBlock;
import net.tslat.aoa3.util.BlockUtil;

public class ChargingTable extends WaterloggableBlock {
	private static final VoxelShape BOTTOM_SHAPE = box(0, 0, 0, 16, 2, 16);
	private static final VoxelShape MID_SHAPE = box(3, 2, 3, 13, 10, 13);
	private static final VoxelShape TOP_SHAPE = box(5, 10, 5, 11, 16, 11);
	private static final VoxelShape SHAPE = VoxelShapes.or(BOTTOM_SHAPE, MID_SHAPE, TOP_SHAPE);

	public ChargingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY).stats(5f, 4f).harvestTool(ToolType.PICKAXE).get());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
