package net.tslat.aoa3.content.block.generation.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Predicate;

public class GenericPlantBlock extends BushBlock {
    private final VoxelShape shape;
    private final Predicate<BlockState> validSurface;

    public GenericPlantBlock(Properties properties, Predicate<BlockState> validSurface, float width, float height) {
        super(properties);

        this.shape = buildShape(width, height);
        this.validSurface = validSurface;
    }

    protected VoxelShape buildShape(float width, float height) {
        float lateralRadius = width / 2f * 0.707106781f * 1.000093959f;

        return Block.box(8 - lateralRadius, 0, 8 - lateralRadius, 8 + lateralRadius, height, 8 + lateralRadius);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return MapCodec.unit(this);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shape;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return this.validSurface.test(state);
    }
}
