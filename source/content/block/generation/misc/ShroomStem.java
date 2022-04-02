package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.content.block.WaterloggableBlock;
import net.tslat.aoa3.util.BlockUtil;

public class ShroomStem extends WaterloggableBlock {
	private static final VoxelShape SHAPE = box(5, 0, 5, 11, 16, 11);

	public ShroomStem() {
		super(new BlockUtil.CompactProperties(Material.VEGETABLE, MaterialColor.TERRACOTTA_WHITE).stats(2f, 0.5f).sound(SoundType.STEM).get());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
