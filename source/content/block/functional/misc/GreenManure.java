package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.block.functional.plant.CropBlock;
import net.tslat.aoa3.util.BlockUtil;

public class GreenManure extends CropBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[] {
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 2, 16),
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 3, 16),
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 4, 16),
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 5, 16),
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 5, 16),
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 6, 16),
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 6, 16),
		BlockUtil.pixelBasedCube(0, 0, 0, 16, 7, 16)
	};

	public GreenManure() {
		super(MaterialColor.COLOR_GREEN, AoAItems.GREEN_MANURE_SEEDS);
	}

	@Override
	public void destroy(LevelAccessor world, BlockPos pos, BlockState state) {
		if (getAge(state) >= getMaxAge()) {
			BlockState groundState = world.getBlockState(pos.below());

			if (groundState.getBlock() instanceof FarmBlock)
				world.setBlock(pos.below(), AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState().setValue(FertilisedFarmland.WELL_FERTILISED, true).setValue(FarmBlock.MOISTURE, groundState.getValue(FarmBlock.MOISTURE)), Block.UPDATE_ALL);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(AGE)];
	}
}
