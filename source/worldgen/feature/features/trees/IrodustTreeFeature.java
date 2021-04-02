package net.tslat.aoa3.worldgen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.block.functional.sapling.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class IrodustTreeFeature extends AoATreeFeature {
	public IrodustTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		if (rand.nextBoolean()) {
			return generateTree1(reader, rand, pos, isWorldGen);
		}
		else {
			return generateTree2(reader, rand, pos, isWorldGen);
		}
	}

	private boolean generateTree1(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 8 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 1, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.IROLOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.IRODUST_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		int treeWidth = 1 + rand.nextInt(2);

		for (int x = -treeWidth - 1; x <= treeWidth + 1; x++) {
			for (int z = -treeWidth - 1; z <= treeWidth + 1; z++) {
				if (Math.abs(x) != treeWidth + 1 && Math.abs(z) != treeWidth + 1) {
					placeBlock(reader, movablePos.offset(x, 1, z), leaves);
				}
				else {
					placeBlock(reader, movablePos.offset(x, 0, z), leaves);
				}
			}
		}

		buildLeafRing(reader, movablePos.offset(0, -1, 0), treeWidth + 1, true);

		return true;
	}

	private boolean generateTree2(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 8 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.IROLOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.IRODUST_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		buildLeafRing(reader, movablePos.offset(0, -(trunkHeight / 1.75), 0), 3, false);
		buildLeafRing(reader, movablePos.offset(0, -(trunkHeight / 4), 0), 2, true);

		for (int x = -1; x <= 1; x++) {
			for (int y = 0; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(reader, movablePos.offset(x, y, z), leaves);
				}
			}
		}

		return true;
	}

	private void buildLeafRing(ISeedReader reader, BlockPos pos, int radius, boolean fullRing) {
		BlockState log = AoABlocks.IROLOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.IRODUST_LEAVES.get().defaultBlockState();

		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				if (Math.abs(x) == radius || Math.abs(z) == radius) {
					if (fullRing || !(Math.abs(x) == radius && Math.abs(z) == radius))
						placeBlock(reader, pos.offset(x, 0, z), leaves);
				}
				else if (x == 0 ^ z == 0) {
					placeBlock(reader, pos.offset(x, 0, z), log);
				}
			}
		}
	}
}
