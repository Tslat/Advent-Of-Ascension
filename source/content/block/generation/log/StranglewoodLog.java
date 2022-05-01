package net.tslat.aoa3.content.block.generation.log;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.block.AoABlocks;

public class StranglewoodLog extends LogBlock {
	public static final VoxelShape VERTICAL_AABB = Shapes.create(new AABB(0.001D, 0.0D, 0.001D, 0.999D, 1.0D, 0.999D));
	public static final VoxelShape NORTH_SOUTH_AABB = Shapes.create(new AABB(0.0D, 0.001D, 0.001D, 1.0D, 0.999D, 0.999D));
	public static final VoxelShape EAST_WEST_AABB = Shapes.create(new AABB(0.001D, 0.001D, 0.0D, 0.999D, 0.999D, 1.0D));

	public StranglewoodLog() {
		super(MaterialColor.COLOR_BROWN, MaterialColor.TERRACOTTA_BROWN, AoABlocks.STRIPPED_STRANGLEWOOD_LOG);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(AXIS)) {
			case X -> NORTH_SOUTH_AABB;
			case Z -> EAST_WEST_AABB;
			default -> VERTICAL_AABB;
		};
	}
}
