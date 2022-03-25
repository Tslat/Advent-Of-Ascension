package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
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
	public void destroy(IWorld world, BlockPos pos, BlockState state) {
		if (getAge(state) >= getMaxAge()) {
			BlockState groundState = world.getBlockState(pos.below());

			if (groundState.getBlock() instanceof FarmlandBlock)
				world.setBlock(pos.below(), AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState().setValue(FertilisedFarmland.WELL_FERTILISED, true).setValue(FarmlandBlock.MOISTURE, groundState.getValue(FarmlandBlock.MOISTURE)), Constants.BlockFlags.DEFAULT);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.getValue(AGE)];
	}
}
