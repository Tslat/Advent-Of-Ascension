package net.tslat.aoa3.worldgen.carvers;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class GreckonCaveCarver extends CaveWorldCarver {
	public GreckonCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int maxHeight) {
		super(config, maxHeight);
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

			if (!canCarveBlock(preCarvedBlock, blockAbove)) {
				return false;
			}
			else {
				if (y < 10) {
					chunk.setBlockState(blockPos, Blocks.WATER.getDefaultState(), false);
				}
				else {
					chunk.setBlockState(blockPos, CAVE_AIR, false);
				}

				return true;
			}
		}
	}

	@Override
	protected boolean canCarveBlock(BlockState state, BlockState aboveState) {
		Block block = state.getBlock();

		return (block == AoABlocks.GRECKON_GRASS.get() || block == AoABlocks.GRECKON_STONE.get()) && !aboveState.getFluidState().isTagged(FluidTags.WATER);
	}
}
