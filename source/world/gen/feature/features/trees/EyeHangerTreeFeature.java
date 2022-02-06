package net.tslat.aoa3.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.object.block.functional.plant.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class EyeHangerTreeFeature extends AoATreeFeature {
	public EyeHangerTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 5 + rand.nextInt(5);
		int weaveHeight = 8 + rand.nextInt(8);

		if (!checkSafeHeight(reader, pos, trunkHeight + weaveHeight, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.SHADOW_LOG.get().defaultBlockState();
		BlockState barkLog = AoABlocks.SHADOW_WOOD.get().defaultBlockState();
		int branchWidth = 1 + rand.nextInt(3);

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		for (int i = 1; i <= branchWidth; i++) {
			placeBlock(reader, movablePos.offset(i, 0, 0), barkLog);
			placeBlock(reader, movablePos.offset(-i, 0, 0), barkLog);
			placeBlock(reader, movablePos.offset(0, 0, i), barkLog);
			placeBlock(reader, movablePos.offset(0, 0, -i), barkLog);

			if (i == branchWidth && rand.nextBoolean()) {
				placeBlock(reader, movablePos.offset(i, 1, 0), log);
				placeBlock(reader, movablePos.offset(-i, 1, 0), log);
				placeBlock(reader, movablePos.offset(0, 1, i), log);
				placeBlock(reader, movablePos.offset(0, 1, -i), log);
			}
		}

		doLeaves(reader, movablePos.above(), rand, branchWidth);

		return true;
	}

	private void doLeaves(ISeedReader reader, BlockPos pos, Random rand, int branchWidth) {
		BlockState leaves = AoABlocks.SHADOWBLOOD_LEAVES.get().defaultBlockState();

		for (int i = -branchWidth - 1; i <= branchWidth + 1; i++) {
			placeBlock(reader, pos.offset(-i, 0, 0), leaves);

			if (Math.abs(i) > branchWidth && rand.nextInt(7) == 0)
				doEyeHanger(reader, pos.offset(i, 0, 0), rand);

			for (int j = 0; j <= branchWidth; j++) {
				if (Math.abs(i) <= branchWidth - j + 1) {
					placeBlock(reader, pos.offset(i, 0, j + 1), leaves);
					placeBlock(reader, pos.offset(-i, 0, -j - 1), leaves);

					if (i != 0) {
						if (rand.nextInt(7) == 0) {
							doEyeHanger(reader, pos.offset(i, 0, -j - 1), rand);
						}

						if (rand.nextInt(7) == 0) {
							doEyeHanger(reader, pos.offset(i, 0, j + 1), rand);
						}
					}
				}
			}
		}

		for (int i = -branchWidth; i <= branchWidth; i++) {
			for (int j = 0; j <= branchWidth - Math.abs(i); j++) {
				placeBlock(reader, pos.offset(i, 1, j), leaves);
				placeBlock(reader, pos.offset(i, 1, -j), leaves);
			}
		}

		if (branchWidth == 3) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, 2, z), leaves);
				}
			}
		}
	}

	private void doEyeHanger(ISeedReader reader, BlockPos leafPos, Random rand) {
		BlockState bloodStrands = AoABlocks.BLOOD_STRANDS.get().defaultBlockState();
		BlockState eye = AoABlocks.EYE_BLOCK.get().defaultBlockState();
		int length = 2 + rand.nextInt(3);

		for (int i = 1; i <= length; i++) {
			placeBlock(reader, leafPos.below(i), bloodStrands);
		}

		placeBlock(reader, leafPos.below(length + 1), eye);
	}
}
