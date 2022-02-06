package net.tslat.aoa3.object.block.functional.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.block.functional.plant.CropBlock;
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
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (getAge(state) >= getMaxAge()) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (heldStack.getItem().getToolTypes(heldStack).contains(ToolType.HOE)) {
				if (world.getBlockState(pos.below()).getBlock() instanceof FarmlandBlock)
					world.setBlock(pos.below(), AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState().setValue(FertilisedFarmland.WELL_FERTILISED, true), Constants.BlockFlags.NO_NEIGHBOR_DROPS);

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.getValue(AGE)];
	}
}
