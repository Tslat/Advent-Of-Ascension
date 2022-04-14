package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.Random;
import java.util.function.Supplier;

public class LunossoTreeFeature extends AoATreeFeature {
	public LunossoTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		switch (rand.nextInt(3)) {
			case 0:
				return generateTree1(reader, rand, pos, isWorldGen);
			case 1:
				return generateTree2(reader, rand, pos, isWorldGen);
			case 2:
			default:
				return generateTree3(reader, rand, pos, isWorldGen);
		}
	}

	private boolean generateTree1(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 3 + rand.nextInt(5);
		int leafLoopWidth = 1 + rand.nextInt(2);

		if (!checkSafeHeight(reader, pos, trunkHeight + leafLoopWidth + 3, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.LUNIDE_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUNOSSO_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		movablePos.move(Direction.UP, 2);

		int leafLoop = rand.nextInt(3);

		if (leafLoop == 2 || leafLoop == 0) {
			for (int x = -leafLoopWidth; x <= leafLoopWidth; x++) {
				placeBlock(reader, movablePos.offset(x, 0, 0), leaves);
				placeBlock(reader, movablePos.offset(x, leafLoopWidth * 2, 0), leaves);

				if (Math.abs(x) == leafLoopWidth) {
					for (int y = 1; y <= leafLoopWidth + 1; y++) {
						placeBlock(reader, movablePos.offset(x, y, 0), leaves);
					}
				}
			}
		}

		if (leafLoop == 2 || leafLoop == 1) {
			for (int z = -leafLoopWidth; z <= leafLoopWidth; z++) {
				placeBlock(reader, movablePos.offset(0, 0, z), leaves);
				placeBlock(reader, movablePos.offset(0, leafLoopWidth * 2, z), leaves);

				if (Math.abs(z) == leafLoopWidth) {
					for (int y = 1; y <= leafLoopWidth + 1; y++) {
						placeBlock(reader, movablePos.offset(0, y, z), leaves);
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree2(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 3 + rand.nextInt(5);
		int leafRings = 1 + rand.nextInt(3);

		if (!checkSafeHeight(reader, pos, trunkHeight + leafRings * 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.LUNIDE_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUNOSSO_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		for (int y = 2; y <= leafRings * 2; y += 2) {
			int ringWidth = 1 + rand.nextInt(2);

			for (int x = -ringWidth; x <= ringWidth; x++) {
				for (int z = -ringWidth; z <= ringWidth; z++) {
					if (Math.abs(x) == ringWidth || Math.abs(z) == ringWidth)
						placeBlock(reader, movablePos.offset(x, y, z), leaves);
				}
			}
		}

		return true;
	}

	private boolean generateTree3(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 3 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + 8, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.LUNIDE_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUNOSSO_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		movablePos.move(Direction.UP, 5);

		for (int x = -3; x <= 3; x++) {
			for (int y = -3; y <= 3; y++) {
				for (int z = -3; z <= 3; z++) {
					if (x * x + y * y + z * z == 9)
						placeBlock(reader, movablePos.offset(x, y, z), leaves);
				}
			}
		}

		return true;
	}
}
