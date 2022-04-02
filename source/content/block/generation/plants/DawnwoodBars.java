package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.util.BlockUtil;

public class DawnwoodBars extends BidirectionalStackablePlant {
	private static final VoxelShape SHAPE = Shapes.create(new AABB(0.375D, 0.0D, 0.375D, 0.625D, 1D, 0.625D));

	public DawnwoodBars() {
		super(new BlockUtil.CompactProperties(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_BLACK).stats(1f, 0f).sound(SoundType.GRASS).get());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
