package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.util.BlockUtil;

public class BoneyBlock extends Block {
	private static final VoxelShape SHAPE = Shapes.create(new AABB(0.001d, 0.001d, 0.001d, 0.999d, 0.999d, 0.999d));

	public BoneyBlock() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_GRAY).stats(3f).get());
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return SHAPE;
	}
}
