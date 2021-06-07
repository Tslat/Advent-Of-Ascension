package net.tslat.aoa3.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.tslat.aoa3.block.functional.sapling.SaplingBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.server.ServerWorld;
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public abstract class AoATreeFeature extends Feature<BlockStateFeatureConfig> {
	protected final Supplier<SaplingBlock> sapling;

	public AoATreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> sapling) {
		super(codec);

		this.sapling = sapling;
	}

	public boolean generate(ServerWorld world, Random rand, BlockPos pos) {
		return generateTree(world, rand, pos, false);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
		return generateTree(reader, rand, pos, true);
	}

	protected abstract boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen);

	protected void placeBlock(ISeedReader reader, BlockPos pos, BlockState state, boolean overwriteBlocks) {
		if (!overwriteBlocks && !isSafeBlock(reader, pos))
			return;

		reader.setBlock(pos, state, 19);
	}

	protected void placeBlock(ISeedReader reader, BlockPos pos, BlockState state) {
		placeBlock(reader, pos, state, false);
	}

	protected static boolean isSafeBlock(ISeedReader reader, BlockPos pos) {
		BlockState existingState = reader.getBlockState(pos);
		Block existingBlock = existingState.getBlock();

		return existingBlock.isAir(existingState, reader, pos) || existingState.getMaterial().isReplaceable() || existingBlock.is(BlockTags.LEAVES);
	}

	protected boolean checkSafeHeight(ISeedReader reader, BlockPos pos, int maxHeight, int trunkWidth, boolean isWorldGen) {
		if (pos.getY() >= 1 && pos.getY() + maxHeight < 256) {
			if (isWorldGen)
				return true;

			for (int i = 1; i <= maxHeight; i++) {
				for (int x = 0; x < trunkWidth; x++) {
					for (int z = 0; z < trunkWidth; z++) {
						if (!isSafeBlock(reader, pos.offset(x, i, z)))
							return false;
					}
				}
			}

			return true;
		}

		return false;
	}

	protected boolean checkAndPrepSoil(ISeedReader reader, BlockPos pos, int trunkWidth, boolean isWorldGen) {
		if (isWorldGen)
			return true;

		ArrayList<Triple<BlockState, Block, BlockPos>> soils = new ArrayList<Triple<BlockState, Block, BlockPos>>(trunkWidth * trunkWidth);

		for (int x = 0; x < trunkWidth; x++) {
			for (int z = 0; z < trunkWidth; z++) {
				BlockPos soilPos = pos.offset(x, -1, z);
				BlockState soilState = reader.getBlockState(soilPos);
				Block soil = soilState.getBlock();

				if (soil.isAir(soilState, reader, soilPos.above()) || !soil.canSustainPlant(soilState, reader, soilPos, Direction.UP, sapling.get()))
					return false;

				soils.add(Triple.of(soilState, soil, soilPos));
			}
		}

		for (Triple<BlockState, Block, BlockPos> soil : soils) {
			soil.getMiddle().onPlantGrow(soil.getLeft(), reader, soil.getRight(), soil.getRight().above());
			reader.setBlock(soil.getRight().above(), Blocks.AIR.defaultBlockState(), 2);
		}

		return true;
	}

	@Nullable
	protected BlockPos findMultiSaplingPosition(ISeedReader reader, Random rand, BlockPos pos, int size, boolean isWorldGen) {
		if (isWorldGen)
			return pos;

		for (int x = 0; x >= -(size - 1); --x) {
			shuffleSearch:
			for (int z = 0; z >= -(size - 1); --z) {
				BlockPos checkPos = pos;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (reader.getBlockState(checkPos = pos.offset(x + i, 0, z + j)).getBlock() != sapling.get())
							continue shuffleSearch;
					}
				}

				return checkPos.offset(-size + 1, 0, -size + 1);
			}
		}

		return null;
	}
}
