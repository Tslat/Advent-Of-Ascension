package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StaticMushroomBlock extends MushroomBlock {
	public StaticMushroomBlock(Properties properties) {
		super(null, properties);
	}

	@Override
	public boolean growMushroom(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
		return false;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return false;
	}
}
