package net.tslat.aoa3.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.tslat.aoa3.block.functional.sapling.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class IrogoldTreeFeature extends AoATreeFeature {
	public IrogoldTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(reader, rand, pos, 2, isWorldGen);

		if ((isWorldGen && rand.nextBoolean()) || multiSaplingPos == null) {
			return generateTree1(reader, rand, pos, isWorldGen);
		}
		else {
			return generateTree2(reader, rand, multiSaplingPos, isWorldGen);
		}
	}

	private boolean generateTree1(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 7 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.IROLOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.IROGOLD_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		for (int x = -3; x <= 3; x++) {
			for (int y = 0; y <= 2; y++) {
				for (int z = -3; z <= 3; z++) {
					if (Math.abs(x) < 4 - y - Math.abs(z) || Math.abs(z) < 4 - y - Math.abs(x))
						placeBlock(reader, movablePos.offset(x, y, z), leaves);
				}
			}
		}

		placeBlock(reader, movablePos.offset(0, -1, 1), leaves);
		placeBlock(reader, movablePos.offset(0, -1, -1), leaves);
		placeBlock(reader, movablePos.offset(1, -1, 0), leaves);
		placeBlock(reader, movablePos.offset(-1, -1, 0), leaves);

		return true;
	}

	private boolean generateTree2(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 7 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 2, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 2, isWorldGen))
			return false;

		BlockState baseSoil = reader.getBlockState(pos.below());

		for (int x = 0; x < 2; x++) {
			for (int z = 0; z < 2; z++) {
				BlockPos testPos = pos.offset(x, -1, z);

				if (reader.getBlockState(testPos).is(Blocks.AIR))
					reader.setBlock(testPos, baseSoil, 2);
			}
		}

		BlockState log = AoABlocks.IROLOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.IROGOLD_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, i, z), log);
				}
			}
		}

		pos = pos.above(trunkHeight - 1);
		int branchWidth = 2 + rand.nextInt(3);

		for (int i = 1; i <= branchWidth; i++) {
			for (int j = -1; j <= 1; j += 2) {
				int i2 = (j < 0 ? i - 1 : i) * j;

				placeBlock(reader, pos.offset(i2, 0, 0), log);
				placeBlock(reader, pos.offset(i2, 0, 1), log);
				placeBlock(reader, pos.offset(0, 0, i2), log);
				placeBlock(reader, pos.offset(1, 0, i2), log);

				if (i == branchWidth) {
					for (Direction dir : Direction.values()) {
						BlockPos leafPos = pos.relative(dir);

						placeBlock(reader, leafPos.offset(i2, 0, 0), leaves);
						placeBlock(reader, leafPos.offset(i2, 0, 1), leaves);
						placeBlock(reader, leafPos.offset(0, 0, i2), leaves);
						placeBlock(reader, leafPos.offset(1, 0, i2), leaves);
					}
				}
			}
		}

		return true;
	}
}
