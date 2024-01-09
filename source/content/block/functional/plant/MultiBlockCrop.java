package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.IPlantable;

import java.util.function.Supplier;

public abstract class MultiBlockCrop extends AoACropBlock {
	public IntegerProperty HEIGHT = null;
	public IntegerProperty AGE = null;
	private final VoxelShape[][] SHAPES;

	public MultiBlockCrop(BlockBehaviour.Properties properties, Supplier<Item> seedItem) {
		super(properties, seedItem);

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
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		int height = state.getValue(getHeightProperty());
		int relativeGrowth = Mth.clamp(state.getValue(getAgeProperty()) - (state.getValue(getHeightProperty()) * (stagesPerBlock() - 1) + 1), 0, stagesPerBlock() - 1);

		return SHAPES[height][relativeGrowth];
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
		return (state.getBlock() == this && isMaxAgeForPart(state)) || state.getBlock() instanceof FarmBlock;
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
		if (state.getBlock() == this)
			return state.getValue(getHeightProperty()) < getGrowthHeight() && isMaxAgeForPart(state);

		return super.canSustainPlant(state, world, pos, facing, plantable);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
		if (!state.canSurvive(world, pos))
			return Blocks.AIR.defaultBlockState();

		if (state.getValue(getAgeProperty()) >= (1 + state.getValue(getHeightProperty())) * stagesPerBlock() && world.getBlockState(pos.above()).getBlock() != this)
			return Blocks.AIR.defaultBlockState();

		return super.updateShape(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		if (!super.canSurvive(state, world, pos))
			return false;

		for (int i = 1; i < getGrowthHeight() + 1 && pos.getY() - i >= 0; i++) {
			BlockState testState = world.getBlockState(pos.below(i));

			if (testState.getBlock() instanceof FarmBlock)
				return true;

			if (testState.getBlock() != this || !isMaxAgeForPart(testState))
				return false;
		}

		return false;
	}

	@Override
	public void growCrops(Level world, BlockPos pos, BlockState state) {
		growDown(world, pos, state);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		if (isMaxAge(state))
			return false;

		if (!isMaxAgeForPart(state))
			return true;

		while ((state = level.getBlockState(pos = pos.above())).getBlock() == this) {}

		return state.isAir();
	}

	@Override
	protected int getBonemealAgeIncrease(Level pLevel) {
		return 1;
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {
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

	protected void growDown(Level world, BlockPos pos, BlockState currentState) {
		int age = getAge(currentState);
		int height = currentState.getValue(getHeightProperty());
		BlockState agedState = getStateForAge(age + 1);

		if (isMaxAgeForPart(currentState) && height < getGrowthHeight() - 1 && world.isEmptyBlock(pos.above()))
			world.setBlock(pos.above(), agedState.setValue(getHeightProperty(), height + 1), Block.UPDATE_ALL);

		for (int i = 0; i <= height; i++) {
			BlockPos newPos = pos.below(i);

			if (world.getBlockState(newPos).getBlock() == this)
				world.setBlock(newPos, agedState.setValue(getHeightProperty(), height - i), Block.UPDATE_ALL);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (!world.isAreaLoaded(pos, 1))
			return; // Forge: prevent loading unloaded chunks when checking neighbor's light

		if (world.getBlockState(pos.above()).getBlock() == this)
			return;

		if (!isMaxAge(state) && world.getRawBrightness(pos, 0) >= 9) {
			int age = getAge(state);
			int height = state.getValue(getHeightProperty());

			if (age <= stagesPerBlock() * (1 + height) - 1) {
				float growthSpeed = getGrowthSpeed(this, world, pos);

				if (CommonHooks.onCropsGrowPre(world, pos, state, rand.nextInt((int)(25f / growthSpeed) + 1) == 0)) {
					growDown(world, pos, state);
					CommonHooks.onCropsGrowPost(world, pos, state);
				}
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(getAgeProperty()).add(getHeightProperty());
	}
}
