package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tslat.aoa3.content.entity.mob.misc.ThornyPlantSproutEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.RandomUtil;

import java.util.function.Supplier;

public class ThornyPlantCrop extends MultiBlockCrop {
	private static final VoxelShape BOTTOM_FULL_SHAPE = BlockUtil.pixelBasedCube(0, 0, 0, 16, 1, 16);

	public ThornyPlantCrop(MaterialColor colour, Supplier<Item> seedItem) {
		super(colour, seedItem);
	}

	@Override
	protected void populateShapes(VoxelShape[][] shapeArray) {
		shapeArray[0][0] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 3, 16);
		shapeArray[0][1] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 7, 16);
		shapeArray[0][2] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 9, 16);
		shapeArray[0][3] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 12, 16);
		shapeArray[0][4] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 16, 16);
		shapeArray[1][0] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 3, 16);
		shapeArray[1][1] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 8, 16);
		shapeArray[1][2] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 13, 16);
		shapeArray[1][3] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 15, 16);
		shapeArray[1][4] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 16, 16);
		shapeArray[2][0] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 7, 16);
		shapeArray[2][1] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 10, 16);
		shapeArray[2][2] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 13, 16);
		shapeArray[2][3] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 15, 16);
		shapeArray[2][4] = BlockUtil.pixelBasedCube(0, 0, 0, 16, 15, 16);
	}

	@Override
	public VoxelShape getBlockSupportShape(BlockState pState, IBlockReader pReader, BlockPos pPos) {
		return super.getBlockSupportShape(pState, pReader, pPos);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
		return super.getCollisionShape(pState, pLevel, pPos, pContext);
	}

	@Override
	public VoxelShape getInteractionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
		return super.getInteractionShape(pState, pLevel, pPos);
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
		return super.getOcclusionShape(pState, pLevel, pPos);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		if (isMaxAge(state) && world instanceof World) {
			return state.getValue(getHeightProperty()) == 0 ? BOTTOM_FULL_SHAPE : VoxelShapes.empty();
		}

		return super.getShape(state, world, pos, context);
	}

	@Override
	public VoxelShape getVisualShape(BlockState pState, IBlockReader pReader, BlockPos pPos, ISelectionContext pContext) {
		return super.getVisualShape(pState, pReader, pPos, pContext);
	}

	@Override
	public BlockRenderType getRenderShape(BlockState pState) {
		return super.getRenderShape(pState);
	}

	@Override
	protected void growDown(World world, BlockPos pos, BlockState currentState) {
		super.growDown(world, pos, currentState);

		pos = pos.below(currentState.getValue(getHeightProperty()));

		if (!world.isClientSide() && currentState.getValue(getAgeProperty()) == getMaxAge() - 1 && currentState.getValue(getHeightProperty()) == getGrowthHeight() - 1) {
			int number = RandomUtil.randomNumberBetween(1, 3);

			for (int i = 0; i < number; i++) {
				world.addFreshEntity(new ThornyPlantSproutEntity(world, pos));
			}
		}
	}
}
