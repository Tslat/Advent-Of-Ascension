package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.content.entity.mob.misc.ThornyPlantSproutEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

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
	public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return super.getBlockSupportShape(pState, pReader, pPos);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return super.getCollisionShape(pState, pLevel, pPos, pContext);
	}

	@Override
	public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return super.getInteractionShape(pState, pLevel, pPos);
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return super.getOcclusionShape(pState, pLevel, pPos);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (isMaxAge(state) && world instanceof Level) {
			return state.getValue(getHeightProperty()) == 0 ? BOTTOM_FULL_SHAPE : Shapes.empty();
		}

		return super.getShape(state, world, pos, context);
	}

	@Override
	public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
		return super.getVisualShape(pState, pReader, pPos, pContext);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return super.getRenderShape(pState);
	}

	@Override
	protected void growDown(Level world, BlockPos pos, BlockState currentState) {
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
