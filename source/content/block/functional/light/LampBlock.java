package net.tslat.aoa3.content.block.functional.light;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class LampBlock extends RedstoneLampBlock {
	public static final BooleanProperty TOGGLEABLE = BooleanProperty.create("toggleable");

	public LampBlock(AbstractBlock.Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(TOGGLEABLE, true));
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!world.isClientSide() && state.getValue(TOGGLEABLE)) {
			boolean lit = state.getValue(LIT);

			if (lit != world.hasNeighborSignal(pos)) {
				if (lit) {
					world.getBlockTicks().scheduleTick(pos, this, 4);
				}
				else {
					world.setBlock(pos, state.cycle(LIT), 2);
				}
			}
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (state.getValue(TOGGLEABLE) && state.getValue(LIT) && !world.hasNeighborSignal(pos))
			world.setBlock(pos, state.cycle(LIT), 2);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(LIT).add(TOGGLEABLE);
	}
}
