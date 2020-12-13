package net.tslat.aoa3.worldgen.carvers;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoATags;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class CreeponiaCaveCarver extends CaveWorldCarver {
	public CreeponiaCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int maxHeight) {
		super(config, maxHeight);
	}

	@Override
	protected float generateCaveRadius(Random rand) {
		return super.generateCaveRadius(rand) * 1.25f;
	}

	@Override
	protected boolean carveBlock(IChunk chunk, Function<BlockPos, Biome> biomePos, BitSet carvingMask, Random rand, BlockPos.Mutable blockPos, BlockPos.Mutable upPos, BlockPos.Mutable downPos, int p_225556_8_, int p_225556_9_, int p_225556_10_, int x, int z, int xOffset, int y, int zOffset, AtomicBoolean carvedTopBlock) {
		int carvingBit = xOffset | zOffset << 4 | y << 8;

		if (carvingMask.get(carvingBit)) {
			return false;
		}
		else {
			carvingMask.set(carvingBit);
			blockPos.setPos(x, y, z);

			BlockState preCarvedBlock = chunk.getBlockState(blockPos);
			BlockState blockAbove = chunk.getBlockState(upPos.setPos(blockPos).move(Direction.UP));

			if (preCarvedBlock.getBlock().isIn(AoATags.Blocks.GRASS))
				carvedTopBlock.set(true);

			if (!canCarveBlock(preCarvedBlock, blockAbove)) {
				return false;
			}
			else {
				if (y <= 5) {
					chunk.setBlockState(blockPos, AoABlocks.PRIMED_STONE.get().getDefaultState(), false);
				}
				else {
					chunk.setBlockState(blockPos, CAVE_AIR, false);

					if (carvedTopBlock.get()) {
						downPos.setPos(blockPos).move(Direction.DOWN);

						ISurfaceBuilderConfig config = biomePos.apply(blockPos).getSurfaceBuilderConfig();

						if (chunk.getBlockState(downPos).getBlock() == config.getUnder().getBlock())
							chunk.setBlockState(downPos, config.getTop(), false);
					}
				}

				return true;
			}
		}
	}

	@Override
	protected boolean canCarveBlock(BlockState state, BlockState aboveState) {
		Block block = state.getBlock();

		return (block == AoABlocks.CREEP_DIRT.get() || block == AoABlocks.CREEP_GRASS.get() || block == AoABlocks.PRIMED_STONE.get() || block == AoABlocks.CREEP_STONE.get() || block == AoABlocks.PRESSED_CREEP_STONE.get() || block == AoABlocks.CREEP_CRYSTAL.get());
	}
}
