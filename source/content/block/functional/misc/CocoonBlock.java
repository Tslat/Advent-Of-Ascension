package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.util.BlockUtil;
import org.jetbrains.annotations.Nullable;

public class CocoonBlock extends Block {
    public static final BooleanProperty SUSPENDED = BooleanProperty.create("suspended");
    private static final VoxelShape SUSPENDED_SHAPE = BlockUtil.pixelBasedCube(3, 1, 3, 13, 15, 13);
    private static final VoxelShape SHAPE = BlockUtil.pixelBasedCube(3, 0, 3, 13, 14, 13);

    public CocoonBlock(Properties properties) {
        super(properties);

        registerDefaultState(getStateDefinition().any().setValue(SUSPENDED, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return state.getValue(SUSPENDED) ? SUSPENDED_SHAPE : SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SUSPENDED);
    }
}
