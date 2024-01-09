package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StackableCappedPlantBlock extends Block implements BonemealableBlock {
    public static final BooleanProperty IS_CAP = BooleanProperty.create("is_cap");
    protected final Direction growthDirection;

    public StackableCappedPlantBlock(Properties properties, Direction growDirection) {
        super(properties);

        this.growthDirection = growDirection;
        registerDefaultState(this.getStateDefinition().any().setValue(IS_CAP, true));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        final Level level = context.getLevel();
        final BlockState attachState = level.getBlockState(context.getClickedPos().relative(this.growthDirection));

        return !isHeadBlock(attachState) && !isStemBlock(attachState) ? getStateForPlacement(level) : getStemBlock();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        final BlockPos attachPos = pos.relative(this.growthDirection.getOpposite());
        final BlockState attachBlock = level.getBlockState(attachPos);

        if (!canSurviveOn(attachBlock))
            return false;

        return isHeadBlock(attachBlock) || isStemBlock(attachBlock) || attachBlock.isFaceSturdy(level, pos, this.growthDirection);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);

            return;
        }

        if (!isHeadBlock(state) && level.getBlockState(pos.relative(this.growthDirection)).isAir())
            level.setBlockAndUpdate(pos, getHeadBlock());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return CaveVines.SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction directionToNeighbour, BlockState neighbourState, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
        if (directionToNeighbour == this.growthDirection.getOpposite() && !state.canSurvive(level, pos))
            level.scheduleTick(pos, this, 1);

        if (directionToNeighbour != this.growthDirection || (!neighbourState.is(this) && !isStemBlock(neighbourState)))
            return state;

        return getStemBlock();
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        BlockState checkState;

        while ((checkState = level.getBlockState(pos = pos.relative(this.growthDirection))).is(this)) {
            ;
        }

        return pos.getY() > level.getMinBuildHeight() && checkState.isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        while (level.getBlockState(pos = pos.relative(this.growthDirection)).is(this)) {
            ;
        }

        level.setBlockAndUpdate(pos, getHeadBlock());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_CAP);
    }

    public boolean canSurviveOn(BlockState state) {
        return true;
    }

    protected BlockState getStateForPlacement(Level level) {
        return defaultBlockState();
    }

    public boolean isHeadBlock(BlockState state) {
       return state.getBlock() == this && state.getValue(IS_CAP);
    }

    public boolean isStemBlock(BlockState state) {
        return state.getBlock() == this && !state.getValue(IS_CAP);
    }

    protected BlockState getHeadBlock() {
        return defaultBlockState().setValue(IS_CAP, true);
    }

    protected BlockState getStemBlock() {
        return defaultBlockState().setValue(IS_CAP, false);
    }
}
