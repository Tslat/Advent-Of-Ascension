package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class StaticMushroomBlock extends MushroomBlock {
	public StaticMushroomBlock(Properties properties) {
		super(properties, () -> null);
	}

	@Override
	public boolean growMushroom(ServerLevel level, BlockPos pos, BlockState state, Random random) {
		return false;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return false;
	}
}
