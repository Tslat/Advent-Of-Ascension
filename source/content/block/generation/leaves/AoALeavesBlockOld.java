package net.tslat.aoa3.content.block.generation.leaves;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AoALeavesBlockOld extends net.minecraft.world.level.block.LeavesBlock {
	private final float saplingDropChance;

	public AoALeavesBlockOld(BlockBehaviour.Properties properties, float saplingChance) {
		super(properties);
		// TODO Tick Randomly & decay

		this.saplingDropChance = saplingChance;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {}

	public float getSaplingDropChance() {
		return saplingDropChance;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 30;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}
}
