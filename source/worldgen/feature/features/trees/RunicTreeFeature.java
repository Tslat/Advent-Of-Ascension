package net.tslat.aoa3.worldgen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class RunicTreeFeature extends AoATreeFeature {
	public RunicTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(reader, rand, pos, 2, isWorldGen);

		switch (rand.nextInt(multiSaplingPos != null ? 5 : 4)) {
			case 0:
				return generateTree1(reader, rand, multiSaplingPos == null ? pos : multiSaplingPos, isWorldGen);
			case 1:
				return generateTree2(reader, rand, multiSaplingPos == null ? pos : multiSaplingPos, isWorldGen);
			case 2:
				return generateTree3(reader, rand, multiSaplingPos == null ? pos : multiSaplingPos, isWorldGen);
			case 3:
				return generateTree4(reader, rand, multiSaplingPos == null ? pos : multiSaplingPos, isWorldGen);
			case 4:
				return generateTree5(reader, rand, multiSaplingPos == null ? pos : multiSaplingPos, isWorldGen);
		}

		return false;
	}

	private boolean generateTree1(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 4 + rand.nextInt(5);
		int leafHeight = 2 + rand.nextInt(3);

		if (!checkSafeHeight(reader, pos, trunkHeight + leafHeight - 1, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.RUNIC_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		placeBlock(reader, movablePos.offset(1, 0, 0), log);
		placeBlock(reader, movablePos.offset(0, 0, 1), log);
		placeBlock(reader, movablePos.offset(0, 0, -1), log);
		placeBlock(reader, movablePos.offset(-1, 0, 0), log);
		placeBlock(reader, movablePos.offset(1, 1, 0), log);
		placeBlock(reader, movablePos.offset(0, 1, 1), log);
		placeBlock(reader, movablePos.offset(0, 1, -1), log);
		placeBlock(reader, movablePos.offset(-1, 1, 0), log);

		for (int i = 0; i < leafHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), leaves);
		}

		return true;
	}

	private boolean generateTree2(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 4 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 3, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.RUNIC_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		for (int i = -2; i < 3; i++) {
			if (i != 0) {
				placeBlock(reader, movablePos.offset(i, 0, 0), log);
				placeBlock(reader, movablePos.offset(0, 0, i), log);

				if (Math.abs(i) == 2) {
					placeBlock(reader, movablePos.offset(i, 1, 0), log);
					placeBlock(reader, movablePos.offset(0, 1, i), log);
				}
			}
		}

		int leafHeight = 2 + rand.nextInt(3);

		for (int x = -1; x <= 1; x++) {
			for (int y = 1; y <= leafHeight; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(reader, movablePos.offset(x, y, z), leaves);
				}
			}
		}

		return true;
	}

	private boolean generateTree3(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 7 + rand.nextInt(6);
		int bulbWidth = 3 + rand.nextInt(2);

		if (!checkSafeHeight(reader, pos, trunkHeight + bulbWidth * 2 - 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.RUNIC_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		for (int i = -bulbWidth; i <= bulbWidth; i++) {
			if (i != 0) {
				placeBlock(reader, movablePos.offset(i, 0, 0), log);
				placeBlock(reader, movablePos.offset(0, 0, i), log);

				if (Math.abs(i) == bulbWidth) {
					placeBlock(reader, movablePos.offset(i, 1, 0), log);
					placeBlock(reader, movablePos.offset(i, 2, 0), log);
					placeBlock(reader, movablePos.offset(0, 1, i), log);
					placeBlock(reader, movablePos.offset(0, 2, i), log);
				}
			}
		}

		for (int x = -bulbWidth + 1; x <= bulbWidth - 1; x++) {
			for (int z = -bulbWidth + 1; z <= bulbWidth - 1; z++) {
				for (int y = 1; y <= bulbWidth * 2 - 2; y++) {
					if ((y != 1 && y != bulbWidth * 2 - 2) || (Math.abs(x) != bulbWidth - 1 && Math.abs(z) != bulbWidth - 1))
						placeBlock(reader, movablePos.offset(x, y, z), leaves);
				}
			}
		}

		return true;
	}

	private boolean generateTree4(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 9 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.RUNIC_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		int branchHeight = 2 + rand.nextInt(3);

		for (int i = -4; i <= 4; i++) {
			if (i != 0) {
				placeBlock(reader, movablePos.offset(i, 0, 0), log);
				placeBlock(reader, movablePos.offset(0, 0, i), log);

				if (Math.abs(i) == 4) {
					for (int j = 1; j <= branchHeight; j++) {
						placeBlock(reader, movablePos.offset(i, -j, 0), log);
						placeBlock(reader, movablePos.offset(0, -j, i), log);
					}
				}
			}
		}

		for (int y = -1; y <= 1; y++) {
			int width = 3 - Math.abs(y);

			for (int x = -width; x <= width; x++) {
				for (int z = -width; z <= width; z++) {
					if (x * x + z * z <= width * width) {
						placeBlock(reader, movablePos.offset(x, y - 2, z), leaves);
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree5(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 4 + rand.nextInt(5);
		int leafHeight = 2 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + leafHeight - 1, 2, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 2, isWorldGen))
			return false;

		BlockState log = AoABlocks.RUNIC_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().defaultBlockState();

		for (int x = 0; x <= 1; x++) {
			for (int y = 0; y <= trunkHeight; y++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, y, z), log);
				}
			}
		}

		for (int x = -1; x <= 2; x++) {
			for (int y = 0; y <= 1; y++) {
				for (int z = -1; z <= 2; z++) {
					if (x != -1 && x != 2 || z != -1 && z != 2)
						placeBlock(reader, pos.offset(x, trunkHeight + y, z), log);
				}
			}
		}

		for (int x = 0; x <= 1; x++) {
			for (int y = 1; y <= leafHeight; y++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, trunkHeight + y, z), leaves);
				}
			}
		}

		return true;
	}
}
