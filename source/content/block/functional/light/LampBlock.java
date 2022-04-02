package net.tslat.aoa3.content.block.functional.light;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Random;

public class LampBlock extends RedstoneLampBlock {
	public static final BooleanProperty TOGGLEABLE = BooleanProperty.create("toggleable");

	public LampBlock(Block.Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(TOGGLEABLE, true));
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!world.isClientSide() && state.getValue(TOGGLEABLE)) {
			boolean lit = state.getValue(LIT);

			if (lit != world.hasNeighborSignal(pos)) {
				if (lit) {
					world.scheduleTick(pos, this, 4);
				}
				else {
					world.setBlock(pos, state.cycle(LIT), 2);
				}
			}
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (state.getValue(TOGGLEABLE) && state.getValue(LIT) && !world.hasNeighborSignal(pos))
			world.setBlock(pos, state.cycle(LIT), 2);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIT).add(TOGGLEABLE);
	}
}
