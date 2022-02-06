package net.tslat.aoa3.object.block.functional.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.Constants;

import java.util.Random;
import java.util.function.Supplier;

public abstract class MultiBlockCrop extends CropBlock {
	public IntegerProperty HEIGHT = null;
	public IntegerProperty AGE = null;
	private final VoxelShape[][] SHAPES;

	public MultiBlockCrop(MaterialColor colour, Supplier<Item> seedItem) {
		super(colour, seedItem);

		registerDefaultState(defaultBlockState().setValue(getHeightProperty(), 0));
		populateShapes(SHAPES = new VoxelShape[getGrowthHeight()][stagesPerBlock()]);
	}

	protected abstract void populateShapes(VoxelShape[][] shapeArray);

	public int getGrowthHeight() {
		return 3;
	}

	public int stagesPerBlock() {
		return 5;
	}

	public IntegerProperty getHeightProperty() {
		if (HEIGHT != null)
			return HEIGHT;

		HEIGHT = IntegerProperty.create("height", 0, getGrowthHeight() - 1);

		return HEIGHT;
	}

	@Override
	public IntegerProperty getAgeProperty() {
		if (AGE != null)
			return AGE;

		AGE = IntegerProperty.create("age", 0, stagesPerBlock() * getGrowthHeight() - 1);

		return AGE;
	}

	@Override
	public int getMaxAge() {
		return getGrowthHeight() * stagesPerBlock() - 1;
	}

	public boolean isMaxAgeForPart(BlockState state) {
		return state.getValue(getAgeProperty()) >= stagesPerBlock() * (1 + state.getValue(getHeightProperty())) - 1;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		int height = state.getValue(getHeightProperty());

		return SHAPES[height][MathHelper.clamp(state.getValue(getAgeProperty()), 0, stagesPerBlock() - 1)];
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
		return (state.getBlock() == this && isMaxAgeForPart(state)) || state.getBlock() instanceof FarmlandBlock;
	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
		if (state.getBlock() == this)
			return state.getValue(getHeightProperty()) < getGrowthHeight() && isMaxAgeForPart(state);

		return super.canSustainPlant(state, world, pos, facing, plantable);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
		if (!state.canSurvive(world, pos))
			return Blocks.AIR.defaultBlockState();

		if (state.getValue(getAgeProperty()) >= (1 + state.getValue(getHeightProperty())) * stagesPerBlock() && world.getBlockState(pos.above()).getBlock() != this)
			return Blocks.AIR.defaultBlockState();

		return super.updateShape(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		if (!super.canSurvive(state, world, pos))
			return false;

		for (int i = 1; i < getGrowthHeight() + 1 && pos.getY() - i >= 0; i++) {
			BlockState testState = world.getBlockState(pos.below(i));

			if (testState.getBlock() instanceof FarmlandBlock)
				return true;

			if (testState.getBlock() != this || !isMaxAgeForPart(testState))
				return false;
		}

		return false;
	}

	@Override
	public void growCrops(World world, BlockPos pos, BlockState state) {
		growDown(world, pos, state);
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean pIsClient) {
		if (isMaxAge(state))
			return false;

		if (!isMaxAgeForPart(state))
			return true;

		while ((state = world.getBlockState(pos = pos.above())).getBlock() == this) {}

		return state.isAir(world, pos);
	}

	@Override
	protected int getBonemealAgeIncrease(World pLevel) {
		return 1;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		while (state.getValue(getAgeProperty()) > (1 + state.getValue(getHeightProperty())) * stagesPerBlock() - 1) {
			state = world.getBlockState(pos = pos.above());

			if (state.getBlock() != this)
				return;
		}

		growCrops(world, pos, state);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		int age = state.getValue(getAgeProperty());

		if (age == getMaxAge())
			return false;

		return age <= stagesPerBlock() * (1 + state.getValue(getHeightProperty())) - 1;
	}

	protected void growDown(World world, BlockPos pos, BlockState currentState) {
		int age = getAge(currentState);
		int height = currentState.getValue(getHeightProperty());
		BlockState agedState = getStateForAge(age + 1);

		if (isMaxAgeForPart(currentState) && height < getGrowthHeight() - 1 && world.isEmptyBlock(pos.above()))
			world.setBlock(pos.above(), agedState.setValue(getHeightProperty(), height + 1), Constants.BlockFlags.BLOCK_UPDATE);

		for (int i = 0; i <= height; i++) {
			BlockPos newPos = pos.below(i);

			if (world.getBlockState(newPos).getBlock() == this)
				world.setBlock(newPos, agedState.setValue(getHeightProperty(), height - i), Constants.BlockFlags.BLOCK_UPDATE);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!world.isAreaLoaded(pos, 1))
			return; // Forge: prevent loading unloaded chunks when checking neighbor's light

		if (world.getBlockState(pos.above()).getBlock() == this)
			return;

		if (!isMaxAge(state) && world.getRawBrightness(pos, 0) >= 9) {
			int age = getAge(state);
			int height = state.getValue(getHeightProperty());

			if (age <= stagesPerBlock() * (1 + height) - 1) {
				float growthSpeed = getGrowthSpeed(this, world, pos);

				if (ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt((int)(25f / growthSpeed) + 1) == 0)) {
					growDown(world, pos, state);
					ForgeHooks.onCropsGrowPost(world, pos, state);
				}
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(getAgeProperty()).add(getHeightProperty());
	}
}
