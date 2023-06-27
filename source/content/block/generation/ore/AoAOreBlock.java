package net.tslat.aoa3.content.block.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.smartbrainlib.util.RandomUtil;

public class AoAOreBlock extends Block {
	private final int minXp;
	private final int maxXp;

	public AoAOreBlock(BlockBehaviour.Properties properties, int minXp, int maxXp) {
		super(properties);

		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	public AoAOreBlock(BlockBehaviour.Properties properties) {
		this(properties, 0, 0);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader world, RandomSource randomSource, BlockPos pos, int fortune, int silkTouch) {
		if (silkTouch > 0 || this.maxXp == 0)
			return 0;

		return (int)(RandomUtil.randomNumberBetween(this.minXp, this.maxXp) * (1 + 0.15f * fortune));
	}
}
