package net.tslat.aoa3.content.block.generation.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;
import org.jetbrains.annotations.Nullable;

// Same as vanilla leaves but extends decay dist cus vanilla limit why?
public class AoALeavesBlock extends Block implements SimpleWaterloggedBlock, IShearable {
	public static final int DECAYS_AT_DIST = 15;
	public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 15);
	public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public AoALeavesBlock(Properties properties) {
		super(properties);

		registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(DECAYS_AT_DIST)).setValue(PERSISTENT, false).setValue(WATERLOGGED, false));
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 30;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}

	@Override
	public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
		return Shapes.empty();
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(DISTANCE) == DECAYS_AT_DIST && !state.getValue(PERSISTENT);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (shouldDecay(state)) {
			dropResources(state, level, pos);
			level.removeBlock(pos, false);
		}
	}

	protected boolean shouldDecay(BlockState state) {
		return !state.getValue(PERSISTENT) && state.getValue(DISTANCE) == DECAYS_AT_DIST;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource pRandom) {
		level.setBlock(pos, updateLeafDistance(state, level, pos), Block.UPDATE_ALL);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
		return 1;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
		if (state.getValue(WATERLOGGED)) {
			final Fluid fluid = level instanceof WorldGenRegion ? Fluids.WATER : level.getFluidState(pos).getType();

			level.scheduleTick(pos, fluid, fluid.getTickDelay(level));
		}

		final int dist = getLeafDistance(neighbour);

		if (dist > 1 || state.getValue(DISTANCE) != dist)
			level.scheduleTick(pos, this, 1);

		return state;
	}

	public static int getLeafDistance(BlockState neighbourState) {
		if (neighbourState.is(BlockTags.LOGS))
			return 0;

		return neighbourState.hasProperty(DISTANCE) ? neighbourState.getValue(DISTANCE) : DECAYS_AT_DIST;
	}

	protected static BlockState updateLeafDistance(BlockState state, LevelAccessor level, BlockPos pos) {
		int minDist = DECAYS_AT_DIST;
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

		for (Direction direction : Direction.values()) {
			checkPos.setWithOffset(pos, direction);

			minDist = Math.min(minDist, getLeafDistance(level.getBlockState(checkPos)) + 1);

			if (minDist == 1)
				break;
		}

		return state.setValue(DISTANCE, minDist);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (!level.isRainingAt(pos.above()) || random.nextInt(15) > 0)
			return;

		final BlockPos belowPos = pos.below();
		final BlockState belowState = level.getBlockState(belowPos);

		if (!belowState.canOcclude() || !belowState.isFaceSturdy(level, belowPos, Direction.UP))
			ParticleUtils.spawnParticleBelow(level, pos, random, ParticleTypes.DRIPPING_WATER);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DISTANCE, PERSISTENT, WATERLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		final Level level = context.getLevel();
		final BlockPos clickedPos = context.getClickedPos();

		return updateLeafDistance(defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, level.getFluidState(clickedPos).getType() == Fluids.WATER), level, clickedPos);
	}
}
