package net.tslat.aoa3.content.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.gen.feature.placement.config.BlockStatePlacementConfig;

import java.util.Random;
import java.util.function.Supplier;

public class IrodustTreeFeature extends AoATreeFeature {
	public IrodustTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		if (rand.nextBoolean()) {
			return generateTree1(reader, rand, pos, isWorldGen);
		}
		else {
			return generateTree2(reader, rand, pos, isWorldGen);
		}
	}

	private boolean generateTree1(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 8 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 1, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
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

	private boolean generateTree2(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 8 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
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

	private void buildLeafRing(WorldGenLevel reader, BlockPos pos, int radius, boolean fullRing) {
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
