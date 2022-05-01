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

public class Stranglewood extends LogBlock {
	public static final VoxelShape BARK_AABB = Shapes.create(new AABB(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D));

	public Stranglewood() {
		super(MaterialColor.COLOR_BROWN, AoABlocks.STRIPPED_STRANGLEWOOD);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return BARK_AABB;
	}
}
