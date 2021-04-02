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

public class LuniciaTreeFeature extends AoATreeFeature {
	public LuniciaTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
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
		int trunkHeight = 6 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.LUNIDE_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUNICIA_LEAVES.get().defaultBlockState();

		int leafGap = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);

			if (leafGap >= 2 && rand.nextFloat() < 0.65f) {
				leafGap = 0;

				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						if (Math.abs(x) == 2 || Math.abs(z) == 2)
							placeBlock(reader, movablePos.offset(x, 0, z), leaves);
					}
				}
			}

			leafGap++;
		}

		return true;
	}

	private boolean generateTree2(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 6 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.LUNIDE_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUNICIA_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		int leafPillars = 2 + rand.nextInt(5);

		for (double i = 0; i < Math.PI * 2; i += Math.PI / leafPillars) {
			int x = (int)Math.round(Math.cos(i) * 2);
			int z = (int)Math.round(Math.sin(i) * 2);
			int initY = rand.nextInt(2);
			int endY = rand.nextInt(2);

			for (int y = initY; y <= trunkHeight - 3 - endY; y++) {
				placeBlock(reader, movablePos.offset(x, -y, z), leaves);
			}
		}

		return true;
	}
}
