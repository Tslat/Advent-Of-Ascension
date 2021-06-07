package net.tslat.aoa3.world.gen.feature.features.trees;

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

public class InvertedAchonyTreeFeature extends AoATreeFeature {
	public InvertedAchonyTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean checkSafeHeight(ISeedReader reader, BlockPos pos, int maxHeight, int trunkWidth, boolean isWorldGen) {
		return pos.getY() <= 256 && pos.getY() - maxHeight >= 1;
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 20 + rand.nextInt(10);
		int coreHeight = 4 + rand.nextInt(3);

		if (!checkSafeHeight(reader, pos, trunkHeight + (int)(coreHeight * 1.5f), 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.above());
		BlockState log = AoABlocks.ACHONY_LOG.get().defaultBlockState();
		boolean lastRowRinged = false;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.DOWN), log);

			if (i >= 3 && rand.nextFloat() < 0.4f && !lastRowRinged) {
				buildLeafRing(reader, movablePos.immutable());

				lastRowRinged = true;
			}
			else {
				lastRowRinged = false;
			}
		}

		buildCrown(reader, movablePos.immutable(), coreHeight);

		return true;
	}

	private void buildCrown(ISeedReader reader, BlockPos pos, int coreHeight) {
		BlockState achonyLeaves = AoABlocks.ACHONY_LEAVES.get().defaultBlockState();
		BlockState lelyetianLeaves = AoABlocks.LELYETIAN_LEAVES.get().defaultBlockState();

		buildLeafRing(reader, pos.above(1));

		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				if (x != 0 || z != 0)
					placeBlock(reader, pos.offset(x, 0, z), achonyLeaves);
			}
		}

		for (int x = -3; x <= 3; x++) {
			placeBlock(reader, pos.offset(x, -1, -3), achonyLeaves);
			placeBlock(reader, pos.offset(x, -1, 3), achonyLeaves);
		}

		for (int z = -2; z <= 2; z++) {
			placeBlock(reader, pos.offset(3, -1, z), achonyLeaves);
			placeBlock(reader, pos.offset(-3, -1, z), achonyLeaves);
		}

		placeBlock(reader, pos.offset(3, -2, 2), achonyLeaves);
		placeBlock(reader, pos.offset(3, -2, 3), achonyLeaves);
		placeBlock(reader, pos.offset(3, -2, -2), achonyLeaves);
		placeBlock(reader, pos.offset(3, -2, -3), achonyLeaves);
		placeBlock(reader, pos.offset(-3, -2, 2), achonyLeaves);
		placeBlock(reader, pos.offset(-3, -2, 3), achonyLeaves);
		placeBlock(reader, pos.offset(-3, -2, -2), achonyLeaves);
		placeBlock(reader, pos.offset(-3, -2, -3), achonyLeaves);
		placeBlock(reader, pos.offset(2, -2, 3), achonyLeaves);
		placeBlock(reader, pos.offset(-2, -2, 3), achonyLeaves);
		placeBlock(reader, pos.offset(2, -2, -3), achonyLeaves);
		placeBlock(reader, pos.offset(-2, -2, -3), achonyLeaves);
		placeBlock(reader, pos.offset(3, -3, 3), achonyLeaves);
		placeBlock(reader, pos.offset(-3, -3, -3), achonyLeaves);
		placeBlock(reader, pos.offset(-3, -3, 3), achonyLeaves);
		placeBlock(reader, pos.offset(3, -3, -3), achonyLeaves);

		for (int x = -1; x <= 1; x++) {
			for (int y = 0; y <= coreHeight; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, -y, z), lelyetianLeaves);
				}
			}
		}

		for (int y = coreHeight; y <= (int)(coreHeight * 1.5); y++) {
			placeBlock(reader, pos.offset(0, -y, 0), lelyetianLeaves);
		}
	}

	private void buildLeafRing(ISeedReader reader, BlockPos pos) {
		BlockState achonyLeaves = AoABlocks.ACHONY_LEAVES.get().defaultBlockState();

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				if (x != 0 || z != 0)
					placeBlock(reader, pos.offset(x, 0, z), achonyLeaves);
			}
		}
	}
}
